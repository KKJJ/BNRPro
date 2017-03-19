package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.ui.fragment.first.adapter.TagViewPagerAdapter;
import com.jkdev.wzryzhangyb.bean.TagListBean;
import com.jkdev.wzryzhangyb.constant.NetConstant;
import com.jkdev.wzryzhangyb.net.NetworkManager;
import com.jkdev.wzryzhangyb.net.RetrofitInterface;
import com.jkdev.wzryzhangyb.ui.fragment.second.SecondFragment;
import com.jkdev.wzryzhangyb.ui.view.MySimplePagerTitleView;
import com.jkdev.wzryzhangyb.utils.LogUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private MagicIndicator magicIndicator;
    private ViewPager mViewPager;
    private List<TagListBean.DataEntity> mTagList;
    private TagViewPagerAdapter adapter;
    private ArrayList<SupportFragment> supportFragments;

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

        magicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTagList = new ArrayList<>();
        supportFragments = new ArrayList<>();
        adapter = new TagViewPagerAdapter(getFragmentManager(), supportFragments);


        initMagicIndicator(); // 初始化指示器

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initTagData();

    }

    /**
     * 初始化tag数据
     */
    private void initTagData() {
        TagListBean tagListBean = new Gson().fromJson(NetConstant.tag_list_data, TagListBean.class);
        mTagList = tagListBean.getData();
        supportFragments.add(new TagFragmentFirst());
        for (int i = 1; i < mTagList.size(); i++) {
            supportFragments.add(new SecondFragment());
        }
        mViewPager.setAdapter(adapter);
        magicIndicator.getNavigator().notifyDataSetChanged();

    }

    /**
     * 暂未用到
     */
    private void askNet() {
        RetrofitInterface retrofitInterface = networkManager.create(RetrofitInterface.class);
        HashMap map = networkManager.putParam(NetConstant.checkclock);
        Call<String> call = retrofitInterface.getData(map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogUtil.e(TAG, "onResponse: " + response.body().toString());
                initTagData();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    /**
     * 初始化指示器
     */
    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(getResources().getColor(R.color.tag_list_bg));
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setScrollPivotX(0.35f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTagList == null ? 0 : mTagList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                MySimplePagerTitleView simplePagerTitleView = new MySimplePagerTitleView(context);
                simplePagerTitleView.setText(mTagList.get(index).getName());
                simplePagerTitleView.setNormalColor(Color.WHITE);
                simplePagerTitleView.setSelectedColor(Color.WHITE);

                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);

                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }


}
