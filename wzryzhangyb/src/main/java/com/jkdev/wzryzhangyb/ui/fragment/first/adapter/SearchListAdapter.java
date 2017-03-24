package com.jkdev.wzryzhangyb.ui.fragment.first.adapter;

import android.content.Context;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.SearchListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 首页搜索展示
 * <p>
 * Created by Kuang on 2017/3/24.
 */

public class SearchListAdapter extends CommonAdapter<SearchListBean.DataBean> {

    public SearchListAdapter(Context context, int layoutId, List<SearchListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, SearchListBean.DataBean dataBean, int position) {
        holder.setText(R.id.tv_title, dataBean.getTitle());
        holder.setText(R.id.tv_author, dataBean.getAuthor());
        holder.setText(R.id.tv_comment_count, dataBean.getComment_count() + "评");
    }
}
