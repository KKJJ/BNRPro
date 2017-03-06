package com.nettest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterActivity extends AppCompatActivity {

    private static final int[] imgs = {R.drawable.collection_icon, R.drawable.healthy_icon,
            R.drawable.periphery_icon, R.drawable.pharmacy_icon, R.drawable.plan_icon,
            R.drawable.purchase_icon, R.drawable.questions_icon};
    private static final String[] strs = {"收藏夹", "健康档案", "健康加医", "问诊", "健康计划", "购物车", "问答模块"};

    private ListView mListView;
    private Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        mListView = (ListView) findViewById(R.id.list_view);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        List<ListViewModle> arrayList = getArrayList();
        final MyArrayAdapter arrayAdapter = new MyArrayAdapter(this, R.layout.item_layout_h, arrayList);
        mListView.setAdapter(arrayAdapter);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter.clear();
                arrayAdapter.addAll(getArrayList2());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<ListViewModle> getArrayList() {
        List<ListViewModle> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < strs.length; j++) {
                ListViewModle modle = new ListViewModle(imgs[j], strs[j]);
                list.add(modle);
            }
        }
        return list;
    }

    private List<ListViewModle> getArrayList2() {
        List<ListViewModle> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < strs.length; j++) {
                ListViewModle modle = new ListViewModle(imgs[0], strs[0]);
                list.add(modle);
            }
        }
        return list;
    }

    class MyArrayAdapter extends ArrayAdapter<ListViewModle> {
        int viewId;

        public MyArrayAdapter(Context context, int resource, List<ListViewModle> list) {
            super(context, resource, list);
            viewId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewModle item = getItem(position);
            View view;
            ViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(ArrayAdapterActivity.this).inflate(viewId, parent, false);
                holder = new ViewHolder();
                holder.img = (ImageView) view.findViewById(R.id.img_pic);
                holder.desc = (TextView) view.findViewById(R.id.tv_desc);

                view.setTag(holder);

            } else {
                view = convertView;
                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setImageResource(item.getImhId());
            holder.desc.setText(item.getDesc());

            return view;
        }

    }

    class ViewHolder {
        public ImageView img;
        public TextView desc;
    }

}

