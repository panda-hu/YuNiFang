package utils;

import android.util.Log;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import interFace.YuNiFangData;

/**
 * 姓名:胡文帅
 * 时间:2017/3/19
 * 邮箱：
 * 备注：请求数据的工具类
 */

public class NetWorkUtils {

    //xutils请求数据的简单封装
    public static <T>void getStr(String url, final Class<T> mclass, final YuNiFangData yunifangdata){
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                T t = gson.fromJson(result, mclass);
                yunifangdata.ynfdataSuccer(t);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //volley请求数据的简单封装
    public static void getVolleyStr(){

    }



}
