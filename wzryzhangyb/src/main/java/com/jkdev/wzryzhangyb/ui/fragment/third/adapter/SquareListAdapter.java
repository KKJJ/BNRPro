package com.jkdev.wzryzhangyb.ui.fragment.third.adapter;

import android.content.Context;

import com.jkdev.wzryzhangyb.bean.SquareListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Kuang on 2017/3/22.
 */

public class SquareListAdapter extends CommonAdapter<SquareListBean.DataBean> {


    public SquareListAdapter(Context context, int layoutId, List<SquareListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SquareListBean.DataBean dataBean, int position) {

    }

}
