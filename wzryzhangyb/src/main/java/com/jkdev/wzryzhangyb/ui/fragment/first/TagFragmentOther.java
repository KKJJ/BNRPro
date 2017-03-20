package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.AdListDataBean;
import com.jkdev.wzryzhangyb.bean.RecommendListDataBean;
import com.jkdev.wzryzhangyb.constant.Constants;
import com.jkdev.wzryzhangyb.event.StartBrotherEvent;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.AdListAdapter;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.ListAdapterTagFirst;
import com.jkdev.wzryzhangyb.ui.view.DividerItemDecoration;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.jkdev.wzryzhangyb.utils.SharePreferenceUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KJ on 2017/3/20.
 */

public class TagFragmentOther extends SupportFragment {

    private static final String TAG = "--TagFragmentOther";
    private static final String TAG_ID = "TAG_ID";
    private int tagIdFlag;
    private RollPagerView mRollPagerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private CommonAdapter mCommonAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;
    private View mHeaderView;
    private View mLoadMoreView;
    private List<RecommendListDataBean.DataEntity> dataList;
    private List<AdListDataBean.DataEntity.ListEntity> adList;
    private NetworkClient mNetworkClient;
    private Gson mGson;
    private SharePreferenceUtil mSharePreferenceUtil;
    private int[] tagIds = {5, 4, 3, 2, 98};
    private int tagId;

    public static TagFragmentOther newInstance(int tId) {

        TagFragmentOther fragment = new TagFragmentOther();
        Bundle args = new Bundle();
        args.putInt(TAG_ID, tId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_fragment_first, container, false);
        mHeaderView = inflater.inflate(R.layout.list_view_header_other, container, false);
        mLoadMoreView = inflater.inflate(R.layout.listview_footer_layout, container, false);
        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mSharePreferenceUtil = SharePreferenceUtil.getInstance();
        mGson = new Gson();
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {

        initHeaderView();
        tagIdFlag = getArguments().getInt(TAG_ID);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_tag_first);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        tagId = tagIds[tagIdFlag];
        String tagListDataString = mSharePreferenceUtil.getTagListData(Constants.TAG_LIST_DATA + tagId); // 拿缓存数据
        if (TextUtils.isEmpty(tagListDataString)) { // 没本地数据 联网请求
            getTagListDataFromNet(tagId, true);
        } else {
            dataList = new Gson().fromJson(tagListDataString, RecommendListDataBean.class).getData();
            setAdapter(true); // 设置各种适配器
        }
    }

    /**
     * @param isInit : 是不是初始请求  即与刷新区分
     */
    private void getTagListDataFromNet(final int tagId, final boolean isInit) {
        mNetworkClient.getTagListData(tagId, new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                mSharePreferenceUtil.setTagListData(Constants.TAG_LIST_DATA + tagId, responseData);// 缓存本地
                dataList = mGson.fromJson(responseData, RecommendListDataBean.class).getData();
                setAdapter(isInit);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "getRecommendDataFromNet onFailure: " + t.getMessage());
            }
        });
    }

    /**
     * 设置各种适配器
     *
     * @param isInit
     */
    private void setAdapter(boolean isInit) {
        Log.d(TAG, "setAdapter: init: " + isInit);
        if (isInit) {
            mCommonAdapter = new ListAdapterTagFirst(getContext(), R.layout.list_item_tag_first, dataList);
            mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
            mHeaderAndFooterWrapper.addHeaderView(mHeaderView); // 头部
            mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
            mLoadMoreWrapper.setLoadMoreView(mLoadMoreView); // 加载更多
            mRecyclerView.setAdapter(mLoadMoreWrapper);
            setListener(dataList); // 设置各种监听
        } else {
            mLoadMoreWrapper.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 设置监听
     *
     * @param dataList
     */
    private void setListener(final List<RecommendListDataBean.DataEntity> dataList) {
        mCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(NewsContentFragment.getInstance(dataList.get(position).getId())));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataList.addAll(dataList);
                        mLoadMoreWrapper.notifyDataSetChanged();
                    }
                }, 1500);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTagListDataFromNet(tagId, false);
            }
        });
    }

    /**
     * 初始化列表头部
     */
    private void initHeaderView() {
        mRollPagerView = (RollPagerView) mHeaderView.findViewById(R.id.roll_viewpager);
        String adListData = SharePreferenceUtil.getInstance().getAdListData(); // 拿缓存数据
        if (TextUtils.isEmpty(adListData)) {
            getAdListData();
        } else {
            AdListDataBean adListDataBean = new Gson().fromJson(adListData, AdListDataBean.class);
            adList = adListDataBean.getData().getList();
            mRollPagerView.setAdapter(new AdListAdapter(adList));
        }
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(
                        new StartBrotherEvent(
                                NewsContentFragment.getInstance(
                                        adList.get(position).getRedirect_data()))); //
            }
        });
    }

    /**
     * 获取轮播图数据
     */
    private void getAdListData() {
        mNetworkClient.getAdListData(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                SharePreferenceUtil.getInstance().setAdListData(responseData); // 缓存
                AdListDataBean adListDataBean = new Gson().fromJson(responseData, AdListDataBean.class);
                adList = adListDataBean.getData().getList();
                mRollPagerView.setAdapter(new AdListAdapter(adList));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
