package com.jkdev.wzryzhangyb;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.jkdev.wzryzhangyb.ui.fragment.first.FirstFragment;
import com.jkdev.wzryzhangyb.ui.fragment.five.FiveFragment;
import com.jkdev.wzryzhangyb.ui.fragment.forth.FourthFragment;
import com.jkdev.wzryzhangyb.ui.fragment.second.SecondFragment;
import com.jkdev.wzryzhangyb.ui.fragment.third.ThirdFragment;
import com.jkdev.wzryzhangyb.ui.view.BottomBar;
import com.jkdev.wzryzhangyb.ui.view.BottomBarTab;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends SupportActivity {

    private static final String TAG = "--MainActivity";

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;
    private SupportFragment[] mFragments = new SupportFragment[5];

    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置只能竖屏
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mFragments[FIRST] = FirstFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = ThirdFragment.newInstance();
            mFragments[FOURTH] = FourthFragment.newInstance();
            mFragments[FIVE] = FiveFragment.newInstance();

            loadMultipleRootFragment(R.id.main_content, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIVE]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(FirstFragment.class);
            mFragments[SECOND] = findFragment(SecondFragment.class);
            mFragments[THIRD] = findFragment(ThirdFragment.class);
            mFragments[FOURTH] = findFragment(FourthFragment.class);
            mFragments[FIVE] = findFragment(FiveFragment.class);
        }

        initView();
    }

    private void initView() {

        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        mBottomBar
                .addItem(new BottomBarTab(this, R.drawable.home_recommend_selector, getString(R.string.title_home_recommend)))
                .addItem(new BottomBarTab(this, R.drawable.home_find_selector, getString(R.string.title_home_find)))
                .addItem(new BottomBarTab(this, R.drawable.home_square_selector, getString(R.string.title_home_square)))
                .addItem(new BottomBarTab(this, R.drawable.home_message_selector, getString(R.string.title_home_message)))
                .addItem(new BottomBarTab(this, R.drawable.home_personal_selector, getString(R.string.title_home_personal)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

}
