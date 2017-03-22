package com.jkdev.wzryzhangyb.ui.fragment.five;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/22.
 */

public class SettingFragment extends SupportFragment implements View.OnClickListener {

    private ImageView mImgActionBack;
    private LinearLayout mSetting1;
    private LinearLayout mSetting2;
    private LinearLayout mSetting3;
    private LinearLayout mSetting4;
    private LinearLayout mSetting5;
    private LinearLayout mSetting6;
    private LinearLayout mSetting7;


    public static SettingFragment newInstance() {

        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mImgActionBack = (ImageView) view.findViewById(R.id.img_action_back);
        mSetting1 = (LinearLayout) view.findViewById(R.id.ll_setting1);
        mSetting2 = (LinearLayout) view.findViewById(R.id.ll_setting2);
        mSetting3 = (LinearLayout) view.findViewById(R.id.ll_setting3);
        mSetting4 = (LinearLayout) view.findViewById(R.id.ll_setting4);
        mSetting5 = (LinearLayout) view.findViewById(R.id.ll_setting5);
        mSetting6 = (LinearLayout) view.findViewById(R.id.ll_setting6);
        mSetting7 = (LinearLayout) view.findViewById(R.id.ll_setting7);

        mImgActionBack.setOnClickListener(this);
        mSetting1.setOnClickListener(this);
        mSetting2.setOnClickListener(this);
        mSetting3.setOnClickListener(this);
        mSetting4.setOnClickListener(this);
        mSetting5.setOnClickListener(this);
        mSetting6.setOnClickListener(this);
        mSetting7.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_action_back:
                pop(); // 返回
                break;
            case R.id.ll_user_login:

                break;
            case R.id.ll_user_my_wallet:
                break;
            case R.id.ll_user_mission:
                break;
            case R.id.ll_user_reply:
                break;
            case R.id.ll_user_fav:
                break;
            case R.id.ll_user_video:
                break;
            default:
                break;
        }
        if (view.getId() != R.id.img_action_back) {
            // 除了设置 其它弹提示
            Toast.makeText(_mActivity, "点击了", Toast.LENGTH_SHORT).show();
        }

    }

}
