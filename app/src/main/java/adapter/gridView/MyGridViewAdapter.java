package adapter.gridView;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.test.R;

import java.util.List;

import bean.HomeBean;

/**
 * 姓名:胡文帅
 * 时间:2017/3/23
 * 邮箱：
 * 备注：
 */

public class MyGridViewAdapter{

//    private final Context context;
//    private final List<HomeBean.DefaultGoodsList> defaultGoodsList;
//
//    public MyGridViewAdapter(Context context, List<HomeBean.DefaultGoodsList> defaultGoodsList) {
//        this.context=context;
//        this.defaultGoodsList=defaultGoodsList;
//    }
//
//    @Override
//    public int getCount() {
//        return defaultGoodsList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return defaultGoodsList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        viewHolder holder;
//        if(convertView==null){
//            holder=new viewHolder();
//            convertView=convertView.inflate(context, R.layout.goods_item,null);
//            holder.iitem_goods_image= (ImageView) convertView.findViewById(R.id.item_goods_image);
//            holder.item_goods_info= (TextView) convertView.findViewById(R.id.item_goods_info);
//            holder.item_goods_price= (TextView) convertView.findViewById(R.id.item_goods_price);
//            holder.item_goods_old_price= (TextView) convertView.findViewById(R.id.item_goods_old_price);
//            convertView.setTag(holder);
//        }
//        else{
//            holder= (viewHolder) convertView.getTag();
//        }
//        Glide.with(context).load(defaultGoodsList.get(position).goods_img).into(holder.iitem_goods_image);
//        holder.item_goods_info.setText(defaultGoodsList.get(position).goods_name);
//        holder.item_goods_price.setText("¥ "+defaultGoodsList.get(position).shop_price);
//        holder.item_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.item_goods_old_price.setText("¥ "+defaultGoodsList.get(position).market_price);
//        return convertView;
//    }
//
//    class viewHolder{
//        ImageView iitem_goods_image;
//        TextView item_goods_info;
//        TextView item_goods_price;
//        TextView item_goods_old_price;
//    }
}
