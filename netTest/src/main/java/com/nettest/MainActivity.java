package com.nettest;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 包括retrofit和okhttp测试代码
 * <p>
 * 以及
 * <p>
 * percent布局测试
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PERMISSION_CODE_CALL_PHONE = 0;
    private static final int REQUEST_PERMISSION_CODE_READ_CONTACTS = 1;

    private Button btnRetrofit;
    private Button btnokHttp;
    private Button btnPercent;
    private Button btnArrayAdapter;
    private Button btnStaggeredLayoutManager;
    private Button btnChatLbtnChatLayout;
    private Button btnProcessDialog;
    private Button btnRequestPetmission;
    private Button btnBottomSheetDialog;
    private Button btnContentProvider;
    private Button btnContentProvider2;
    private Button btnWebView;
    private AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRetrofit = (Button) findViewById(R.id.btn_retrofit);
        btnokHttp = (Button) findViewById(R.id.btn_okhttp);
        btnPercent = (Button) findViewById(R.id.btn_percent);
        btnArrayAdapter = (Button) findViewById(R.id.btn_arrayAdapter);
        btnStaggeredLayoutManager = (Button) findViewById(R.id.btn_staggeredLayoutManger);
        btnChatLbtnChatLayout = (Button) findViewById(R.id.btn_chatLayout);
        btnProcessDialog = (Button) findViewById(R.id.btn_testProgressDialog);
        btnRequestPetmission = (Button) findViewById(R.id.btn_testRequestPermission);
        btnBottomSheetDialog = (Button) findViewById(R.id.btn_testBottomSheetDialog);
        btnContentProvider = (Button) findViewById(R.id.btn_testContentProvider);
        btnContentProvider2 = (Button) findViewById(R.id.btn_testContentProvider2);
        btnWebView = (Button) findViewById(R.id.btn_testWebView);
        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_ctv);

        btnRetrofit.setOnClickListener(this);
        btnokHttp.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnArrayAdapter.setOnClickListener(this);
        btnStaggeredLayoutManager.setOnClickListener(this);
        btnChatLbtnChatLayout.setOnClickListener(this);
        btnProcessDialog.setOnClickListener(this);
        btnRequestPetmission.setOnClickListener(this);
        btnBottomSheetDialog.setOnClickListener(this);
        btnContentProvider.setOnClickListener(this);
        btnContentProvider2.setOnClickListener(this);
        btnWebView.setOnClickListener(this);

        String[] strs = {"aa", "aaa", "ab", "abc", "aac"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs);
        mAutoCompleteTextView.setAdapter(arrayAdapter);

    }

    // http://www.kuaidi100.com/query ?type=zhongtong&postid=428763978880
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_retrofit:
//                testRetrofit();

                testRetrofit1();
                break;
            case R.id.btn_okhttp:
//                testokHtpp();

                testOkhttp();
                break;
            case R.id.btn_percent:
                startActivity(new Intent(this, PercentActivity.class));

                break;
            case R.id.btn_arrayAdapter:
                startActivity(new Intent(this, ArrayAdapterActivity.class));

                break;
            case R.id.btn_staggeredLayoutManger:
                startActivity(new Intent(this, StaggeredGridLayoutManagerActivity.class));

                break;
            case R.id.btn_chatLayout:
                startActivity(new Intent(this, ChatLayoutActivity.class));

                break;
            case R.id.btn_testProgressDialog:
                showProgressDialog();

                break;
            case R.id.btn_testRequestPermission:
                callPhone();

                break;
            case R.id.btn_testBottomSheetDialog:

                BottomSheetDialog sheetDialog = new BottomSheetDialog(this);
                sheetDialog.setTitle("标题哦");
                sheetDialog.setContentView(R.layout.activity_percent);

                sheetDialog.show();

                break;
            case R.id.btn_testContentProvider: // 读取电话簿
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_CODE_READ_CONTACTS);
                } else {
                    readContact();
                }
                break;
            case R.id.btn_testContentProvider2: // 自定义的 ContentProvider
                startActivity(new Intent(this, ContentProviderActivity.class));
                break;
            case R.id.btn_testWebView: // 测试WebView加载纯html代码
                Intent intent = new Intent(this, WebViewActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    private void readContact() {
        int i = 0;
        ArrayList<String> contactsList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)); // 得到的都是联系人的序号id
                    i++;
                    LogUtil.e(TAG, "onClick: " + "name = " + name + ",number = " + number);

                    String data = name + "\n" + number;
                    contactsList.add(data);
                    if (i == 5) {
                        break;
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        Intent intent = ContactsActivity.newIntent(this, contactsList);
        startActivity(intent);
        LogUtil.e(TAG, "readContact: " + "success");
    }

    /**
     * 通过 打电话 测试 6.0运行时权限
     */
    private void callPhone() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION_CODE_CALL_PHONE);
        } else {
            call();
        }

    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_PERMISSION_CODE_CALL_PHONE:
                if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你拒绝了打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_PERMISSION_CODE_READ_CONTACTS:
                if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContact();
                } else {
                    Toast.makeText(this, "你拒绝了读取联系人的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }

    /**
     * 显示等待progressDialog
     */
    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("这是Title");
        progressDialog.setMessage("这是Message");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    /**
     * 测试 okHttp
     */
    private void testokHtpp() {
        String url = baseUrl + "query?type=" + type + "&postid=" + postid;


        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("type", type).add("postid", postid).build();
        Request request = new Request.Builder().url(url).build(); // get 请求
//        Request request = new Request.Builder().url(baseUrl + "query").post(formBody).build(); // post 请求
        LogUtil.e(TAG, "testokHtpp: url--" + request.url());


        okhttp3.Call call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "okhttp 请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "okhttp 请求成功", Toast.LENGTH_SHORT).show();
                    }
                });
                String string = response.body().string();

                LogUtil.e(TAG, "onResponse: body--" + response.toString());

                LogUtil.e(TAG, "onResponse: ================================================");
                LogUtil.e(TAG, "onResponse: ================================================");
                Gson gson = new Gson();

                Data data = gson.fromJson(string, Data.class);
                LogUtil.e(TAG, "onResponse: data--" + data.data.get(0));

            }
        });
    }

    private static final String TAG = "--MainActivity";
    private String baseUrl = "http://www.kuaidi100.com/";
    private String type = "zhongtong";
    private String postid = "428763978880";

    /**
     * 测试 retrofit
     */
    private void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Data> call = retrofitService.getData(type, postid);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(MainActivity.this, "retrofit 请求成功", Toast.LENGTH_SHORT).show();
                LogUtil.e(TAG, "onResponse: " + response.toString());
                LogUtil.e(TAG, "onResponse: " + response.body().data.get(0).toString());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(MainActivity.this, "retrofit 请求失败", Toast.LENGTH_SHORT).show();
                LogUtil.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public interface RetrofitService {
        @GET("query")
        Call<Data> getData(@Query("type") String type, @Query("postid") String id);
    }

    private void testRetrofit1() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<Data> call = retrofitInterface.retrofit(type, postid);
        LogUtil.e(TAG, "--testRetrofit1: " + retrofit.baseUrl());
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                LogUtil.e(TAG, "onResponse: res:" + response.body());
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    interface RetrofitInterface {

        @GET("query)")
        Call<Data> retrofit(@Field("type") String type, @Field("postid") String id);
    }


    private void testOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("type", type).add("postid", postid).build();
        final Request request = new Request.Builder().url(baseUrl + "query").post(formBody).build();
        okhttp3.Call call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                LogUtil.e(TAG, "onResponse: res okhttp: " + response.body().string());
                // Gson解析

            }
        });
    }

}































