package fragment;

import android.content.Intent;
import android.os.Bundle;
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

import utils.GlideImageLoader;
import utils.TestNet;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：主页
 */

public class FragmentHome extends Fragment implements View.OnClickListener{

    private View view;
    private List<String> list;
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
    private ImageView iv_youhui;

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
        /*ptr_scrollview.getLoadingLayoutProxy().setLastUpdatedLabel("时间:");
        ptr_scrollview.getLoadingLayoutProxy().setPullLabel("下拉刷新");
        ptr_scrollview.getLoadingLayoutProxy().setReleaseLabel("松开加载");
        ptr_scrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);*/
        initData();
        initBanner();

    }

    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        banner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("https://image.yunifang.com/yunifang/images/goods/ad0/17031517406303974036478057.jpg");
        list.add("https://image.yunifang.com/yunifang/images/goods/ad0/1703151603387814865615044.jpg");
        list.add("https://image.yunifang.com/yunifang/images/goods/ad0/1701190959809077048917947.jpg");
        list.add("https://image.yunifang.com/yunifang/images/goods/ad0/170301165920612393251158072.jpg");
        list.add("http://image.hmeili.com/yunifang/images/goods/ad0/170111173213619823650768274.jpg");
        list.add("http://image.hmeili.com/yunifang/images/goods/ad0/16122110567474029873241894.jpg");
        list.add("http://image.hmeili.com/yunifang/images/goods/ad0/16101517113385857065462262.jpg");
        list.add("http://image.hmeili.com/yunifang/images/goods/ad0/16090611503412651428962103.jpg");
        list.add("https://image.yunifang.com/yunifang/images/goods/ad0/17030317572876071477102023.jpg");
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160823172997710201253418883.png").into(iv_meiri);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120383916524110935835.png").into(iv_jifen);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120326416505640517284.png").into(iv_duihuan);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/goods/ad0/160623120430916487170096321.png").into(iv_zhenwei);
        Glide.with(getActivity()).load("http://image.hmeili.com/yunifang/images/lottery/142/lottery_img/1607022220068457990753273.png").into(iv_youhui);
    }

    private void initView() {
        //ptr_scrollview = (PullToRefreshScrollView) view.findViewById(R.id.ptr_scrollview);
        banner = (Banner) view.findViewById(R.id.banner);
        r1 = (RelativeLayout) view.findViewById(R.id.r1);
        r2 = (RelativeLayout) view.findViewById(R.id.r2);
        r3 = (RelativeLayout) view.findViewById(R.id.r3);
        r4 = (RelativeLayout) view.findViewById(R.id.r4);
        iv_meiri = (ImageView) view.findViewById(R.id.iv_meiri);
        iv_jifen = (ImageView) view.findViewById(R.id.iv_jifen);
        iv_duihuan = (ImageView) view.findViewById(R.id.iv_duihuan);
        iv_zhenwei = (ImageView) view.findViewById(R.id.iv_zhenwei);
        iv_youhui = (ImageView) view.findViewById(R.id.iv_youhui);
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
}
