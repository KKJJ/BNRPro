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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.AdListDataBean;
import com.jkdev.wzryzhangyb.bean.RecommendListDataBean;
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

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;

/**
 * Created by KJ on 2017/3/18.
 */

public class TagFragmentFirst extends SupportFragment implements View.OnClickListener {

    private static final String TAG = "--TagFragmentFirst";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RollPagerView mRollPagerView;
    private RecyclerView mRecyclerView;
    private CommonAdapter mCommonAdapter;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private LoadMoreWrapper mLoadMoreWrapper;
    private View mHeaderView;
    private View mLoadMoreView;
    private ImageView mImageViewFour1;
    private ImageView mImageViewFour2;
    private ImageView mImageViewFour3;
    private ImageView mImageViewFour4;
    private List<RecommendListDataBean.DataEntity> dataList;
    private List<AdListDataBean.DataEntity.ListEntity> adList;
    private NetworkClient mNetworkClient;
    private Gson mGson;
    private SharePreferenceUtil mSharePreferenceUtil;

    private AdListAdapter mAdListAdapter; // 轮播图Adapter
    private boolean needToLoad = true; // 因为在第一次安装时 轮播图有时候没显示,所以滑动的时候刷新下 这是是否刷新标志

    public static TagFragmentFirst newInstance() {
        TagFragmentFirst fragment = new TagFragmentFirst();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tag_fragment_first, container, false);
        mHeaderView = inflater.inflate(R.layout.list_view_header_first, container, false);
        mLoadMoreView = inflater.inflate(R.layout.listview_footer_layout, container, false);
        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mGson = new Gson();
        mSharePreferenceUtil = SharePreferenceUtil.getInstance();
        dataList = new ArrayList<>();
        adList = new ArrayList<>();
        initView(view);
        return view;
    }

    private void initView(View view) {

        initHeaderView(); // 初始头部
        initRecommendRecycleView(view); // 初始列表数据

    }

    /**
     * 初始列表数据
     *
     * @param view
     */
    private void initRecommendRecycleView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_tag_first);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        initRvAdapter(); //

        String recommendListDataString = mSharePreferenceUtil.getRecommendListData(); // 拿缓存数据
        if (TextUtils.isEmpty(recommendListDataString)) { // 没本地数据 联网请求
            getRecommendDataFromNet();
        } else {
            List<RecommendListDataBean.DataEntity> localDataList = new Gson().fromJson(recommendListDataString, RecommendListDataBean.class).getData();
            dataList.addAll(localDataList); // 需用add再notify才有效果，不能用 =
            notifyRvAdapter();
        }
    }

    /**
     * 网络请求recommend列表数据
     */
    private void getRecommendDataFromNet() {
        mNetworkClient.getRecommendListData(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                mSharePreferenceUtil.setRecommendListData(responseData); // 缓存本地
                dataList.clear(); // 先清空数据
                needToLoad = true; // 刷新后置为true
                List<RecommendListDataBean.DataEntity> netDataList = mGson.fromJson(responseData, RecommendListDataBean.class).getData();
                dataList.addAll(netDataList); //
                notifyRvAdapter();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "getRecommendDataFromNet onFailure: " + t.getMessage());
            }
        });
    }

    /**
     * 初始Adapter并设置
     */
    private void initRvAdapter() {

        Log.d(TAG, "setRvAdapter: 组装adapter");
        mCommonAdapter = new ListAdapterTagFirst(getContext(), R.layout.list_item_tag_first, dataList);
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        mHeaderAndFooterWrapper.addHeaderView(mHeaderView); // 头部
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);

        mLoadMoreWrapper.setLoadMoreView(mLoadMoreView); // 加载更多
        mRecyclerView.setAdapter(mLoadMoreWrapper);
        setListener(); // 设置监听
    }

    /**
     * 刷新列表数据
     */
    private void notifyRvAdapter() {
        Log.d(TAG, "setRvAdapter: 刷新数据");
        mLoadMoreWrapper.notifyDataSetChanged();
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                EventBus.getDefault().post(
                        new StartBrotherEvent(
                                NewsContentFragment.getInstance(
                                        dataList.get(position - 1).getId()))); // 因为列表有header 所以减1
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
                getRecommendDataFromNet();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needToLoad && mAdListAdapter != null) {
                    mAdListAdapter.notifyDataSetChanged(); // 因为在第一次安装时 轮播图有时候没显示,所以滑动的时候刷新下
                    needToLoad = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 初始化列表头部
     */

    private void initHeaderView() {
        mRollPagerView = (RollPagerView) mHeaderView.findViewById(R.id.roll_viewpager);
        mImageViewFour1 = (ImageView) mHeaderView.findViewById(R.id.img_four_1);
        mImageViewFour2 = (ImageView) mHeaderView.findViewById(R.id.img_four_2);
        mImageViewFour3 = (ImageView) mHeaderView.findViewById(R.id.img_four_3);
        mImageViewFour4 = (ImageView) mHeaderView.findViewById(R.id.img_four_4);

        mAdListAdapter = new AdListAdapter(adList);
        mRollPagerView.setAdapter(mAdListAdapter);

        String adListData = SharePreferenceUtil.getInstance().getAdListData(); // 拿缓存数据
        if (TextUtils.isEmpty(adListData)) {
            getAdListData();
        } else {
            Log.d(TAG, "initHeaderView: adList缓存 ： " + adListData);
            List<AdListDataBean.DataEntity.ListEntity> entityList = new Gson().fromJson(adListData, AdListDataBean.class).getData().getList();
            adList.addAll(entityList);
            mAdListAdapter.notifyDataSetChanged();

        }
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 4) { ////////////////////////////////////////
                    Toast.makeText(_mActivity, "这个还没做", Toast.LENGTH_SHORT).show();
                    return;
                }
                EventBus.getDefault().post(
                        new StartBrotherEvent(
                                NewsContentFragment.getInstance(
                                        adList.get(position).getRedirect_data()))); //
            }
        });
        mImageViewFour1.setOnClickListener(this);
        mImageViewFour2.setOnClickListener(this);
        mImageViewFour3.setOnClickListener(this);
        mImageViewFour4.setOnClickListener(this);

    }

    /**
     * 获取轮播图数据
     */
    private void getAdListData() {
        mNetworkClient.getAdListData(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                Log.i(TAG, "getAdListData onResponse: " + data);
                mSharePreferenceUtil.setAdListData(responseData); // 缓存到本地

                List<AdListDataBean.DataEntity.ListEntity> entityList = mGson.fromJson(responseData, AdListDataBean.class).getData().getList();
                adList.addAll(entityList);
                mAdListAdapter.notifyDataSetChanged();
                mAdListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "getAdListData onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_four_1:
                break;
            case R.id.img_four_2:
                break;
            case R.id.img_four_3:
                break;
            case R.id.img_four_4:
                break;
            default:
                break;
        }
    }


}
