package com.jkdev.wzryzhangyb.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jkdev.wzryzhangyb.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/16.
 */

public class ThirdFragmentCopy extends SupportFragment implements View.OnClickListener {

    private static final int TAB_ONE = 0;
    private static final int TAB_TWO = 1;
    private static final int TAB_THIRD = 2;

    private SupportFragment[] mFragments = new SupportFragment[3];
    private TextView tvTabHot;
    private TextView tvTabNow;
    private TextView tvTabAttention;

    private static int prePosition = 1; // 记录上一次的位置 以便隐藏

    public static ThirdFragmentCopy newInstance() {

        ThirdFragmentCopy fragment = new ThirdFragmentCopy();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_layout, container, false);

        if (savedInstanceState == null) {
            mFragments[TAB_ONE] = ThirdTab1Fragment.newInstance();
            mFragments[TAB_TWO] = ThirdTab2Fragment.newInstance();
            mFragments[TAB_THIRD] = ThirdTab3Fragment.newInstance();

            loadMultipleRootFragment(R.id.container, TAB_ONE,
                    mFragments[TAB_ONE],
                    mFragments[TAB_TWO],
                    mFragments[TAB_THIRD]);
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些)
            mFragments[TAB_ONE] = findFragment(ThirdTab1Fragment.class);
            mFragments[TAB_TWO] = findFragment(ThirdTab2Fragment.class);
            mFragments[TAB_THIRD] = findFragment(ThirdTab3Fragment.class);
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTabHot = (TextView) view.findViewById(R.id.tv_tab_hot);
        tvTabNow = (TextView) view.findViewById(R.id.tv_tab_now);
        tvTabAttention = (TextView) view.findViewById(R.id.tv_tab_attention);

        tvTabHot.setOnClickListener(this);
        tvTabNow.setOnClickListener(this);
        tvTabAttention.setOnClickListener(this);

        selectTab(TAB_ONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_hot:
                selectTab(TAB_ONE);
                prePosition = TAB_ONE;
                break;
            case R.id.tv_tab_now:
                selectTab(TAB_TWO);
                prePosition = TAB_TWO;
                break;
            case R.id.tv_tab_attention:
                selectTab(TAB_THIRD);
                prePosition = TAB_THIRD;
                break;
            default:
                break;
        }
    }

    private void selectTab(int tabId) {
        switch (tabId) {
            case TAB_ONE:
                setTvState(true, false, false);
                showHideFragment(mFragments[TAB_ONE], mFragments[prePosition]);
                break;
            case TAB_TWO:
                setTvState(false, true, false);
                showHideFragment(mFragments[TAB_TWO], mFragments[prePosition]);
                break;
            case TAB_THIRD:
                setTvState(false, false, true);
                showHideFragment(mFragments[TAB_THIRD], mFragments[prePosition]);
                break;
            default:
                break;
        }
    }

    /**
     * 设置两个textView 状态
     *
     * @param selected
     * @param selected2
     */
    private void setTvState(boolean selected, boolean selected2, boolean selected3) {
        tvTabHot.setSelected(selected);
        tvTabNow.setSelected(selected2);
        tvTabAttention.setSelected(selected3);
    }


}
