package utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * 姓名:胡文帅
 * 时间:2017/3/19
 * 邮箱：
 * 备注：
 */

public class TestNet {


    /**
     * 检测网络是否存在
     */
    public static void HttpTest(final Activity mActivity)
    {
        if( !isNetworkAvailable( mActivity) ){
            AlertDialog.Builder builders = new AlertDialog.Builder(mActivity);
            builders.setTitle("抱歉，网络连接失败，是否进行网络设置？");
            builders.setPositiveButton("确定",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which)
                        {
                            //进入无线网络配置界面
                            mActivity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                            //startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));  //进入手机中的wifi网络设置界面
                        }
                    });
            builders.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //关闭当前activity
                            mActivity.finish();
                        }
                    });
            builders.show();
        }

    }

    private static boolean isNetworkAvailable(Activity mActivity) {
        Context context = mActivity.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else if(connectivity!=null){
            return  true;
        }
        return false;
    }

}

