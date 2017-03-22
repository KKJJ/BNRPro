package com.jkdev.wzryzhangyb.ui.fragment.first.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KJ on 2017/3/18.
 */

public class TagViewPagerAdapter extends FragmentPagerAdapter {

    private List<SupportFragment> fragments;

    public TagViewPagerAdapter(FragmentManager fm, List<SupportFragment> fms) {
        super(fm);
        fragments = fms;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
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
