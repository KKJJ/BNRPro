package com.criminalintent;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Kuang on 2017/2/20.
 */

public class PhotoViewFragment extends DialogFragment {

    private static final String TAG = "PhotoViewFragment";
    private static final String PHOTO_PATH = "photoPath";

    public static PhotoViewFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString(PHOTO_PATH, path);
        PhotoViewFragment fragment = new PhotoViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.e(TAG, "onCreate: onCreate");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LogUtil.e(TAG, "onCreateDialog: ");
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.e(TAG, "onCreateView: ");

        View view = inflater.inflate(R.layout.dialog_photo_view, container);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_photo_view);
        String photoPath = getArguments().getString(PHOTO_PATH);

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
        imageView.setImageBitmap(bitmap);

        return view;
    }

}
