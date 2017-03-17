package com.bwei.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import fragment.FragmentUser;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：登录页
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;

    private TextView tv_register;
    private ImageButton ib_login_back;
    private ImageButton ib_login_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,LoginActivity.this.getApplicationContext());

    }

    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        ib_login_back = (ImageButton) findViewById(R.id.ib_login_back);
        ib_login_back.setOnClickListener(this);
        ib_login_qq = (ImageButton) findViewById(R.id.ib_login_qq);
        ib_login_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.ib_login_back:
                finish();
                break;

            case R.id.ib_login_qq:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(LoginActivity.this,"all", mIUiListener);
                break;
        }
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject jsonObject= (JSONObject) response;
                        String nickname = jsonObject.optString("nickname");
                        String figureurl_qq_2 = jsonObject.optString("figureurl_qq_2");
                        FragmentUser fu=new FragmentUser();
                        Bundle bundle=new Bundle();
                        bundle.putString("nickname",nickname);
                        bundle.putString("figureurl_qq_2",figureurl_qq_2);
                        fu.setArguments(bundle);
                        finish();
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }
    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
