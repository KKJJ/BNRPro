package com.jkdev.wzryzhangyb.ui.fragment.second;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.TabFindBean;
import com.jkdev.wzryzhangyb.bean.TabFindBean2;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kuang on 2017/3/16.
 */

public class SecondFragment extends SupportFragment {

    private static final String TAG = "--SecondFragment";

    private TextView mTvUpdate;
    private RecyclerView mRecycleView1;
    private RecyclerView mRecycleView2;
    private RecyclerView mRecycleView3;
    private RecyclerView mRecycleView4;

    // 第一排 数据
    private int[] imgs1 = {R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon};
    private String[] titles1 = {"英雄", "铭文", "视频"};

    // 第二排 数据
    private int[] imgs2 = {R.drawable.ic_guess_share, R.drawable.ic_guess_share, R.drawable.ic_guess_share, R.drawable.ic_guess_share};
    private String[] titles2 = {"金币夺宝", "掌豆商城", "值得买", "精品手游"};
    private String[] descs2 = {"只要1元抢枪抢", "炫酷边框改名卡", "买买买停不下来", "打发时光好游戏"};


    // 第三排 数据
    private int[] imgs3 = {R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon};
    private String[] titles3 = {"装备查询", "召唤师技能", "游戏下载"};

    // 第四排 数据
    private int[] imgs4 = {R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon, R.drawable.message_topic_tip_icon};
    private String[] titles4 = {"精彩专栏", "排位风向", "游戏壁纸", "副本挑战"};


    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_layout, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvUpdate = (TextView) view.findViewById(R.id.tv_update);
        mRecycleView1 = (RecyclerView) view.findViewById(R.id.recycle_view_1);
        mRecycleView2 = (RecyclerView) view.findViewById(R.id.recycle_view_2);
        mRecycleView3 = (RecyclerView) view.findViewById(R.id.recycle_view_3);
        mRecycleView4 = (RecyclerView) view.findViewById(R.id.recycle_view_4);

        mTvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity, "判断更新", Toast.LENGTH_SHORT).show();
            }
        });


        initRecycleView1();

        initRecycleView2();

        initRecycleView3();

        initRecycleView4();

    }

    private void initRecycleView4() {
        final ArrayList<TabFindBean> dataList4 = new ArrayList<>();
        for (int i = 0; i < imgs4.length; i++) {
            dataList4.add(new TabFindBean(imgs4[i], titles4[i]));
        }
        mRecycleView4.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        PubAdapter pubAdapter4 = new PubAdapter(_mActivity,
                R.layout.list_item_tab_find_1, dataList4);
        mRecycleView4.setAdapter(pubAdapter4);
        pubAdapter4.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(_mActivity, dataList4.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initRecycleView3() {
        final ArrayList<TabFindBean> dataList3 = new ArrayList<>();
        for (int i = 0; i < imgs3.length; i++) {
            dataList3.add(new TabFindBean(imgs3[i], titles3[i]));
        }
        mRecycleView3.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        PubAdapter pubAdapter3 = new PubAdapter(_mActivity,
                R.layout.list_item_tab_find_1, dataList3);
        mRecycleView3.setAdapter(pubAdapter3);
        pubAdapter3.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(_mActivity, dataList3.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initRecycleView2() {
        final ArrayList<TabFindBean2> dataList2 = new ArrayList<>();
        for (int i = 0; i < imgs2.length; i++) {
            dataList2.add(new TabFindBean2(imgs2[i], titles2[i], descs2[i]));
        }
        mRecycleView2.setLayoutManager(new GridLayoutManager(_mActivity, 2));
        PubAdapter2 pubAdapter2 = new PubAdapter2(_mActivity,
                R.layout.list_item_tab_find_2, dataList2);
        mRecycleView2.setAdapter(pubAdapter2);
        pubAdapter2.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(_mActivity, dataList2.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void initRecycleView1() {
        final ArrayList<TabFindBean> dataList1 = new ArrayList<>();
        for (int i = 0; i < imgs1.length; i++) {
            dataList1.add(new TabFindBean(imgs1[i], titles1[i]));
        }
        mRecycleView1.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        PubAdapter pubAdapter1 = new PubAdapter(_mActivity,
                R.layout.list_item_tab_find_1, dataList1);
        mRecycleView1.setAdapter(pubAdapter1);
        pubAdapter1.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(_mActivity, dataList1.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private static class PubAdapter extends CommonAdapter<TabFindBean> {

        public PubAdapter(Context context, int layoutId, List<TabFindBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, TabFindBean dataBean, int position) {
            holder.setText(R.id.tv_title_tab_find_1, dataBean.getTitle());
            holder.setImageResource(R.id.img_tab_find_1, dataBean.getImg());
        }
    }

    private static class PubAdapter2 extends CommonAdapter<TabFindBean2> {


        public PubAdapter2(Context context, int layoutId, List<TabFindBean2> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, TabFindBean2 dataBean, int position) {
            holder.setText(R.id.tv_title_tab_find_2, dataBean.getTitle());
            holder.setText(R.id.tv_desc_tab_find_2, dataBean.getDesc());
            holder.setImageResource(R.id.img_tab_find_2, dataBean.getImg());
        }
    }


}
