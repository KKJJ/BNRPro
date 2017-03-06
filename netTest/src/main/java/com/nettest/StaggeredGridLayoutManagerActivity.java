package com.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StaggeredGridLayoutManagerActivity extends AppCompatActivity {

    private static final int[] imgs = {R.drawable.apple_pic, R.drawable.banana_pic,
            R.drawable.cherry_pic, R.drawable.grape_pic, R.drawable.mango_pic,
            R.drawable.orange_pic, R.drawable.pear_pic, R.drawable.pineapple_pic,
            R.drawable.strawberry_pic, R.drawable.watermelon_pic};
    private static final String[] strsEng = {"apple", "banana", "cheery", "grape", "mango", "orange", "pear", "pineapple", "strawberry", "watermelon"};
    private static final String[] strsChan = {"苹果", "香蕉", "樱桃", "葡萄", "芒果", "橙子", "梨", "菠萝", "草莓", "西瓜"};

    private RecyclerView mRecyclerView;

    /**
     * 获得数据List
     *
     * @return
     */
    private List<DataModel> getArrayList() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < strsChan.length; j++) {
                DataModel model = new DataModel(imgs[j], strsEng[j] + " " + getName(strsChan[j]));
                list.add(model);
            }
        }
        return list;
    }

    /**
     * 随机生成Desc长度
     *
     * @param name
     * @return
     */
    private String getName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout_manager);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(getArrayList());
        mRecyclerView.setAdapter(myAdapter);


    }

}

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DataModel> list;

    public MyAdapter(List<DataModel> lis) {
        list = lis;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_v, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);

        holder.itemRView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DataModel model = list.get(position);
                Toast.makeText(v.getContext(), "you clicked view " + model.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.imgPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DataModel model = list.get(position);
                Toast.makeText(v.getContext(), "you clicked image " + model.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataModel data = list.get(position);
        holder.imgPic.setImageResource(data.getImgId());
        holder.tvDesc.setText(data.getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public View itemRView;
        public ImageView imgPic;
        public TextView tvDesc;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemRView = itemView;
            imgPic = (ImageView) itemView.findViewById(R.id.img_pic);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }

    }


}