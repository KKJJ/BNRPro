package com.jkdev.wzryzhangyb.ui.fragment.first.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jkdev.wzryzhangyb.MyApplication;
import com.jkdev.wzryzhangyb.bean.AdListDataBean;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * 首页广告轮播图adapter
 * <p>
 * Created by KJ on 2017/3/19.
 */
public class AdListAdapter extends StaticPagerAdapter {

    private List<AdListDataBean.DataEntity.ListEntity> list;

    public AdListAdapter(List<AdListDataBean.DataEntity.ListEntity> list) {
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(MyApplication.getContext()).load(list.get(position).getImage_url()).into(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
