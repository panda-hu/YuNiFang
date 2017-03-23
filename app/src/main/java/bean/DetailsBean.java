package bean;

import java.util.List;

/**
 * 姓名:胡文帅
 * 时间:2017/3/22
 * 邮箱：
 * 备注：
 */

public class DetailsBean {
    public DataBean data;
    public class DataBean{
        public List<Activity> activity;
        public Goods goods;
    }
    public class Activity{
        public String description;
        public String title;
    }

    public class Goods{
        //商品图片
        public String goods_img;
        //商品名字
        public String goods_name;
        //运费
        public String shipping_fee;
        //原价
        public String market_price;
        //现价
        public String shop_price;
        //库存
        public String stock_number;
        //销量
        public String sales_volume;
    }
}
