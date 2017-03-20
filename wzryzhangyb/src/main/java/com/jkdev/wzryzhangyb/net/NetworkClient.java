package com.jkdev.wzryzhangyb.net;

import com.jkdev.wzryzhangyb.constant.NetConstant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by KJ on 2017/3/20.
 */

public class NetworkClient {

    private NetworkManager mNetworkManager;
    private static NetworkClient instance;

    private NetworkClient() {
        mNetworkManager = NetworkManager.getInstance();
    }

    /**
     * @return
     */
    public static NetworkClient getInstance() {
        if (instance == null) {
            synchronized (NetworkClient.class) {
                if (instance == null) {
                    instance = new NetworkClient();
                }
            }
        }
        return instance;
    }

    /**
     * 请求数据基方法
     */
    private void requestData(String api, Map<String, String> param, Callback callback) {
        RetrofitInterface retrofitInterface = mNetworkManager.create(RetrofitInterface.class);
        HashMap map = mNetworkManager.putParam(api);
        map.putAll(param);
        Call call = retrofitInterface.getData(map);
        call.enqueue(callback);
    }

    /**
     * 获取轮播图数据
     */
    public void getAdListData(Callback callback) {

        Map<String, String> param = new HashMap();
        param.put("params[position]", "topic_top");
        requestData(NetConstant.ad_list, param, callback);
    }

    /**
     * 获取首页列表数据
     *
     * @param callback
     */
    public void getRecommendListData(Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[tagId]", "0");
        param.put("params[lastId]", "0");
        requestData(NetConstant.recommend_list, param, callback);
    }

    /**
     * 获取标签页 2、3、4、5、6的数据
     * tagId分别为：5、4、3、2、1
     *
     * @param callback
     */
    public void getTagListData(int tagId, Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[tagId]", tagId + "");
        param.put("params[lastId]", "0");
        requestData(NetConstant.recommend_list, param, callback);
    }

    /**
     * @param id       : 某项item的id
     * @param callback
     */
    public void getNewsById(String id, Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[id]", id);
        param.put("params[is_economize]", "0");
        param.put("params[webview]", "1");
        requestData(NetConstant.get_news_by_id, param, callback);
    }
}
