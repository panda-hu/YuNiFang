package bean;

import java.util.List;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：home页解析数据所用bean类
 */

public class HomeBean {

    //最外层类对象
    public  Data data;

    public class Data{
        //对象里的数据（对象,数组）
        public ActivityInfo activityInfo;
        public List<Ad1> ad1;
        public List<Ad5> ad5;
        public List<BestSellers> bestSellers;
        public List<DefaultGoodsList> defaultGoodsList;
        public List<Subjects> subjects;
    }

    public class ActivityInfo{
        //类里的数组
        public List<ActivityInfoList> activityInfoList;
        public class ActivityInfoList{
            //数组中对象的属性
            public String activityImg;
        }
    }

    public class Ad1{
        public String image;
    }

    public class Ad5{
        public String title;
        public String image;
        public String ad_type_dynamic_data;
    }

    public class BestSellers{
        public List<GoodsList> goodsList;
        public class GoodsList{
            public String goods_img;
            public String goods_name;
            public String shop_price;
            public String market_price;
        }
    }

    public class DefaultGoodsList{
        public String goods_img;
        public String goods_name;
        public String shop_price;
        public String market_price;
    }

    public class Subjects{
        public String image;
        public List<GoodsList> goodsList;
        public class GoodsList{
            public String goods_img;
            public String goods_name;
            public String shop_price;
            public String market_price;
        }
    }
}
