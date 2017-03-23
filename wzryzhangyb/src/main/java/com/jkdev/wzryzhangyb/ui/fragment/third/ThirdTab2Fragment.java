package com.jkdev.wzryzhangyb.ui.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.SquareListBean;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.ui.fragment.third.adapter.SquareListAdapter;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 暂时跟 ThirdTab1Fragment 一模一样
 * <p>
 * Created by Kuang on 2017/3/21.
 */

public class ThirdTab2Fragment extends SupportFragment {

    private static final String TAG = "--ThirdTab2Fragment";

    private RecyclerView mRecyclerView;
    private SquareListAdapter mListAdapter;
    private List<SquareListBean.DataBean> mDataList;
    private NetworkClient mNetworkClient;
    private Gson mGson;

    public static ThirdTab2Fragment newInstance() {

        ThirdTab2Fragment fragment = new ThirdTab2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 暂时加载 ThirdTab1Fragment 的布局
        View view = inflater.inflate(R.layout.fragment_tab3_1, container, false);

        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mGson = new Gson();
        mDataList = new ArrayList<>();

        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_square_hot);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));

        mListAdapter = new SquareListAdapter(_mActivity, R.layout.list_item_square_list, mDataList);
        mRecyclerView.setAdapter(mListAdapter);

        initData();
    }

    /**
     * 获取数据
     */
    private void initData() {
        mNetworkClient.getSquareCurrentList(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogUtil.e(TAG, "onResponse" + response.body().toString());
                List<SquareListBean.DataBean> beanList = mGson.fromJson(response.body(), SquareListBean.class).getData();

                notifyRvAdapter(beanList); // 刷新数据
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "initData onFailure: " + t.getMessage());
            }
        });
    }

    /**
     * 刷新列表数据
     *
     * @param list
     */
    private void notifyRvAdapter(List<SquareListBean.DataBean> list) {
        mDataList.clear();
        mDataList.addAll(list);
        mListAdapter.notifyDataSetChanged();
    }

}
