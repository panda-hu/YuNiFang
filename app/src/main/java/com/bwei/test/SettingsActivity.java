package com.bwei.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class SettingsActivity extends Activity implements View.OnClickListener{

    private ImageButton ib_settings_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    private void initView() {
        ib_settings_back = (ImageButton) findViewById(R.id.ib_settings_back);
        ib_settings_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_settings_back:
                finish();
                break;
        }
    }
}
