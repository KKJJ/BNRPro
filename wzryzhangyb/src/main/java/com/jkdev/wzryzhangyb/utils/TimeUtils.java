package com.jkdev.wzryzhangyb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by KJ on 2017/3/19.
 */

public class TimeUtils {

    //    public static long oneHourMillis = 60 * 60 * 1000; // 一小时的毫秒数
    private static long oneMinuteMillis = 60; // 一分钟的秒数
    private static long oneHourMillis = 60 * oneMinuteMillis; // 一小时的秒数
    private static long oneDayMillis = 24 * oneHourMillis; // 一天的秒数
    private static long oneYearMillis = 365 * oneDayMillis; // 一年的秒数

    /**
     * 根据毫秒数 得到距离现在间隔的格式字符串
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
                insStr = getFormatDate(times);
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

    private static String getFormatDate(long times) {
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
        return format.format(new Date(millis * 1000)); // 秒转成毫秒再计算
    }

    /**
     * 传入秒级时间戳 得到指定格式的时间字符串
     */
    public static String millisToStringDate(long millis) {
        return TimeUtils.millisToStringDate(millis, "yyyy-MM-dd HH:mm");
    }

    /**
     * 传入秒级时间戳 得到指定格式的时间字符串
     */
    public static String millisToStringDate(String millis) {
        return TimeUtils.millisToStringDate(Integer.parseInt(millis), "yyyy-MM-dd HH:mm");
    }


}
