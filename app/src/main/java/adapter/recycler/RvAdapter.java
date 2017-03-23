package adapter.recycler;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwei.test.R;

import java.util.List;

import bean.HomeBean;
import holder.RvHolder;
import interFace.OnRecyclerViewItemClickListener;

/**
 * 姓名:胡文帅
 * 时间:2017/3/21
 * 邮箱：
 * 备注：首页热销商品RecyclerView所用adapter
 */

public class RvAdapter extends RecyclerView.Adapter<RvHolder>{


    private final Context context;
    private final List<HomeBean.BestSellers.GoodsList> goodsList;
    private OnRecyclerViewItemClickListener onItemClickListener;

    public RvAdapter(Context context, List<HomeBean.BestSellers.GoodsList> goodsList) {
        this.context=context;
        this.goodsList=goodsList;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.goods_item,parent,false);
        RvHolder rvHolder=new RvHolder(view);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(RvHolder holder, final int position) {
        Glide.with(context).load(goodsList.get(position).goods_img).into(holder.item_goods_image);
        holder.item_goods_info.setText(goodsList.get(position).goods_name);
        holder.item_goods_price.setText("¥ "+goodsList.get(position).shop_price);
        holder.item_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.item_goods_old_price.setText("¥ "+goodsList.get(position).market_price);
        holder.item_goods_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return goodsList.size();
    }
}
