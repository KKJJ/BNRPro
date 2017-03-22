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
import com.jkdev.wzryzhangyb.event.StartBrotherEvent;

import org.greenrobot.eventbus.EventBus;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/16.
 */

public class FiveFragment extends SupportFragment implements View.OnClickListener {

    private ImageView mImgActionOption;
    private LinearLayout mUserLogin;
    private LinearLayout mUserMyWallet;
    private LinearLayout mUserMission;
    private LinearLayout mUserReply;
    private LinearLayout mUserFav;
    private LinearLayout mUserVideo;


    public static FiveFragment newInstance() {

        FiveFragment fragment = new FiveFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mImgActionOption = (ImageView) view.findViewById(R.id.img_action_setting);
        mUserLogin = (LinearLayout) view.findViewById(R.id.ll_user_login);
        mUserMyWallet = (LinearLayout) view.findViewById(R.id.ll_user_my_wallet);
        mUserMission = (LinearLayout) view.findViewById(R.id.ll_user_mission);
        mUserReply = (LinearLayout) view.findViewById(R.id.ll_user_reply);
        mUserFav = (LinearLayout) view.findViewById(R.id.ll_user_fav);
        mUserVideo = (LinearLayout) view.findViewById(R.id.ll_user_video);

        mImgActionOption.setOnClickListener(this);
        mUserLogin.setOnClickListener(this);
        mUserMyWallet.setOnClickListener(this);
        mUserMission.setOnClickListener(this);
        mUserReply.setOnClickListener(this);
        mUserFav.setOnClickListener(this);
        mUserVideo.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_action_setting:
                EventBus.getDefault().post(new StartBrotherEvent(SettingFragment.newInstance()));
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
        if (view.getId() != R.id.img_action_setting) {
            // 除了设置 其它弹提示
            Toast.makeText(_mActivity, "点击", Toast.LENGTH_SHORT).show();
        }


    }

}
