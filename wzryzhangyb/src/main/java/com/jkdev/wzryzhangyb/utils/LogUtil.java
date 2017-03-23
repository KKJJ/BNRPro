package com.jkdev.wzryzhangyb.utils;

import android.util.Log;

/**
 * 日志打印类:
 */
public class LogUtil {

    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    public static int level = VERBOSE;

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            if (msg == null || tag == null)
                return;
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            if (msg == null || tag == null)
                return;
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            if (msg == null || tag == null)
                return;
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            if (msg == null || tag == null)
                return;
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            if (msg == null || tag == null)
                return;
            Log.e(tag, msg);
        }
    }

    /**
     * 截断输出日志,针对较长日志
     *
     * @param msg
     */
    public static void eLong(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }

}
