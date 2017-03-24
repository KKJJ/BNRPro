package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.SearchListBean;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.SearchListAdapter;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KJ on 2017/3/20.
 */

public class SearchFragment extends SupportFragment {

    private static final String TAG = "--SearchFragment";

    private ImageView btnImageBack;
    private ImageView btnImageView;
    private EditText etSearch;
    private RecyclerView mRecyclerView;
    private TextView mTvNoData;

    private NetworkClient mNetworkClient;
    private Gson mGson;
    private List<SearchListBean.DataBean> mDataList;
    private SearchListAdapter mListAdapter;

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
        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mGson = new Gson();
        mDataList = new ArrayList<>();
        initView(view);

        return view;
    }

    private void initView(View view) {
        btnImageBack = (ImageView) view.findViewById(R.id.img_action_back);
        btnImageView = (ImageView) view.findViewById(R.id.img_search);
        etSearch = (EditText) view.findViewById(R.id.et_search);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_search_result);
        mTvNoData = (TextView) view.findViewById(R.id.tv_no_data);
        etSearch.setFocusable(true);
        showSoftInput(etSearch);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mListAdapter = new SearchListAdapter(_mActivity, R.layout.list_item_search_list, mDataList);
        mRecyclerView.setAdapter(mListAdapter);

        btnImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etSearch.getText().toString();
                if (TextUtils.isEmpty(word)) {
                    Toast.makeText(_mActivity, "请输入关键字！", Toast.LENGTH_SHORT).show();
                } else {
                    //  搜索工作
                    searchFromNet(word);
                    hideSoftInput();
                }
            }
        });
        btnImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

        mListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(_mActivity, "点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    /**
     * 联网搜索
     *
     * @param word
     */
    private void searchFromNet(String word) {
        mNetworkClient.topicSearch(word, new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseString = response.body().toString();
                LogUtil.d(TAG, "searchFromNet onResponse: " + responseString);

                List<SearchListBean.DataBean> beanList = mGson.fromJson(responseString, SearchListBean.class).getData();
                if (beanList.size() == 0) { // 没有数据返回
                    mTvNoData.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else { // 有数据返回
                    mTvNoData.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mDataList.addAll(beanList);
                    mListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "searchFromNet onFailure: " + t.getMessage());
            }
        });

    }


}
