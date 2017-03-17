package com.jkdev.wzryzhangyb.ui.fragment.forth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.ui.fragment.first.ViewPagerFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/16.
 */

public class FourthFragment extends SupportFragment {

    public static FourthFragment newInstance() {

        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_layout, container, false);
//        initView(savedInstanceState);
        return view;
    }

    private void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_fourth_container, ViewPagerFragment.newInstance());
        }

    }


}
