package com.bwei.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：注册页
 */

public class RegisterActivity extends AutoLayoutActivity implements View.OnClickListener{

    private ImageButton ib_register_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        ib_register_back = (ImageButton) findViewById(R.id.ib_register_back);
        ib_register_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_register_back:
                finish();
                break;
        }
    }
}
