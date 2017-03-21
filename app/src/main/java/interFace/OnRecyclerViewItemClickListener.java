package interFace;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：RecyclerView点击事件接口
 */

public interface OnRecyclerViewItemClickListener {

    //接口 抽象方法
    public void onItemClick(int position);
    public void onItemLongClick(int position);
}
