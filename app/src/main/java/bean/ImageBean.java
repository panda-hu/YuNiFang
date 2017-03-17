package bean;

import java.util.List;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class ImageBean {

    public  Data data;

    public class Data{
        public List<Ad1> ad1;
        public List<Ad5> ad5;
    }

    public class Ad1{
        public String image;
    }
    public class Ad5{
        public String title;
        public String image;
        public String ad_type_dynamic_data;
    }
}
