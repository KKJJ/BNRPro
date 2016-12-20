package com.criminalintent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private UUID mClickCrimeId;

    private boolean isDelete = false;

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
                CrimeLab.getInstance(getActivity()).adddCrime(crime);
                Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        int size = crimeLab.getCrimes().size();
        // ①
        String subtitle = getString(R.string.subtitle_format, size);

        // ②
        String sub = getString(R.string.subtitle_format);
        subtitle = String.format(sub, size);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * 刷新界面UI
     */
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
//            mAdapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(View v, int pos) {
//                    Toast.makeText(getActivity(), "click " + pos, Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onItemLongClick(View v, int pos) {
//                    Toast.makeText(getActivity(), "longclick " + pos, Toast.LENGTH_LONG).show();
//                    ifDelete(pos);
//                }
//            });
        } else {
            Log.d(TAG, "updateUI: isDelete----" + isDelete);
            if (isDelete) { // 是删除之后返回，全部刷新
                mAdapter.notifyDataSetChanged();
                isDelete = false;
            } else { // 否则只刷新改变的部分

                Crime crime = crimeLab.getCrime(mClickCrimeId);
                int position = crimes.indexOf(crime);

                // 为什么视觉延迟？？
//            mAdapter.notifyItemChanged(position);
                mAdapter.notifyItemChanged(position, 1);
            }
        }

        updateSubtitle();
    }

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
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
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
                }
            });
        }

        @Override
        public void onClick(View v) {

            mClickCrimeId = mCrime.getId();
//            Intent intent = CrimeActivity.newIntent(getActivity(), mClickCrimeId);
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mClickCrimeId);
            startActivityForResult(intent, REQUEST_CODE);

        }

        @Override
        public boolean onLongClick(View v) {
            ifRealDelete();
            return true;
        }

        private void ifRealDelete() {
            Toast.makeText(getActivity(), "111111", Toast.LENGTH_LONG).show();
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("重要操作提示").setMessage("是否确认删除?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CrimeLab.getInstance(getActivity()).deleteCrime(mCrime.getId());
                    mAdapter.notifyDataSetChanged(); // 刷新数据
                }
            }).create().show();
        }
    }

    private void ifDelete(int pos) {
        final CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        final Crime crime = crimeLab.getCrimes().get(pos);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("重要操作提示").setMessage("是否确认删除?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                crimeLab.deleteCrime(crime.getId());
                mAdapter.notifyDataSetChanged(); // 刷新数据
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
            isDelete = CrimeFragment.getIsDelete(data);
            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_LONG).show();
        }
    }

}
