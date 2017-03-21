package adapter.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import guide.Guide1;
import guide.Guide2;
import guide.Guide3;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class GuideAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> list;
    public GuideAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
//    public GuideAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        Fragment fragment=null;
//        switch (position){
//            case 0:
//                fragment=new Guide1();
//                break;
//            case 1:
//                fragment=new Guide2();
//                break;
//            case 2:
//                fragment=new Guide3();
//                break;
//        }
//        return fragment;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }
}
