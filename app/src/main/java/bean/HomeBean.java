package bean;

import java.util.List;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class HomeBean {

    //最外层类对象
    public  Data data;

    public class Data{
        //
        public ActivityInfo activityInfo;
        public List<Ad1> ad1;
        public List<Ad5> ad5;
        public List<BestSellers> bestSellers;
    }
    public class ActivityInfo{
        public List<ActivityInfoList> activityInfoList;
        public class ActivityInfoList{
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
            public String arket_price;
        }
    }
}
