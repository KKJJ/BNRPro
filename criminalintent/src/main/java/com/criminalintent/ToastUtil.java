package com.criminalintent;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Kuang on 2017/2/20.
 */

public class ToastUtil {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
