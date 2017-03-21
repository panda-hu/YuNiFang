package holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.test.R;

/**
 * 姓名:胡文帅
 * 时间:2017/3/21
 * 邮箱：
 * 备注：
 */

public class RvHolder extends RecyclerView.ViewHolder{

    public final ImageView item_goods_image;
    public final TextView item_goods_info;
    public final TextView item_goods_price;
    public final TextView item_goods_old_price;

    public RvHolder(View itemView) {
        super(itemView);
        item_goods_image = (ImageView) itemView.findViewById(R.id.item_goods_image);
        item_goods_info = (TextView) itemView.findViewById(R.id.item_goods_info);
        item_goods_price = (TextView) itemView.findViewById(R.id.item_goods_price);
        item_goods_old_price = (TextView) itemView.findViewById(R.id.item_goods_old_price);
    }
}
