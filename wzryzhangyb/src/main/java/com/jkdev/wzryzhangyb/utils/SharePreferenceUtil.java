package com.jkdev.wzryzhangyb.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.jkdev.wzryzhangyb.MyApplication;
import com.jkdev.wzryzhangyb.constant.Constants;


/**
 * SharePreference工具类.
 * <p/>
 * Created by Kuang on 2016/7/18.
 */
@SuppressLint("CommitPrefEdits")
public class SharePreferenceUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String name = "CACHE_DATA";

    private static SharePreferenceUtil sharePreferenceUtil;

    public SharePreferenceUtil() {
        sp = MyApplication.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SharePreferenceUtil getInstance() {
        if (sharePreferenceUtil == null) {
            return new SharePreferenceUtil();
        }
        return sharePreferenceUtil;
    }

    /**
     * 轮播图缓存
     *
     * @return
     */
    public String getAdListData() {
        return sp.getString(Constants.AD_LIST_DATA, "");
    }

    public void setAdListData(String data) {
        editor.putString(Constants.AD_LIST_DATA, data);
        editor.commit();
    }

    /**
     * 首页列表数据缓存
     *
     * @return
     */
    public String getRecommendListData() {
        return sp.getString(Constants.RECOMMEND_LIST_DATA, "");
    }

    public void setRecommendListData(String data) {
        editor.putString(Constants.RECOMMEND_LIST_DATA, data);
        editor.commit();
    }

    /**
     * 首页其他标签 列表数据缓存
     *
     * @return
     */
    public String getTagListData(String tag) {
        return sp.getString(tag, "");
    }

    public void setTagListData(String tag, String data) {
        editor.putString(tag, data);
        editor.commit();
    }

}
