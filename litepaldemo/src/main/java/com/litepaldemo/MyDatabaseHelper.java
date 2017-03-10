package com.litepaldemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kuang on 2017/3/10.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE = "create table Person(" +
            "id integer primary key autoincrement" +
            "name varchar" +
            "age integer" +
            "role varchar" +
            ")";

    private Context mContext;

    public MyDatabaseHelper(Context context) {
        this(context, "SQL_TEST.db", null, 1);
    }

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }

}
