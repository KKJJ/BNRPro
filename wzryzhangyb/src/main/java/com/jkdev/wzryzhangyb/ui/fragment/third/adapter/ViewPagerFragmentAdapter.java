package com.jkdev.wzryzhangyb.ui.fragment.third.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/21.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private static final String TAG = "--ViewPagerFragmentAdapte";
    private List<SupportFragment> mFragments;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<SupportFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((Fragment) obj).getView();
    }


}
