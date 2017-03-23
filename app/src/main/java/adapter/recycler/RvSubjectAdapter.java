package adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.test.GoodsDetailsActivity;
import com.bwei.test.R;

import java.util.List;

import bean.HomeBean;
import holder.RvSubjectHolder;
import holder.RvSubjectHolder2;
import interFace.OnRecyclerViewItemClickListener;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：首页专题商品RecyclerView所用adapter
 */

public class RvSubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<HomeBean.Subjects> subjects;
    private final Context context;
    private static final int ONE_TYPE = 0;
    private static final int TWO_TYPE = 1;
    private int type = ONE_TYPE;

    public RvSubjectAdapter(Context context, List<HomeBean.Subjects> subjects) {
        this.context=context;
        this.subjects=subjects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        switch (viewType){
            case 0:
                View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_zhuanti_item1,parent,false);
                holder=new RvSubjectHolder(view);
                break;

            case 1:
                View view2= LayoutInflater.from(context).inflate(R.layout.recyclerview_zhuanti_item2,parent,false);
                holder=new RvSubjectHolder2(view2);
                break;
        }
        return holder;
    }

    /**
     * 判断当前条目类型
     */
    @Override
    public int getItemViewType(int position) {

        int mposition=position%2;
        switch (mposition) {
            case 0:
                type = ONE_TYPE;
                break;
            case 1:
                type = TWO_TYPE;
                break;

        }
        return type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //获取条目类型
        int itemViewType = getItemViewType(position);

        switch (itemViewType){
            case 0:
                RvSubjectHolder holder1 = (RvSubjectHolder) holder;
                Glide.with(context).load(subjects.get(position/2).image).into(holder1.zhuanti_item_iv);
                break;

            case 1:
                RvSubjectHolder2 holder2= (RvSubjectHolder2) holder;
                holder2.zhuanti_item_recyclerview.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                final List<HomeBean.Subjects.GoodsList> goodsList = subjects.get(position/2).goodsList;
                RvSubjectAdapter2 rvsubjectAdapter2=new RvSubjectAdapter2(context,goodsList);
                rvsubjectAdapter2.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(context, "点击了" + position, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, GoodsDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", goodsList.get(position).id);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });
                holder2.zhuanti_item_recyclerview.setAdapter(rvsubjectAdapter2);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return (subjects.size())*2;
    }
}
