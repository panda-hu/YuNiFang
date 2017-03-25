package com.bwei.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import adapter.recycler.RvGoodsActivityAdapter;
import bean.DetailsBean;
import bean.FenLeiShuJu;
import interFace.OnRecyclerViewItemClickListener;
import interFace.YuNiFangData;
import utils.MyUrl;
import utils.NetWorkUtils;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：商品展示页
 */

public class GoodsActivity extends AutoLayoutActivity implements View.OnClickListener, YuNiFangData<FenLeiShuJu>{


    private ImageButton ib_goods_back;
    private TextView tv_goods_title;
    private RecyclerView recyclerview_gv_goods;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                FenLeiShuJu fenLeiShuJu= (FenLeiShuJu) msg.obj;
                final List<FenLeiShuJu.DataBean> data = fenLeiShuJu.getData();
                recyclerview_gv_goods.setLayoutManager(new GridLayoutManager(GoodsActivity.this,2));
                RvGoodsActivityAdapter rvgoodsAdapter=new RvGoodsActivityAdapter(GoodsActivity.this,data);
                rvgoodsAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        Toast.makeText(GoodsActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(GoodsActivity.this, GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", data.get(position).getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                });
                recyclerview_gv_goods.setAdapter(rvgoodsAdapter);
            }
        }
    };

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
        if(is_leaf.equals("1")){
            if(id.equals("38")){
                NetWorkUtils.getStr(MyUrl.url_tieshi,FenLeiShuJu.class,this);
            }
            else{
                String url_sx=MyUrl.url_shuxing+id;
                NetWorkUtils.getStr(url_sx,FenLeiShuJu.class,this);
            }

        } else if(is_leaf.equals("2")){
            String url_gx=MyUrl.url_gongxiao+id;
            NetWorkUtils.getStr(url_gx,FenLeiShuJu.class,this);
        } else if(is_leaf.equals("3")){
            String url_fz=MyUrl.url_fuzhi+id;
            NetWorkUtils.getStr(url_fz,FenLeiShuJu.class,this);
        }

    }

    private void initView() {
        ib_goods_back = (ImageButton) findViewById(R.id.ib_goods_back);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        recyclerview_gv_goods = (RecyclerView) findViewById(R.id.recyclerview_gv_goods);
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

    @Override
    public void ynfdataSuccer(FenLeiShuJu flsj) {
        Mes1(flsj);

    }
    private void Mes1(FenLeiShuJu flsj) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = flsj;
        handler.sendMessage(obtain);
    }
}
