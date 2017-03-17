package com.jkdev.wzryzhangyb.net;

import com.jkdev.wzryzhangyb.bean.Data;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络封装类-- 单例
 * <p>
 * Created by Kuang on 2017/3/17.
 */

public class NetworkManager {

    String url = "http://yxzj.service.zhangyoubao.com/service/rest?api=checksig.checkclock&game=yxzj&userId=0&platformVersion=106000";
    private static final String baseUrl = "http://yxzj.service.zhangyoubao.com/";

    private Retrofit mRetrofit;
    private static NetworkManager instance;

    /**
     * 做网络初始化工作
     */
    private NetworkManager() {
        mRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    /**
     * @return
     */
    public static NetworkManager getInstance() {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public HashMap putParam(String param) {
        HashMap dataMap = getDataMap();
        dataMap.put("api", param);
        return dataMap;
    }

    private HashMap getDataMap() {
        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("api", "checksig.checkclock");
        hashMap.put("apiVersion", "v1");
        hashMap.put("deviceId", "SZi5StsLWDdWACOMA6fegtBFGYehxFqe01qEVyhi8A7KOAVLJUwl8e6H1ngMtAn77PKP/gseqF0AYdqcc+D8RQ==");
        hashMap.put("game", "yxzj");
        hashMap.put("nonce", "287908");
        hashMap.put("os", "android");
        hashMap.put("osVersion", "19");
        hashMap.put("platform", "android");
        hashMap.put("platformVersion", "106000");
        hashMap.put("secretId", "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA");
        hashMap.put("secretSignature", "ZR1qGjWwyEYINOL+AJtbKtJBHFM=");
        hashMap.put("secretVersion", "v1.0");
        hashMap.put("sign", "");
        hashMap.put("time", System.currentTimeMillis() + "");
        hashMap.put("userId", "0");
        hashMap.put("userToken", "");
        return hashMap;
    }

    /**
     * 暂未用到
     *
     * @return
     */
    private Data getDataBean() {
        Data data = new Data();
        data.setApi("checksig.checkclock");
        data.setApiVersion("v1");
        data.setDeviceId("SZi5StsLWDdWACOMA6fegtBFGYehxFqe01qEVyhi8A7KOAVLJUwl8e6H1ngMtAn77PKP/gseqF0AYdqcc+D8RQ==");
        data.setGame("yxzj");
        data.setNonce("287908");
        data.setOs("android");
        data.setOsVersion("19");
        data.setPlatform("android");
        data.setPlatformVersion("106000");
        data.setSecretId("AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA");
        data.setSecretSignature("ZR1qGjWwyEYINOL+AJtbKtJBHFM=");
        data.setSecretVersion("v1.0");
        data.setSign(null);
        data.setTime(System.currentTimeMillis() + "");
        data.setUserId("0");
        data.setUserToken(null);

        return data;
    }


}
