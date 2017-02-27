package com.criminalintent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

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
    private static final int REQUEST_CONTACT = 1002;
    private static final int REQUEST_PHOTO = 1003;
    private static final String PHOTO_VIEW = "PHOTO_VIEW";

    public Crime mCrime;
    private EditText mTitleField;
    private Button mDateTimeShowButton;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    private Button mDeleteButton;
    private Button mConfirmButton;

    private Button mChooseSuspectButton;
    private Button mSendReportButton;
    private Button mCallSuspectButton;

    private CrimeLab mCrimeLab;

    private ImageView mPhotoView;
    private ImageButton mPhotoButton;
    private File mPhotoFile;

    private Uri uri;

    private Callbacks mCallbacks;

    public interface Callbacks {
        void onCrimeUpdated(Crime crime);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtil.e(TAG, "onAttach: " + activity.getClass().getSimpleName());
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void updateCrime() {
        CrimeLab.getInstance(getActivity()).updateCrime(mCrime);
        mCallbacks.onCrimeUpdated(mCrime);
    }

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
        mCrimeLab = CrimeLab.getInstance(getActivity());
        mCrime = mCrimeLab.getCrime(crimeId);
        mPhotoFile = mCrimeLab.getPhotoFile(mCrime);


    }

    @Override
    public void onPause() {
        super.onPause();

        mCrimeLab.updateCrime(mCrime);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
                Log.i(TAG, "onTextChanged: " + s.toString());
                updateCrime();
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
                updateCrime();
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
//               mCrimeLab.addCrime(mCrime);
//                Intent data = new Intent();
//                data.putExtra(IS_REFRESH_TOTAL, true);
//                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//        pickContact.addCategory(Intent.CATEGORY_HOME); // 模拟无联系人应用的机器

        mChooseSuspectButton = (Button) v.findViewById(R.id.crime_choose_suspect);
        mSendReportButton = (Button) v.findViewById(R.id.crime_send_report);
        mChooseSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });
        mSendReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND)
//                        .setType("text/plain")
//                        .putExtra(Intent.EXTRA_TEXT, getCrimeReport())
//                        .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject));
//                intent = Intent.createChooser(intent, getString(R.string.send_report)); // 每次都弹出选择框
//                startActivity(intent);

                // 使用 ShareCompat.IntentBuilder 实现
                Intent chooserIntent = ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText(getCrimeReport())
                        .setSubject(getString(R.string.crime_report_subject))
                        .createChooserIntent(); // 每次都弹出选择框
//                .getIntent()

                startActivity(chooserIntent);

            }
        });

        mCallSuspectButton = (Button) v.findViewById(R.id.crime_call_suspect);
        mCallSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startCallSuspectActivity(); // 启动打电话
//                sendSMS("12345", "i'm mr.k,who are you?"); // 发短信
            }
        });

        mTitleField.setText(mCrime.getTitle());
        mSolvedCheckBox.setChecked(mCrime.isSolved());

        if (mCrime.getSuspect() != null) {
            mChooseSuspectButton.setText(mCrime.getSuspect());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            mChooseSuspectButton.setEnabled(false);
        }

        // 拍照
        picture(v, packageManager);

        // 加密练习
//         secretTest();

        return v;
    }

    private void secretTest() {
        try {
            // 密匙
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encoded = secretKey.getEncoded();

            // 加密
            SecretKeySpec des = new SecretKeySpec(encoded, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, des);
            byte[] data = "this is secret data".getBytes();

            byte[] doFinal = cipher.doFinal(data); // 加密后的数据
            LogUtil.e(TAG, "onCreateView: " + doFinal.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    private void picture(View v, PackageManager packageManager) {
        mPhotoView = (ImageView) v.findViewById(R.id.photo_view);
        mPhotoButton = (ImageButton) v.findViewById(R.id.crime_photo_button);

        ViewTreeObserver observer = mPhotoView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO : set image
                updatePhotoView();
            }
        });

        // TODO take photo
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureIntent.resolveActivity(packageManager) != null;

        if (canTakePhoto) {

            if (Build.VERSION.SDK_INT >= 24) { // API大于24需要特殊处理

                uri = FileProvider.getUriForFile(getContext(), "com.criminalintent.fileprovider", mPhotoFile);
                Log.i(TAG, "onCreateView: 当前机器API大于24");
            } else {
                uri = Uri.fromFile(mPhotoFile);
                Log.i(TAG, "onCreateView: 当前机器API小于24");
            }

        } else {
            Snackbar.make(new View(getContext()), "该机器没有相机功能", Snackbar.LENGTH_LONG).show();
            Log.i(TAG, "该机器没有相机功能 ");
        }

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e(TAG, "onClick: uri: " + uri);
                //  file:///storage/emulated/0/Android/data/com.criminalintent/files/Pictures/IMG_754e2b04-7862-4cc8-9c79-1b1236b2b010.jpg
                // content://com.criminalintent.fileprovider/my_images/Android/data/com.criminalintent/files/Pictures/IMG_1624b919-0ba1-4554-9d4f-72b338b357a8.jpg

                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(captureIntent, REQUEST_PHOTO);
                Log.i(TAG, "成功调起相机");
            }
        });
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhotoFile == null || !mPhotoFile.exists()) {
                    ToastUtil.showToast(getContext(), "照片无法展示，或因不存在");
                    return;
                }

