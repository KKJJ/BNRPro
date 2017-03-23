package com.jkdev.wzryzhangyb.ui.fragment.third.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jkdev.wzryzhangyb.MyApplication;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.SquareListBean;
import com.jkdev.wzryzhangyb.utils.DeviceUtils;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.jkdev.wzryzhangyb.utils.TimeUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Kuang on 2017/3/22.
 */

public class SquareListAdapter extends CommonAdapter<SquareListBean.DataBean> implements View.OnClickListener {

    private static final String TAG = "--SquareListAdapter";

    public SquareListAdapter(Context context, int layoutId, List<SquareListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(final ViewHolder holder, SquareListBean.DataBean dataBean, int position) {

        //  设置头像
        final ImageView imageView = holder.getView(R.id.img_pic);
        Glide.with(MyApplication.getContext()).load(dataBean.getAvatar_url()).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(MyApplication.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });

        holder.setText(R.id.tv_name, dataBean.getUser_name());
        holder.setText(R.id.tv_time, TimeUtils.millisToStringDate(dataBean.getPublish_time()));
        holder.setText(R.id.tv_content, dataBean.getContent());

        holder.setText(R.id.tv_good_count, dataBean.getGood_count() + "");
        holder.setText(R.id.tv_comment_count, dataBean.getComment_count() + "");

        List<SquareListBean.DataBean.ImagesBean> imageList = dataBean.getImages(); // 图片数据

        RecyclerView recycleViewImg = holder.getView(R.id.recycle_img);
        GridLayoutManager layoutManager = new GridLayoutManager(MyApplication.getContext(), 3);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 0; // 实时控制GridView列数
//            }
//        });
        recycleViewImg.setLayoutManager(layoutManager);
        ImgAdapter imgAdapter = new ImgAdapter(MyApplication.getContext(), R.layout.list_item_square_list_imgs, imageList);
        recycleViewImg.setAdapter(imgAdapter);

        setListener(holder);
    }

    private void setListener(ViewHolder holder) {
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.getView(R.id.ll_comment_count).setOnClickListener(this);
        holder.getView(R.id.ll_good_count).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_comment_count:
                Toast.makeText(mContext, "点了评论", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_good_count:
                Toast.makeText(mContext, "点了赞", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    class ImgAdapter extends CommonAdapter<SquareListBean.DataBean.ImagesBean> {

        public ImgAdapter(Context context, int layoutId, List<SquareListBean.DataBean.ImagesBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, SquareListBean.DataBean.ImagesBean imagesBean, int position) {

            int sizeW, sizeH; // 图片要显示出的宽高，

            int screenWidth = DeviceUtils.getScreenWidth();
            LogUtil.d(TAG, "convert: screenWidth = " + screenWidth + " px ");

            sizeW = screenWidth / 3;  // 实际上这里只控制了高度，宽度由GridLayoutManager设置列数为3决定了
            //   只是为了由宽度得到高度
            sizeH = sizeW + DeviceUtils.dip2px(30);

            String small_url = imagesBean.getSmall_url();
            Glide.with(MyApplication.getContext()).load(small_url)
                    .override(sizeW, sizeH)
                    .into((ImageView) holder.getView(R.id.iv_img));

        }
    }

}
