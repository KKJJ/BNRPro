package com.jkdev.wzryzhangyb.ui.fragment.forth.adapter;

import android.content.Context;

import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.MessageListDataBean;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.jkdev.wzryzhangyb.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Kuang on 2017/3/22.
 */

public class MessageListAdapter extends CommonAdapter<MessageListDataBean.DataBean> {

    private static final String TAG = "--MessageListAdapter";

    public MessageListAdapter(Context context, int layoutId, List<MessageListDataBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MessageListDataBean.DataBean data, int position) {
        holder.setText(R.id.tv_name, data.getTitle());
        holder.setText(R.id.tv_desc, data.getContent());
        holder.setText(R.id.tv_time, TimeUtils.millisToStringDate(data.getCreate_time()));
    }


}
