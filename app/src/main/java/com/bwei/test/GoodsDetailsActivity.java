package com.bwei.test;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLayoutActivity;

import bean.DetailsBean;
import bean.HomeBean;
import interFace.YuNiFangData;
import utils.MyUrl;
import utils.NetWorkUtils;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：商品详情页
 */

public class GoodsDetailsActivity extends AutoLayoutActivity implements View.OnClickListener, YuNiFangData<DetailsBean>{

    private ImageButton ib_goods_details_back;
    private ImageView iv_goods_details;
    private String goods_img;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                DetailsBean db = (DetailsBean) msg.obj;
                initData(db);
            }
        }
    };
    private TextView goods_tv_shoppingcart;


    private void initData(DetailsBean db) {
        goods_img = db.data.goods.goods_img;

        Glide.with(GoodsDetailsActivity.this).load(goods_img).into(iv_goods_details);
        tv_goods__details_name.setText(db.data.goods.goods_name);
        tv_goods_price.setText("¥ "+db.data.goods.shop_price);
        tv_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_goods_old_price.setText("¥ "+db.data.goods.market_price);
    }

    private TextView tv_goods__details_name;
    private TextView tv_goods_price;
    private TextView tv_goods_old_price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        initView();
        Bundle bundle = this.getIntent().getExtras();
        String id = bundle.getString("id");
        String url= MyUrl.url_xiangqing+id;
        NetWorkUtils.getStr(url, DetailsBean.class,GoodsDetailsActivity.this);
    }


    private void initView() {
        ib_goods_details_back = (ImageButton) findViewById(R.id.ib_goods_details_back);
        iv_goods_details = (ImageView) findViewById(R.id.iv_goods_details);
        tv_goods__details_name = (TextView) findViewById(R.id.tv_goods__details_name);
        tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_goods_old_price = (TextView) findViewById(R.id.tv_goods_old_price);
        goods_tv_shoppingcart = (TextView) findViewById(R.id.goods_tv_shoppingcart);
        initClick();
    }

    //设置点击
    private void initClick() {
        ib_goods_details_back.setOnClickListener(this);
        goods_tv_shoppingcart.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_goods_details_back:
                finish();
                break;

            case R.id.goods_tv_shoppingcart:

                break;
        }
    }

    @Override
    public void ynfdataSuccer(DetailsBean mt) {
        Mes1(mt);
    }

    private void Mes1(DetailsBean db) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = db;
        handler.sendMessage(obtain);
    }
}
