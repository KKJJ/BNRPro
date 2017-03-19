package com.jkdev.wzryzhangyb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KJ on 2017/3/19.
 */

public class TimeUtils {

    //    public static long oneHourMillis = 60 * 60 * 1000; // 一小时的毫秒数
    public static long oneMinuteMillis = 60; // 一分钟的秒数
    public static long oneHourMillis = 60 * oneMinuteMillis; // 一小时的秒数
    public static long oneDayMillis = 24 * oneHourMillis; // 一天的秒数
    public static long oneYearMillis = 365 * oneDayMillis; // 一年的秒数

    /**
     * 根据毫秒数
     *
     * @param times
     * @return
     */
    public static String getInstanceWithNow(long times) {
        String insStr = new String();
        long gap = System.currentTimeMillis() / 1000 - times;
        if (gap > oneDayMillis) {
            int days = (int) (gap / oneDayMillis);
            if (days > 3) {
                insStr = getFormatData(times);
            } else {
                insStr = days + "天前";
            }
        } else if (gap > oneHourMillis) {
            int hours = (int) (gap / oneHourMillis);
            insStr = hours + "小时前";
        } else {
            int minutes = (int) (gap / oneMinuteMillis);
            insStr = minutes + "分钟前";
        }
        return insStr;
    }

    private static String getFormatData(long times) {
        Date date = new Date(times * 1000); // 秒转成毫秒再计算
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * @param millis  要转化的日期毫秒数。
     * @param pattern 要转化为的字符串格式（如：yyyy-MM-dd HH:mm:ss）。
     * @return 返回日期字符串。
     */
    public static String millisToStringDate(long millis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern,
                Locale.getDefault());
        return format.format(new Date(millis));
    }

}
