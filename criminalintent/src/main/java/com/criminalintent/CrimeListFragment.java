package com.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by Kuang on 2016/12/13.
 */

public class CrimeListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private static final int REQUEST_CODE = 1001;

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private boolean mSubtitleVisible;

    private static final String TAG = "--CrimeListFragment";
    public UUID mClickCrimeId;

    private boolean isDelete = false;
    private CrimeLab mCrimeLab;

    private Callbacks mCallbacks;
    private CheckCallbacks mCheckCallbacks;

    public interface Callbacks {
        void onCrimeSelected(Crime crime);
    }

    public interface CheckCallbacks {
        void onCheckedChanged(Crime crime, boolean isChecked);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtil.e(TAG, "onAttach: " + activity.getClass().getSimpleName());
        mCallbacks = (Callbacks) activity;
        mCheckCallbacks = (CheckCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
        mCheckCallbacks = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCrimeRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        mCrimeLab = CrimeLab.getInstance(getActivity());

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_item:
                Crime crime = new Crime();
                mCrimeLab.addCrime(crime);

//                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
//                startActivity(intent);

                updateUI();
                mCallbacks.onCrimeSelected(crime);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu(); // 重绘工具栏
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        int size = mCrimeLab.getCrimes().size();
        // ①
        String subtitle = getString(R.string.subtitle_format, size);
        subtitle = getResources().getQuantityString(R.plurals.subtitle_plural, size, size);

        // ②
//        String sub = getString(R.string.subtitle_format);
//        subtitle = String.format(sub, size, size);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * 刷新界面UI
     */
    public void updateUI() {

        List<Crime> crimes = mCrimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
//            mAdapter.setOnItemClickListener(onItemClickListener); // 设置自定义接口的监听

        } else {
//            Log.i(TAG, "updateUI: isDelete----" + isDelete);
            mAdapter.setCrimes(crimes);
            if (isDelete) { // 是删除之后返回，全部刷新
                mAdapter.notifyDataSetChanged();
                isDelete = false;
            } else { // 否则只刷新改变的部分

                if (mClickCrimeId != null) {
                    Crime crime = mCrimeLab.getCrime(mClickCrimeId);
                    int position = crimes.indexOf(crime); // 之前一直返回-1，就是列表里找不到--没重写eauals和hashcode方法！！！！！！！！！

                    // 为什么视觉延迟？？
//            mAdapter.notifyItemChanged(position);
                    mAdapter.notifyItemChanged(position, 1);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        }

        updateSubtitle();
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View v, int pos) {
            Toast.makeText(getActivity(), "click " + pos, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onItemLongClick(View v, int pos) {
            Toast.makeText(getActivity(), "longclick " + pos, Toast.LENGTH_LONG).show();

            final Crime crime = mCrimeLab.getCrimes().get(pos);

            deleteOperation(crime); // 自定义监听方式的 删除操作
        }
    };

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);

        void onItemLongClick(View v, int pos);
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        private OnItemClickListener mOnItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(final CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> crimes) {
            mCrimes = crimes;
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this); // 点击监听
            itemView.setOnLongClickListener(this); // 长按监听
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());

            mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mCrime.setSolved(isChecked);
                    mCrimeLab.updateCrime(mCrime);
                    mCheckCallbacks.onCheckedChanged(mCrime, isChecked); /////////////////
                }
            });
        }

        @Override
        public void onClick(View v) {
            mClickCrimeId = mCrime.getId();
//            Intent intent = CrimePagerActivity.newIntent(getActivity(), mClickCrimeId);
//            startActivityForResult(intent, REQUEST_CODE);
            mCallbacks.onCrimeSelected(mCrime);
        }

        @Override
        public boolean onLongClick(View v) {
            deleteOperation(mCrime); // 删除操作
            return true;
        }

    }

    /**
     * 删除操作
     *
     * @param c
     */
    private void deleteOperation(final Crime c) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("操作提示").setMessage("是否确认删除?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCrimeLab.deleteCrime(c.getId());
                mAdapter.setCrimes(mCrimeLab.getCrimes());
                mAdapter.notifyDataSetChanged(); // 刷新数据
                updateSubtitle(); // 刷新工具栏
            }
        }).create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE) {
            isDelete = CrimeFragment.getIsRefreshAll(data);
            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
        }
    }


}
