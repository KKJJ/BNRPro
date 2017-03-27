package com.jkdev.wzryzhangyb.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.event.StartBrotherEvent;
import com.jkdev.wzryzhangyb.ui.fragment.first.FirstFragment;
import com.jkdev.wzryzhangyb.ui.fragment.five.FiveFragment;
import com.jkdev.wzryzhangyb.ui.fragment.forth.FourthFragment;
import com.jkdev.wzryzhangyb.ui.fragment.second.SecondFragment;
import com.jkdev.wzryzhangyb.ui.fragment.third.ThirdFragment;
import com.jkdev.wzryzhangyb.ui.view.BottomBar;
import com.jkdev.wzryzhangyb.ui.view.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KJ on 2017/3/20.
 */
public class MainFragment extends SupportFragment {

    private static final String TAG = "--MainFragment";

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIVE = 4;
    private SupportFragment[] mFragments = new SupportFragment[5];

    private BottomBar mBottomBar;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

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

        initView(view);
        return view;
    }

    private void initView(View view) {
        EventBus.getDefault().register(this);
        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.home_recommend_selector, getString(R.string.title_home_recommend)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.home_find_selector, getString(R.string.title_home_find)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.home_square_selector, getString(R.string.title_home_square)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.home_message_selector, getString(R.string.title_home_message)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.home_personal_selector, getString(R.string.title_home_personal)));

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

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Subscribe
    public void startBrotherFragment(StartBrotherEvent event) {
        start(event.targetFragment);
    }

}
