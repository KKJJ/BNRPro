package com.jkdev.wzryzhangyb;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.jkdev.wzryzhangyb.first.FirstFragment;
import com.jkdev.wzryzhangyb.second.SecondFragment;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    private static final String TAG = "--MainActivity";

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!item.isChecked()) {
                        replaceLoadRootFragment(R.id.content, FirstFragment.newInstance(), false);
                        mTextMessage.setText(R.string.title_home);
                        LogUtil.e(TAG, "onNavigationItemSelected: " + R.string.title_home);
                    }

                    return true;
                case R.id.navigation_dashboard:
                    if (!item.isChecked()) {
                        replaceLoadRootFragment(R.id.content, SecondFragment.newInstance(), false);
                        mTextMessage.setText(R.string.title_dashboard);
                        LogUtil.e(TAG, "onNavigationItemSelected: " + R.string.title_dashboard);
                    }
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置只能竖屏

        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadRootFragment(R.id.content, FirstFragment.newInstance());
    }

}
