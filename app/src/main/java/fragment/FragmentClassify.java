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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bwei.test.GoodsActivity;
import com.bwei.test.GoodsDetailsActivity;
import com.bwei.test.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.List;

import adapter.recycler.RvFenLeiAdapter;
import bean.FenLeiBean;
import bean.HomeBean;
import interFace.OnRecyclerViewItemClickListener;
import interFace.YuNiFangData;
import utils.MyUrl;
import utils.NetWorkUtils;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：分类页
 */

public class FragmentClassify extends Fragment implements View.OnClickListener , YuNiFangData<FenLeiBean>{

    private View view;
    private ImageView iv_classify_mianmo;
    private ImageView iv_classify_runfushi;
    private ImageView iv_classify_runfuru;
    private ImageView iv_classify_jiemianru;
    private ImageView iv_classify_qita;
    private ImageView iv_classify_shihui;
    private ImageView iv_classify_gx_bushui;
    private ImageView iv_classify_gx_shuhuan;
    private ImageView iv_classify_gx_kongyou;
    private ImageView iv_classify_gx_meibai;
    private ImageView iv_classify_gx_jinzhi;
    private ImageView iv_classify_fz_hunhe;
    private ImageView iv_classify_fz_zhongxing;
    private ImageView iv_classify_fz_ganxing;
    private ImageView iv_classify_fz_youxing;
    private ImageView iv_classify_fz_doudou;
    private ImageView iv_classify_fz_mingan;
    private RecyclerView recyclerview_gv_fenlei;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                FenLeiBean fenleibean = (FenLeiBean) msg.obj;
                final List<FenLeiBean.DataBean.GoodsBriefBean> goodsBrief = fenleibean.getData().getGoodsBrief();
                recyclerview_gv_fenlei.setLayoutManager(new GridLayoutManager(getActivity(),2));
                RvFenLeiAdapter rvfenleiadapter=new RvFenLeiAdapter(getActivity(),goodsBrief);
                rvfenleiadapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        final Context context = getActivity().getApplicationContext();
                        Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", goodsBrief.get(position).getId());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                });
                recyclerview_gv_fenlei.setAdapter(rvfenleiadapter);
            }
        }
    };
    private PullToRefreshScrollView ptr_classify;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_classify, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        ptr_classify.getLoadingLayoutProxy().setPullLabel("下拉刷新...");
        ptr_classify.getLoadingLayoutProxy().setReleaseLabel("松开加载...");
        ptr_classify.getLoadingLayoutProxy().setRefreshingLabel("正在加载...");
        //只支持下拉
        ptr_classify.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        ptr_classify.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
        NetWorkUtils.getStr(MyUrl.url_fenlei,FenLeiBean.class,this);
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
            ptr_classify.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    private void initView() {
        ptr_classify = (PullToRefreshScrollView) view.findViewById(R.id.ptr_classify);
        iv_classify_mianmo = (ImageView) view.findViewById(R.id.iv_classify_mianmo);
        iv_classify_runfushi = (ImageView) view.findViewById(R.id.iv_classify_runfushi);
        iv_classify_runfuru = (ImageView) view.findViewById(R.id.iv_classify_runfuru);
        iv_classify_jiemianru = (ImageView) view.findViewById(R.id.iv_classify_jiemianru);
        iv_classify_qita = (ImageView) view.findViewById(R.id.iv_classify_qita);
        iv_classify_shihui = (ImageView) view.findViewById(R.id.iv_classify_shihui);
        iv_classify_gx_bushui = (ImageView) view.findViewById(R.id.iv_classify_gx1);
        iv_classify_gx_shuhuan = (ImageView) view.findViewById(R.id.iv_classify_gx2);
        iv_classify_gx_kongyou = (ImageView) view.findViewById(R.id.iv_classify_gx3);
        iv_classify_gx_meibai = (ImageView) view.findViewById(R.id.iv_classify_gx4);
        iv_classify_gx_jinzhi = (ImageView) view.findViewById(R.id.iv_classify_gx5);
        iv_classify_fz_hunhe = (ImageView) view.findViewById(R.id.iv_classify_fz1);
        iv_classify_fz_zhongxing = (ImageView) view.findViewById(R.id.iv_classify_fz2);
        iv_classify_fz_ganxing = (ImageView) view.findViewById(R.id.iv_classify_fz3);
        iv_classify_fz_youxing = (ImageView) view.findViewById(R.id.iv_classify_fz4);
        iv_classify_fz_doudou = (ImageView) view.findViewById(R.id.iv_classify_fz5);
        iv_classify_fz_mingan = (ImageView) view.findViewById(R.id.iv_classify_fz6);
        recyclerview_gv_fenlei = (RecyclerView) view.findViewById(R.id.recyclerview_gv_fenlei);

        initClick();
    }

    private void initClick() {
        iv_classify_mianmo.setOnClickListener(this);
        iv_classify_runfushi.setOnClickListener(this);
        iv_classify_runfuru.setOnClickListener(this);
        iv_classify_jiemianru.setOnClickListener(this);
        iv_classify_qita.setOnClickListener(this);
        iv_classify_shihui.setOnClickListener(this);
        iv_classify_gx_bushui.setOnClickListener(this);
        iv_classify_gx_shuhuan.setOnClickListener(this);
        iv_classify_gx_kongyou.setOnClickListener(this);
        iv_classify_gx_meibai.setOnClickListener(this);
        iv_classify_gx_jinzhi.setOnClickListener(this);
        iv_classify_fz_hunhe.setOnClickListener(this);
        iv_classify_fz_zhongxing.setOnClickListener(this);
        iv_classify_fz_ganxing.setOnClickListener(this);
        iv_classify_fz_youxing.setOnClickListener(this);
        iv_classify_fz_doudou.setOnClickListener(this);
        iv_classify_fz_mingan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_classify_mianmo:
                initIntent("38","面膜","1");
                break;

            case R.id.iv_classify_runfushi:
                initIntent("39","润肤水","1");
                break;

            case R.id.iv_classify_runfuru:
                initIntent("40","润肤乳","1");
                break;

            case R.id.iv_classify_jiemianru:
                initIntent("24","洁面乳","1");
                break;

            case R.id.iv_classify_qita:
                initIntent("35","其他","1");
                break;

            case R.id.iv_classify_shihui:
                initIntent("33","实惠套装","1");
                break;

            case R.id.iv_classify_gx1:
                initIntent("17","按功效","2");
                break;

            case R.id.iv_classify_gx2:
                initIntent("31","按功效","2");
                break;

            case R.id.iv_classify_gx3:
                initIntent("19","按功效","2");
                break;

            case R.id.iv_classify_gx4:
                initIntent("18","按功效","2");
                break;

            case R.id.iv_classify_gx5:
                initIntent("20","按功效","2");
                break;

            case R.id.iv_classify_fz1:
                initIntent("14","按肤质","3");
                break;

            case R.id.iv_classify_fz2:
                initIntent("13","按肤质","3");
                break;

            case R.id.iv_classify_fz3:
                initIntent("29","按肤质","3");
                break;

            case R.id.iv_classify_fz4:
                initIntent("28","按肤质","3");
                break;

            case R.id.iv_classify_fz5:
                initIntent("15","按肤质","3");
                break;

            case R.id.iv_classify_fz6:
                initIntent("37","按肤质","3");
                break;

        }
    }

    private void initIntent(String id, String title, String is_leaf) {
        Intent intent = new Intent(getActivity(), GoodsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("title", title);
        bundle.putString("is_leaf", is_leaf);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void ynfdataSuccer(FenLeiBean mt) {
        Mes1(mt);
    }
    private void Mes1(FenLeiBean fb) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = fb;
        handler.sendMessage(obtain);
    }
}
