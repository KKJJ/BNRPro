package com.jkdev.wzryzhangyb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import me.yokeyword.fragmentation.SupportActivity;

public class WelcomeActivity extends SupportActivity {

    private static final int GOTO_MAIN_ACTIVITY = 0;
    private static final int DELAY_MILLIS = 2000;
    ;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);  //右进左出动画
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, DELAY_MILLIS);
    }

}
