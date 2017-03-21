package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bwei.test.R;
import com.bwei.test.WebViewActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import bean.HomeBean;
import interFace.YuNiFangData;
import utils.GlideImageLoader;
import utils.MyUrl;
import utils.NetWorkUtils;
import utils.TestNet;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：主页
 */

public class FragmentHome extends Fragment implements View.OnClickListener, YuNiFangData<HomeBean>{

    private View view;
    private List<String> list_lunbo_image;
    private Banner banner;
    private PullToRefreshScrollView ptr_scrollview;
    private RelativeLayout r1;
    private RelativeLayout r2;
    private RelativeLayout r3;
    private RelativeLayout r4;
    private ImageView iv_meiri;
    private ImageView iv_jifen;
    private ImageView iv_duihuan;
    private ImageView iv_zhenwei;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private Banner banner_huodong;
    private List<String> list_huodong;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        list_lunbo_image = new ArrayList<>();
        list_huodong = new ArrayList<>();
        ptr_scrollview.getLoadingLayoutProxy().setPullLabel("下拉刷新...");
        ptr_scrollview.getLoadingLayoutProxy().setReleaseLabel("松开加载...");
        //只支持下拉
        ptr_scrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        NetWorkUtils.getStr(MyUrl.url_home,HomeBean.class,this);
        initData();


    }

    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        banner.setImages(list_lunbo_image);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner_huodong.setImageLoader(new GlideImageLoader());
        banner_huodong.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner_huodong.isAutoPlay(false);
        banner_huodong.setImages(list_huodong);
        banner_huodong.start();
    }

    private void initData() {
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160823172997710201253418883.png").into(iv_meiri);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120383916524110935835.png").into(iv_jifen);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120326416505640517284.png").into(iv_duihuan);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120430916487170096321.png").into(iv_zhenwei);
    }

    private void initView() {
        ptr_scrollview = (PullToRefreshScrollView) view.findViewById(R.id.ptr_scrollview);
        banner = (Banner) view.findViewById(R.id.banner);
        banner_huodong = (Banner) view.findViewById(R.id.banner_huodong);
        r1 = (RelativeLayout) view.findViewById(R.id.r1);
        r2 = (RelativeLayout) view.findViewById(R.id.r2);
        r3 = (RelativeLayout) view.findViewById(R.id.r3);
        r4 = (RelativeLayout) view.findViewById(R.id.r4);
        iv_meiri = (ImageView) view.findViewById(R.id.iv_meiri);
        iv_jifen = (ImageView) view.findViewById(R.id.iv_jifen);
        iv_duihuan = (ImageView) view.findViewById(R.id.iv_duihuan);
        iv_zhenwei = (ImageView) view.findViewById(R.id.iv_zhenwei);
        iv_meiri.setOnClickListener(this);
        iv_jifen.setOnClickListener(this);
        iv_duihuan.setOnClickListener(this);
        iv_zhenwei.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:
                intentWV("http://h.yunifang.com/sign/sign.html?login_check=2");
                break;
            case R.id.r2:
                intentWV("http://m.yunifang.com/yunifang/mobile" +
                        "/creditShop/loginDBShop?dbredirect=" +
                        "http://www.duiba.com.cn/chome/index");
                break;
            case R.id.r3:
                intentWV("http://h.yunifang.com/exchange/code_app.html?login_check=1");
                break;
            case R.id.r4:
                intentWV("http://www.yunifang.com/a/fangweichaxun/wap_fwcx.html");
                break;
        }
    }
    public void intentWV(String url){
        Intent intent=new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void ynfdataSuccer(HomeBean mt) {
        //轮播图的集合
        List<HomeBean.Ad1> ad1 = mt.data.ad1;
        for (int i = 0; i <ad1.size() ; i++) {
            list_lunbo_image.add(ad1.get(i).image);
        }
        //活动图的集合
        List<HomeBean.ActivityInfo.ActivityInfoList> activityInfoList = mt.data.activityInfo.activityInfoList;
        for (int i = 0; i <activityInfoList.size() ; i++) {
            list_huodong.add(activityInfoList.get(i).activityImg);
        }
        initBanner();
    }
}
