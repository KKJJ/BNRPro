package com.jkdev.wzryzhangyb.ui.fragment.forth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.MessageListDataBean;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.ui.fragment.forth.adapter.MessageListAdapter;
import com.jkdev.wzryzhangyb.ui.view.DividerItemDecoration;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kuang on 2017/3/21.
 */

public class FourTab1Fragment extends SupportFragment {

    private static final String TAG = "--FourTab1Fragment";
    private RecyclerView mRecyclerView; //  消息列表
    private MessageListAdapter mAdapter;
    private List<MessageListDataBean.DataBean> dataList;
    private NetworkClient mNetworkClient;
    private Gson mGson;

    public static FourTab1Fragment newInstance() {

        FourTab1Fragment fragment = new FourTab1Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4_1, container, false);
        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mGson = new Gson();
        dataList = new ArrayList<>();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_message);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(_mActivity,
                DividerItemDecoration.VERTICAL_LIST)); // item分割线

        mAdapter = new MessageListAdapter(_mActivity, R.layout.list_item_message_list, dataList);
        mRecyclerView.setAdapter(mAdapter);

        initData();

    }

    /**
     * 刷新列表数据
     *
     * @param list
     */
    private void notifyRvAdapter(List<MessageListDataBean.DataBean> list) {
        dataList.clear();
        dataList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 联网
     */
    private void initData() {
        mNetworkClient.getMessageList(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogUtil.d(TAG, "onResponse" + response.body().toString());
                List<MessageListDataBean.DataBean> beanList = mGson.fromJson(response.body(), MessageListDataBean.class).getData();

                notifyRvAdapter(beanList); // 刷新数据

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "initData onFailure: " + t.getMessage());
            }
        });
    }

}
