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
import holder.RvDefaultHolder;
import holder.RvHolder;
import interFace.OnRecyclerViewItemClickListener;

/**
 * 姓名:胡文帅
 * 时间:2017/3/21
 * 邮箱：
 * 备注：首页下方默认商品RecyclerView所用adapter
 */

public class RvDefaultAdapter extends RecyclerView.Adapter<RvDefaultHolder>{


    private final Context context;
    private final List<HomeBean.DefaultGoodsList> mdefaultgoodslist;
    private OnRecyclerViewItemClickListener onItemClickListener;

    public RvDefaultAdapter(Context context, List<HomeBean.DefaultGoodsList> mdefaultgoodslist) {
        this.context=context;
        this.mdefaultgoodslist=mdefaultgoodslist;

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public RvDefaultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.goods_gv_item,parent,false);
        RvDefaultHolder rvdefaultHolder=new RvDefaultHolder(view);
        return rvdefaultHolder;
    }

    @Override
    public void onBindViewHolder(RvDefaultHolder holder, final int position) {
        Glide.with(context).load(mdefaultgoodslist.get(position).goods_img).into(holder.item_gv_goods_image);
        holder.item_gv_goods_info.setText(mdefaultgoodslist.get(position).goods_name);
        holder.item_gv_goods_price.setText("¥ "+mdefaultgoodslist.get(position).shop_price);
        holder.item_gv_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.item_gv_goods_old_price.setText("¥ "+mdefaultgoodslist.get(position).market_price);
        holder.item_gv_goods_layout.setOnClickListener(new View.OnClickListener() {
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
        return mdefaultgoodslist.size();
    }
}
