package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.constant.NetConstant;
import com.jkdev.wzryzhangyb.net.NetworkManager;
import com.jkdev.wzryzhangyb.net.RetrofitInterface;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import java.util.HashMap;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Kuang on 2017/3/16.
 */

public class FirstFragment extends SupportFragment {

    private NetworkManager networkManager;
    private Toolbar mToolbar;

    public static FirstFragment newInstance() {

        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_layout, container, false);
        networkManager = NetworkManager.getInstance();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.first_toolbar);
        mToolbar.setLogo(R.drawable.home_recommend_p);
        mToolbar.setTitle(R.string.first_toolbar_title);

        view.findViewById(R.id.first_button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetrofitInterface retrofitInterface = networkManager.create(RetrofitInterface.class);
                HashMap map = networkManager.putParam(NetConstant.checkclock);
                Call<String> call = retrofitInterface.getData(map);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        LogUtil.e(TAG, "onResponse: " + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        LogUtil.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }


}
