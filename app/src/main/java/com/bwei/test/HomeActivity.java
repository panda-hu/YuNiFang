package com.bwei.test;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Toast;

import com.youth.banner.Banner;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import fragment.FragmentClassify;
import fragment.FragmentHome;
import fragment.FragmentShopping;
import fragment.FragmentUser;
import utils.GlideImageLoader;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class HomeActivity extends AutoLayoutActivity{

    private RadioGroup rg;
    private RadioButton rb_home;
    private RadioButton rb_classify;
    private RadioButton rb_shopping;
    private RadioButton rb_user;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private FragmentHome fg_home;
    private FragmentClassify fg_classify;
    private FragmentShopping fg_shopping;
    private FragmentUser fg_user;
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        /**首先显示首页
         * 判断是否为空
         */
        if(fg_home==null){
            //不为空就实例化
            fg_home=new FragmentHome();
        }
        //调用方法
        addFragment(fg_home);
        //RadioGroup选择
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        if (fg_home == null) {
                            fg_home = new FragmentHome();
                        }
                        addFragment(fg_home);
                        break;

                    case R.id.rb_classify:
                        if (fg_classify == null) {
                            fg_classify = new FragmentClassify();
                        }
                        addFragment(fg_classify);
                        break;

                    case R.id.rb_shopping:
                        if (fg_shopping == null) {
                            fg_shopping = new FragmentShopping();
                        }
                        addFragment(fg_shopping);
                        break;

                    case R.id.rb_user:
                        if (fg_user == null) {
                            fg_user = new FragmentUser();
                        }
                        addFragment(fg_user);
                        break;
                }
            }
        });
    }



    //添加fragment
    private void addFragment(Fragment fg) {
        //得到管理类
        fm= getSupportFragmentManager();
        //开启事务
        transaction = fm.beginTransaction();
        //赋值
        if(fragment!=null){
            transaction.hide(fragment);
        }
        //判断是否被Add过
        if(!fg.isAdded()){
            transaction.add(R.id.fl,fg);
        }
        transaction.show(fg);
        transaction.commit();
        fragment=fg;
    }

    //添加布局
    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_home.setChecked(true);
        rb_classify = (RadioButton) findViewById(R.id.rb_classify);
        rb_shopping = (RadioButton) findViewById(R.id.rb_shopping);
        rb_user = (RadioButton) findViewById(R.id.rb_user);
    }

    //再按退出
    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() ==
                KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
