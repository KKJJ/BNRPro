package com.jkdev.wzryzhangyb;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.ui.fragment.MainFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


public class MainActivity extends SupportActivity {

    private static final String TAG = "--MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置只能竖屏
        setContentView(R.layout.activity_main);

//        StatusBarUtil.setColor(this, R.color.actionbar_bg_color);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_main_container, MainFragment.newInstance());
        }
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator(R.anim.anim_right_in, R.anim.alpha_scale_exit);
    }

}
