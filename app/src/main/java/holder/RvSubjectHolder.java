package holder;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.test.R;

import java.util.List;

import bean.HomeBean;

/**
 * 姓名:胡文帅
 * 时间:2017/3/21
 * 邮箱：
 * 备注：首页专题商品RecyclerView所用holder
 */

public class RvSubjectHolder extends RecyclerView.ViewHolder{


    public final ImageView zhuanti_item_iv;

    public RvSubjectHolder(View itemView) {
        super(itemView);
        zhuanti_item_iv = (ImageView) itemView.findViewById(R.id.zhuanti_item_iv);
    }


}
