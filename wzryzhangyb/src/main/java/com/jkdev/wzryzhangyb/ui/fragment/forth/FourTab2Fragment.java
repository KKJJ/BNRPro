package com.jkdev.wzryzhangyb.ui.fragment.forth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/21.
 */

public class FourTab2Fragment extends SupportFragment {

    private static final String TAG = "--FourTab2Fragment";
    private Button BtnToLogin;

    public static FourTab2Fragment newInstance() {

        FourTab2Fragment fragment = new FourTab2Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4_2, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        BtnToLogin = (Button) view.findViewById(R.id.btn_to_login);
        BtnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity, "去登陆咯", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
