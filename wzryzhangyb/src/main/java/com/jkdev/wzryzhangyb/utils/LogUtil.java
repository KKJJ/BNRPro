package com.jkdev.wzryzhangyb.utils;

import android.util.Log;

/**
 * 日志打印类:
 */
public class LogUtil {

    public static final int VWEBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;

    public static int level = VWEBOSE;

    public static void v(String tag, String msg) {
        if (level <= VWEBOSE) {
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

}
