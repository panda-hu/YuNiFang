package fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.test.LoginActivity;
import com.bwei.test.R;
import com.bwei.test.SettingsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import bean.QQbean;
import interFace.Qback;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import utils.PreferenceUtils;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：我的页
 */

public class FragmentUser extends Fragment implements View.OnClickListener{

    private View view;
    private ImageButton iv_settings;
    private RelativeLayout rl;
    private ImageView iv_user_tiaofu;
    private ImageView iv_user_default;
    private TextView tv_user_default;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_user,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
//        SharedPreferences sp = LoginActivity.sp;
//        String nickname = sp.getString("nickname","00");
//        String figureurl_qq_2 = sp.getString("figureurl_qq_2","00");
        String nickname = PreferenceUtils.getString(getActivity(), "nickname", "");
        String figureurl_qq_2 = PreferenceUtils.getString(getActivity(), "figureurl_qq_2", "");
        if(nickname!=null&&figureurl_qq_2!=null){
            Glide.with(getActivity()).load(figureurl_qq_2).bitmapTransform(new CropCircleTransformation(getActivity())).crossFade(1000).into(iv_user_default);
            tv_user_default.setText(nickname);
        }
        else {

        }
    }

    private void initView() {
        rl = (RelativeLayout) view.findViewById(R.id.rl_user);
        iv_settings = (ImageButton) view.findViewById(R.id.ib_settings);
        iv_user_tiaofu = (ImageView) view.findViewById(R.id.iv_user_tiaofu);
        iv_user_default = (ImageView) view.findViewById(R.id.iv_user_default);
        tv_user_default = (TextView) view.findViewById(R.id.tv_user);
        rl.setOnClickListener(this);
        iv_settings.setOnClickListener(this);
        iv_user_tiaofu.setOnClickListener(this);

    }

    private void intentView(Activity activity){
        Intent intent=new Intent(getActivity(), activity.getClass());
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_settings:
                SettingsActivity sa=new SettingsActivity();
                intentView(sa);
                break;

            case R.id.rl_user:
                LoginActivity la=new LoginActivity();
                intentView(la);
                break;
        }

    }


}
