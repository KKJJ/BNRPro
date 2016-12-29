package com.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Kuang on 2016/12/13.
 */

public class CrimeFragment extends Fragment {

    private static final String TAG = "--CrimeFragment";
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final String IS_REFRESH_TOTAL = "is_delete";
    private static final int REQUEST_DATE = 1000;
    private static final int REQUEST_TIME = 1001;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateTimeShowButton;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;
    private Button mDeleteButton;
    private Button mConfirmButton;

    /**
     * @param crimeId
     * @return
     */
    public static CrimeFragment newInstance(UUID crimeId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(args);

        return crimeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        mCrime = crimeLab.getCrime(crimeId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
                Log.d(TAG, "onTextChanged: " + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateTimeShowButton = (Button) v.findViewById(R.id.crime_date_time_show);
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mTimeButton = (Button) v.findViewById(R.id.crime_time);
        updateDate();
//        mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                TimePickerFragment timeDialog = TimePickerFragment.newInstance(mCrime.getDate());
                timeDialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                timeDialog.show(fragmentManager, DIALOG_TIME);

            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mDeleteButton = (Button) v.findViewById(R.id.crime_delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteConfirm();
            }
        });

        mConfirmButton = (Button) v.findViewById(R.id.crime_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CrimeLab.getInstance(getActivity()).addCrime(mCrime);
//                Intent data = new Intent();
//                data.putExtra(IS_REFRESH_TOTAL, true);
//                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        });

        mTitleField.setText(mCrime.getTitle());
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        return v;
    }

    private void deleteConfirm() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("操作提示").setMessage("是否确认删除?").setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CrimeLab.getInstance(getActivity()).deleteCrime(mCrime.getId());
                Intent data = new Intent();
                data.putExtra(IS_REFRESH_TOTAL, true);
                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        }).create().show();
    }

    /**
     * @param data
     * @return
     */
    public static boolean getIsDelete(Intent data) {
        return data.getBooleanExtra(IS_REFRESH_TOTAL, false);
    }

    private void updateDate() {
        mDateTimeShowButton.setText(mCrime.getDate().toString());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = DatePickerFragment.getResultDate(data);
            mCrime.setDate(date);
            updateDate();
        }
        if (requestCode == REQUEST_TIME) {
            Date date = TimePickerFragment.getResultDate(data);
            mCrime.setDate(date);
            updateDate();
        }

    }

}
