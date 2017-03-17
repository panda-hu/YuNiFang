package guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.test.R;

/**
 * 姓名:胡文帅
 * 时间:2017/3/16
 * 邮箱：
 * 备注：
 */

public class Guide2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getActivity(), R.layout.guide2,null);
    }
}
