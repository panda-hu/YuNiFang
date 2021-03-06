package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bwei.test.GoodsActivity;
import com.bwei.test.GoodsDetailsActivity;
import com.bwei.test.R;
import com.bwei.test.WebViewActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;
import adapter.gridView.MyGridViewAdapter;
import adapter.recycler.RvAdapter;
import adapter.recycler.RvDefaultAdapter;
import adapter.recycler.RvSubjectAdapter;
import bean.HomeBean;
import holder.RvSubjectHolder;
import interFace.OnRecyclerViewItemClickListener;
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

public class FragmentHome extends Fragment implements View.OnClickListener, YuNiFangData<HomeBean> {

    private View view;
    private List<String> list_lunbo_image;
    private Banner banner;
    private PullToRefreshScrollView ptr_scrollview;
    private RelativeLayout r11;
    private RelativeLayout r22;
    private RelativeLayout r33;
    private RelativeLayout r44;
    private ImageView iv_meiri;
    private ImageView iv_jifen;
    private ImageView iv_duihuan;
    private ImageView iv_zhenwei;
    private Banner banner_huodong;
    private List<String> list_huodong;
    private RecyclerView recyclerview;
    private RecyclerView recyclerview_gv;
    private TextView tv_all_shangpin;
    private List<HomeBean.BestSellers.GoodsList> mgoodsList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                //得到bean
                HomeBean homebean = (HomeBean) msg.obj;
                //得到数据
                List<HomeBean.Ad1> ad1 = homebean.data.ad1;
                for (int i = 0; i < ad1.size(); i++) {
                    list_lunbo_image.add(ad1.get(i).image);
                }
                //调用方法
                initBanner();
            } else if (msg.what == 1) {
                HomeBean homebean = (HomeBean) msg.obj;
                List<HomeBean.ActivityInfo.ActivityInfoList> activityInfoList = homebean.data.activityInfo.activityInfoList;
                for (int i = 0; i < activityInfoList.size(); i++) {
                    list_huodong.add(activityInfoList.get(i).activityImg);
                }
                initBanner1();
            } else if (msg.what == 2) {
                HomeBean homebean = (HomeBean) msg.obj;
                recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                List<HomeBean.BestSellers> bestSellers = homebean.data.bestSellers;
                //得到数据
                mgoodsList = bestSellers.get(0).goodsList;
                RvAdapter rvadapter = new RvAdapter(getActivity(), mgoodsList);
                rvadapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        final Context context = getActivity().getApplicationContext();
                        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", mgoodsList.get(position).id);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                });
                recyclerview.setAdapter(rvadapter);
            } else if(msg.what==3){
                HomeBean homebean = (HomeBean) msg.obj;
                recyclerview_zhuanti.setLayoutManager(new LinearLayoutManager(getActivity()));
                List<HomeBean.Subjects> subjects = homebean.data.subjects;
                RvSubjectAdapter rvsubjectadapter=new RvSubjectAdapter(getActivity(),subjects);
                recyclerview_zhuanti.setAdapter(rvsubjectadapter);

            } else if (msg.what == 4) {
                HomeBean homebean = (HomeBean) msg.obj;
                recyclerview_gv.setLayoutManager(new GridLayoutManager(getActivity(),2));
                final List<HomeBean.DefaultGoodsList> mdefaultGoodsList = homebean.data.defaultGoodsList;
                RvDefaultAdapter rvdefaultadapter=new RvDefaultAdapter(getActivity(),mdefaultGoodsList);
                rvdefaultadapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        final Context context = getActivity().getApplicationContext();
                        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", mdefaultGoodsList.get(position).id);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                recyclerview_gv.setAdapter(rvdefaultadapter);
            }
        }
    };
    private RecyclerView recyclerview_zhuanti;

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
        ptr_scrollview.getLoadingLayoutProxy().setRefreshingLabel("正在加载...");
        //只支持下拉
        ptr_scrollview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ptr_scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
        //请求数据
        NetWorkUtils.getStr(MyUrl.url_home, HomeBean.class, this);
        initData();
    }

    //下拉刷新时间延迟
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            ptr_scrollview.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    //轮播第三方实例化方法
    private void initBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        banner.setImages(list_lunbo_image);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    //轮播第三方实例化方法
    private void initBanner1() {
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
        r11 = (RelativeLayout) view.findViewById(R.id.r1);
        r22 = (RelativeLayout) view.findViewById(R.id.r2);
        r33 = (RelativeLayout) view.findViewById(R.id.r3);
        r44 = (RelativeLayout) view.findViewById(R.id.r4);
        iv_meiri = (ImageView) view.findViewById(R.id.iv_meiri);
        iv_jifen = (ImageView) view.findViewById(R.id.iv_jifen);
        iv_duihuan = (ImageView) view.findViewById(R.id.iv_duihuan);
        iv_zhenwei = (ImageView) view.findViewById(R.id.iv_zhenwei);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerview_gv = (RecyclerView) view.findViewById(R.id.recyclerview_gv);
        recyclerview_zhuanti = (RecyclerView) view.findViewById(R.id.recyclerview_zhuanti);
        tv_all_shangpin = (TextView) view.findViewById(R.id.tv_all_shangpin);
        initClick();
    }

    private void initClick() {
        r11.setOnClickListener(this);
        r22.setOnClickListener(this);
        r33.setOnClickListener(this);
        r44.setOnClickListener(this);
        tv_all_shangpin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.tv_all_shangpin:
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                startActivity(intent);
                break;
        }
    }

    //跳转方法
    public void intentWV(String url) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void ynfdataSuccer(HomeBean mt) {
        //轮播图
        Mes1(mt);
        //优惠活动
        Mes2(mt);
        //本周热销
        Mes3(mt);
        //热门专题
        Mes4(mt);
        //默认双列表样式的数据
        Mes5(mt);
    }

    private void Mes1(HomeBean hb) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = hb;
        handler.sendMessage(obtain);
    }

    private void Mes2(HomeBean hb) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = hb;
        handler.sendMessage(obtain);
    }

    private void Mes3(HomeBean hb) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = hb;
        handler.sendMessage(obtain);
    }

    private void Mes4(HomeBean hb) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = hb;
        handler.sendMessage(obtain);
    }
    private void Mes5(HomeBean hb) {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.obj = hb;
        handler.sendMessage(obtain);
    }
}
