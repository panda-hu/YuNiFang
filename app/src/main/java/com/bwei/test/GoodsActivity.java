package com.bwei.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import utils.MyUrl;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：商品展示页
 */

public class GoodsActivity extends AutoLayoutActivity implements View.OnClickListener{

    private ImageButton ib_goods_back;
    private TextView tv_goods_title;
    private RecyclerView recyclerview_goods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        String title=bundle.getString("title");
        String is_leaf = bundle.getString("is_leaf");
        tv_goods_title.setText(title);
        if(is_leaf.equals("0")){

        } else if(is_leaf.equals("1")){

        } else if(is_leaf.equals("2")){

        }

    }

    private void initView() {
        ib_goods_back = (ImageButton) findViewById(R.id.ib_goods_back);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        recyclerview_goods = (RecyclerView) findViewById(R.id.recyclerview_gv_goods);
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
