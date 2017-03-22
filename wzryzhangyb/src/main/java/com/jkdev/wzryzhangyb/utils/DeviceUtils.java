package com.jkdev.wzryzhangyb.utils;

import com.jkdev.wzryzhangyb.MyApplication;

/**
 * Created by Kuang on 2017/3/21.
 */

public class DeviceUtils {

    public static int px2dip(float pxValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(float dipValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
