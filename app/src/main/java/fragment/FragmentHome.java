package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.test.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import utils.GlideImageLoader;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：主页
 */

public class FragmentHome extends Fragment {

    private View view;
    private List<String> list;
    private Banner banner;
    private PullToRefreshScrollView ptr_scrollview;

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
    }

    private void initView() {
        //ptr_scrollview = (PullToRefreshScrollView) view.findViewById(R.id.ptr_scrollview);
        banner = (Banner) view.findViewById(R.id.banner);
    }
}
