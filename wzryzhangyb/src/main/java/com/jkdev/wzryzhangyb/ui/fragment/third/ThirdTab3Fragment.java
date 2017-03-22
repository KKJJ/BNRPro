package com.jkdev.wzryzhangyb.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkdev.wzryzhangyb.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/21.
 */

public class ThirdTab3Fragment extends SupportFragment {

    private static final String TAG = "--ThirdTab3Fragment";

    public static ThirdTab3Fragment newInstance() {

        ThirdTab3Fragment fragment = new ThirdTab3Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3_3, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }

}
