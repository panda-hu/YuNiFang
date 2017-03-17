package com.bwei.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class WelcomeActivity extends Activity{

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                    Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
