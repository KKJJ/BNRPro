package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.RecommendListDataBean;
import com.jkdev.wzryzhangyb.constant.NetConstant;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.AdListAdapter;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.ListAdapterTagFirst;
import com.jkdev.wzryzhangyb.ui.view.DividerItemDecoration;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by KJ on 2017/3/18.
 */

public class TagFragmentFirst extends SupportFragment implements View.OnClickListener {

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

        initView(view);
        return view;
    }

    private void initView(View view) {

        initHeaderView();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_tag_first);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        RecommendListDataBean recommendListData = new Gson().fromJson(NetConstant.recommend_list_data, RecommendListDataBean.class);
        final List<RecommendListDataBean.DataEntity> dataList = recommendListData.getData();
        mCommonAdapter = new ListAdapterTagFirst(getContext(), R.layout.list_item_tag_first, dataList);

        mCommonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mCommonAdapter);
        mHeaderAndFooterWrapper.addHeaderView(mHeaderView); // 头部
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
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
        mLoadMoreWrapper.setLoadMoreView(mLoadMoreView); // 加载更多
        mRecyclerView.setAdapter(mLoadMoreWrapper);


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
        mRollPagerView.setAdapter(new AdListAdapter());
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(_mActivity, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mImageViewFour1.setOnClickListener(this);
        mImageViewFour2.setOnClickListener(this);
        mImageViewFour3.setOnClickListener(this);
        mImageViewFour4.setOnClickListener(this);
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
