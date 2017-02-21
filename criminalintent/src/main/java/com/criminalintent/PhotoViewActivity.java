package com.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PhotoViewActivity extends AppCompatActivity {

    private static final String PHOTO_PATH = "PHOTO_PATH";
    private ImageView imageView;
    private RelativeLayout rootView;

    public static Intent newIntent(Context context, String photoPath) {

        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(PHOTO_PATH, photoPath);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        String photoPath = getIntent().getStringExtra(PHOTO_PATH);

        rootView = (RelativeLayout) findViewById(R.id.activity_photo_view);
        imageView = (ImageView) findViewById(R.id.iv_photo_view);

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
        imageView.setImageBitmap(bitmap);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
