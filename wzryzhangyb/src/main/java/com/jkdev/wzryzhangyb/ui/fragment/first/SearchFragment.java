package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KJ on 2017/3/20.
 */

public class SearchFragment extends SupportFragment {

    private ImageView btnImageBack;
    private ImageView btnImageView;
    private EditText etSearch;

    public static SearchFragment newInstance() {

        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_layout, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        btnImageBack = (ImageView) view.findViewById(R.id.img_action_back);
        btnImageView = (ImageView) view.findViewById(R.id.img_search);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        etSearch.setFocusable(true);
        showSoftInput(etSearch);

        btnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etSearch.getText().toString();
                if (TextUtils.isEmpty(word)) {
                    Toast.makeText(_mActivity, "请输入关键字！", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO 搜索工作
                }
            }
        });
        btnImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

    }


}
