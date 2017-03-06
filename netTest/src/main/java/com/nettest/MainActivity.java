package com.nettest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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

    private Button btnRetrofit;
    private Button btnokHttp;
    private Button btnPercent;
    private Button btnArrayAdapter;
    private Button btnStaggeredLayoutManager;
    private Button btnChatLbtnChatLayout;
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
        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.auto_ctv);

        btnRetrofit.setOnClickListener(this);
        btnokHttp.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnArrayAdapter.setOnClickListener(this);
        btnStaggeredLayoutManager.setOnClickListener(this);
        btnChatLbtnChatLayout.setOnClickListener(this);

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
            default:
                break;
        }
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
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Data> call = retrofitService.getData(type, postid);
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
        @GET("query")
        Call<Data> retrofit(@Query("type") String type, @Query("postid") String id);
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































