package com.bwei.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import utils.PreferenceUtils;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class SettingsActivity extends Activity implements View.OnClickListener{

    private ImageButton ib_settings_back;
    private Button bt_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        String nickname = PreferenceUtils.getString(SettingsActivity.this, "nickname", "");
        String figureurl_qq_2 = PreferenceUtils.getString(SettingsActivity.this, "figureurl_qq_2", "");
        if(nickname!=null&&figureurl_qq_2!=null){
            bt_out.setVisibility(View.VISIBLE);
        }
    }

    private void initView() {
        ib_settings_back = (ImageButton) findViewById(R.id.ib_settings_back);
        ib_settings_back.setOnClickListener(this);
        bt_out = (Button) findViewById(R.id.bt_out);
        bt_out.setOnClickListener(this);
        bt_out.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_settings_back:
                finish();
                break;

            case R.id.bt_out:
                finish();
                break;
        }
    }
}
