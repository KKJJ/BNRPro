package com.nettest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);

    }

    private Uri mUri = Uri.parse("content://com.litedemo.provider/Person");

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button1:
                ContentValues values = new ContentValues();
                values.put("name", "dani");
                values.put("age", "55");
                values.put("role", "don't know");

                getContentResolver().insert(mUri, values);
                Toast.makeText(this, "add success", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:

                Cursor cursor = getContentResolver().query(mUri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String age = cursor.getString(cursor.getColumnIndex("age"));
                        String role = cursor.getString(cursor.getColumnIndex("role"));
                        LogUtil.e("--log--", "onClick: " + "name= " + name + ",age= " + age + ",role= " + role);
                    }
                    cursor.close();
                }
                break;
            default:
                break;

        }
    }
}
