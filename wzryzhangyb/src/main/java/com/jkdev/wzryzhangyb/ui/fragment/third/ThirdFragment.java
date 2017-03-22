package com.jkdev.wzryzhangyb.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.ui.fragment.third.adapter.ViewPagerFragmentAdapter;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/16.
 */

public class ThirdFragment extends SupportFragment implements View.OnClickListener {

    private static final String TAG = "--ThirdFragment";

    private static final int TAB_ONE = 0;
    private static final int TAB_TWO = 1;
    private static final int TAB_THIRD = 2;

    private List<SupportFragment> mFragments;
    private TextView tvTabHot;
    private TextView tvTabNow;
    private TextView tvTabAttention;
    private ImageView btnTabSend;

    private ViewPager mViewPager;

    public static ThirdFragment newInstance() {

        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_layout, container, false);
        mFragments = new ArrayList<>();
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTabHot = (TextView) view.findViewById(R.id.tv_tab_hot);
        tvTabNow = (TextView) view.findViewById(R.id.tv_tab_now);
        tvTabAttention = (TextView) view.findViewById(R.id.tv_tab_attention);
        btnTabSend = (ImageView) view.findViewById(R.id.img_send_tag4);

        tvTabHot.setOnClickListener(this);
        tvTabNow.setOnClickListener(this);
        tvTabAttention.setOnClickListener(this);
        btnTabSend.setOnClickListener(this);

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mFragments.add(ThirdTab1Fragment.newInstance());
        mFragments.add(ThirdTab2Fragment.newInstance());
        mFragments.add(ThirdTab3Fragment.newInstance());

        initViewPager();
    }

    private void initViewPager() {

        ViewPagerFragmentAdapter fragmentAdapter =
                // 大坑啊，应该用[getChildFragmentManager] 之前用getFragmentManager页面一直空白
                new ViewPagerFragmentAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(0);
        selectTab(TAB_ONE); // 初始设置Tab1为选中
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d(TAG, "onPageSelected: " + position);
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_hot:
                mViewPager.setCurrentItem(TAB_ONE); // 选择viewPageer
                break;
            case R.id.tv_tab_now:
                mViewPager.setCurrentItem(TAB_TWO);
                break;
            case R.id.tv_tab_attention:
                mViewPager.setCurrentItem(TAB_THIRD);
                break;
            case R.id.img_send_tag4:
                Toast.makeText(_mActivity, "Send", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void selectTab(int tabId) {
        switch (tabId) {
            case TAB_ONE:
                setTvState(true, false, false);
                break;
            case TAB_TWO:
                setTvState(false, true, false);
                break;
            case TAB_THIRD:
                setTvState(false, false, true);
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
