package com.litepaldemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private static final int TABLE_DIR = 0;
    private static final int TABLE_ITEM = 1;

    private static UriMatcher mUriMatcher;
    private MyDatabaseHelper mHelper;
    private static final String AUTHORITY = "Person";
    private static final String TABLE_NAME = "Person";

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI("com.litedemo.provider", "person", TABLE_DIR);
        mUriMatcher.addURI("com.litedemo.provider", "person/#", TABLE_ITEM);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mHelper = new MyDatabaseHelper(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {

        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:
                return "vnd.android.cursor.dir/vnd.com.litedemo.provider.Person";

            case TABLE_ITEM:

                return "vnd.android.cursor.item/vnd.com.litedemo.provider.Person";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:
            case TABLE_ITEM:
                long insertId = db.insert(TABLE_NAME, null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/person/" + insertId);
                break;
            default:
                break;
        }
        return uriReturn;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:

                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TABLE_ITEM:
                String bookId = uri.getPathSegments().get(0);
                cursor = db.query(TABLE_NAME, projection, "id = ?", new String[]{bookId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        switch (mUriMatcher.match(uri)) {
            case TABLE_DIR:

                break;
            case TABLE_ITEM:

                break;
            default:
                break;
        }
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