//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                PhotoViewFragment photoViewFragment = PhotoViewFragment.newInstance(mPhotoFile.getPath());
//                photoViewFragment.show(fragmentManager, PHOTO_VIEW);

                Intent intent = PhotoViewActivity.newIntent(getContext(), mPhotoFile.getPath());
                startActivity(intent);

            }
        });
    }

    /**
     * 调起打电话界面
     */

    private void startCallSuspectActivity() {
        if (mCrime.getSuspect() == null) {
            Toast.makeText(getActivity(), "暂无嫌疑人", Toast.LENGTH_LONG).show();
            return;
        }
        String contactId = null;
        String number = null;
        String suspect = null;
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        try {
            if (cursor.getColumnCount() == 0) {
                return;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                suspect = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (mCrime.getSuspect().equals(suspect)) {
                    contactId = id;
                    break;
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        if (contactId == null) {
            return;
        }

        // 注意区分：ContactsContract.CommonDataKinds.Phone.CONTACT_ID 　　　 ContactsContract.CommonDataKinds.Phone._ID
        Cursor cursor1 = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
        try {
            if (cursor1.getColumnCount() == 0) {
                return;
            }
            cursor1.moveToFirst();
            number = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        String idd = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)); // 错误的--记得是[CONTACT_ID]
        } finally {
            cursor1.close();
        }
        if (number == null) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
    }


    /**
     * 直接调用短信接口发短信
     *
     * @param phoneNumber
     * @param message
     */
    public void sendSMS(String phoneNumber, String message) {
        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
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

                mCrimeLab.deleteCrime(mCrime.getId());
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
    public static boolean getIsRefreshAll(Intent data) {
        return data.getBooleanExtra(IS_REFRESH_TOTAL, false);
    }

    private void updateDate() {

        mDateTimeShowButton.setText(mCrime.getDate().toString());
    }

    private String getCrimeReport() {

        String solvedString = null;
        if (mCrime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
        CharSequence dateString = DateFormat.format(dateFormat, mCrime.getDate());

        String suspect = mCrime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }

        return getString(R.string.crime_report, mCrime.getTitle(), dateString, solvedString, suspect);

    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE || requestCode == REQUEST_TIME) {
            Date date = requestCode == REQUEST_DATE ? DatePickerFragment.getResultDate(data) : TimePickerFragment.getResultDate(data);
            mCrime.setDate(date);
            updateCrime(); //////////////////////
            updateDate();
        } else if (requestCode == REQUEST_CONTACT && data != null) {
            Uri uri = data.getData();
            String[] queryFields = {ContactsContract.Contacts.DISPLAY_NAME};
//            Cursor cursor = getActivity().getContentResolver().query(uri, queryFields, null, null, null);
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);

            try {
                if (cursor.getColumnCount() == 0) {
                    return;
                }
                cursor.moveToFirst();
                String suspect = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Log.i(TAG, "onActivityResult: select--id----" + id);

                mCrime.setSuspect(suspect);
                updateCrime(); //////////////////////
                mChooseSuspectButton.setText(suspect);
                mCrimeLab.updateCrime(mCrime);

            } finally {
                cursor.close();
            }
        } else if (requestCode == REQUEST_PHOTO) {
            updateCrime(); //////////////////////
            updatePhotoView();
        }

    }

    public void onCheckedChange(boolean isChecked) {
        mSolvedCheckBox.setChecked(isChecked);
    }

}
