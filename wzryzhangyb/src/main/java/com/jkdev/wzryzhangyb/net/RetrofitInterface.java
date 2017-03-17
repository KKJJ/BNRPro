package com.jkdev.wzryzhangyb.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kuang on 2017/3/17.
 */

public interface RetrofitInterface {


    @FormUrlEncoded
    @POST("service/rest")
    Call<String> getData(@FieldMap Map<String, String> map);

}
