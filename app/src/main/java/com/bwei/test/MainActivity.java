package com.bwei.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import adapter.GuideAdapter;
import guide.Guide1;
import guide.Guide2;
import guide.Guide3;

public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SharedPreferences sp=getSharedPreferences("YuNiFang",MODE_PRIVATE);
        boolean flag=sp.getBoolean("flag",false);
        if(flag==false){
            vp.setAdapter(new GuideAdapter(getSupportFragmentManager(),list));
            sp.edit().putBoolean("flag",true).commit();
        }else {
            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
            finish();
        }

    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp_main);
        list = new ArrayList<>();
        Guide1 guide1=new Guide1();
        Guide2 guide2=new Guide2();
        Guide3 guide3=new Guide3();
        list.add(guide1);
        list.add(guide2);
        list.add(guide3);

    }
}
