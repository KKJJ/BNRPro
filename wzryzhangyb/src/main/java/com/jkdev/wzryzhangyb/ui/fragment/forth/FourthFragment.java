package com.jkdev.wzryzhangyb.ui.fragment.forth;

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

public class FourthFragment extends SupportFragment implements View.OnClickListener {

    private static final int TAB_ONE = 0;
    private static final int TAB_TWO = 1;

    private SupportFragment[] mFragments = new SupportFragment[2];
    private TextView tvTabMessage;
    private TextView tvTabFriends;

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

        if (savedInstanceState == null) {
            mFragments[TAB_ONE] = FourTab1Fragment.newInstance();
            mFragments[TAB_TWO] = FourTab2Fragment.newInstance();

            loadMultipleRootFragment(R.id.container, TAB_ONE,
                    mFragments[TAB_ONE],
                    mFragments[TAB_TWO]);
        } else {
            // 这里库已经做了Fragment恢复工作，不需要额外的处理
            // 这里我们需要拿到mFragments的引用，用下面的方法查找更方便些，也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些)
            mFragments[TAB_ONE] = findFragment(FourTab1Fragment.class);
            mFragments[TAB_TWO] = findFragment(FourTab2Fragment.class);
        }

        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTabMessage = (TextView) view.findViewById(R.id.tv_tab_message);
        tvTabFriends = (TextView) view.findViewById(R.id.tv_tab_friends);

        tvTabMessage.setOnClickListener(this);
        tvTabFriends.setOnClickListener(this);

        selectTab(TAB_ONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_message:
                selectTab(TAB_ONE);
                break;
            case R.id.tv_tab_friends:
                selectTab(TAB_TWO);
                break;
            default:
                break;
        }
    }

    private void selectTab(int tabId) {
        switch (tabId) {
            case TAB_ONE:
                setTvState(true, false);
                showHideFragment(mFragments[TAB_ONE], mFragments[TAB_TWO]);
                break;
            case TAB_TWO:
                setTvState(false, true);
                showHideFragment(mFragments[TAB_TWO], mFragments[TAB_ONE]);
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
    private void setTvState(boolean selected, boolean selected2) {
        tvTabMessage.setSelected(selected);
        tvTabFriends.setSelected(selected2);
    }

}
