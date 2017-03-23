package com.bwei.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：
 */

public class GoodsActivity extends AutoLayoutActivity implements View.OnClickListener{

    private ImageButton ib_goods_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
    }

    private void initView() {
        ib_goods_back = (ImageButton) findViewById(R.id.ib_goods_back);
        initClick();
    }

    private void initClick() {
        ib_goods_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_goods_back:
                finish();
                break;
        }
    }
}
