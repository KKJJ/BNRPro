package com.jkdev.wzryzhangyb.ui.fragment.first.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jkdev.wzryzhangyb.MyApplication;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.RecommendListDataBean;
import com.jkdev.wzryzhangyb.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 首页tag 1  adapter
 * <p>
 * Created by KJ on 2017/3/19.
 */

public class ListAdapterTagFirst extends CommonAdapter<RecommendListDataBean.DataEntity> {

    public ListAdapterTagFirst(Context context, int layoutId, List<RecommendListDataBean.DataEntity> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, RecommendListDataBean.DataEntity data, int position) {

        holder.setText(R.id.tv_action_title, data.getTitle());
        String instanceWithNow = TimeUtils.getInstanceWithNow(Long.parseLong(data.getPublish_time()));
        holder.setText(R.id.tv_time, instanceWithNow);
        holder.setText(R.id.tv_comment, data.getComment_count() + "评");
        int type = Integer.parseInt(data.getSticky_type());
        if (data.getVideos() != null) {
            // 原来先区分是不是视频，再区分是不是置顶 有问题
            // 应该先区分是不是置顶，再区分是不是视频
            // 最后发现要分开分别判断，而且要先判断是否是视频 再判断是否置顶

            if (data.getVideos().size() != 0) { // 视频类型
                holder.setVisible(R.id.img_pic_cover, true);
                Glide.with(MyApplication.getContext()).load(data.getVideos().get(0)
                        .getImage_url()).into((ImageView) holder.getView(R.id.img_pic));

                holder.setVisible(R.id.img_type, true); // ///////必须显示

//                if (type == 1 || type == 2) { // 置顶的type值为1，2
                if (type == 0) { // 非置顶
                    holder.setImageResource(R.id.img_type, R.drawable.video_tags);
                } else { // 置顶
                    holder.setImageResource(R.id.img_type, R.drawable.zhiding);
                }

            } else { // 非视频类型
                holder.setVisible(R.id.img_pic_cover, false);
                Glide.with(MyApplication.getContext()).load(data.getRecommend_covers()
                        .get(0)).into((ImageView) holder.getView(R.id.img_pic));

//                if (type == 1 || type == 2) { // 置顶
                if (type == 0) { // 非置顶
                    holder.setVisible(R.id.img_type, false);
                } else { // 置顶
                    holder.setVisible(R.id.img_type, true);
                    holder.setImageResource(R.id.img_type, R.drawable.zhiding);
                }
            }
        }
    }

}
