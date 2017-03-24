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
     * 根据id获取某一项详情
     *
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

    /**
     * 消息Tab里 消息列表
     *
     * @param callback
     */
    public void getMessageList(Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[installTime]", "1489382367");
        param.put("params[max_read_announcement_id]", "1");
        param.put("params[max_unread_notification_id]", "");
        requestData(NetConstant.messagegroup_list, param, callback);
    }

    /**
     * 广场Tab里 热门
     *
     * @param callback
     */
    public void getSquareHotList(Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[dynamicTopicId]", "0");
        param.put("params[gender]", "0");
        param.put("params[tagsFilter]", "0");
        param.put("params[serviceAreaId]", "0");
        param.put("params[sort]", "0");
        requestData(NetConstant.square_hot_list, param, callback);
    }

    /**
     * 广场Tab里 此刻
     *
     * @param callback
     */
    public void getSquareCurrentList(Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[dynamicTopicId]", "0");
        param.put("params[gender]", "0");
        param.put("params[tagsFilter]", "0");
        param.put("params[serviceAreaId]", "0");
        param.put("params[sort]", "0");
        requestData(NetConstant.current_list, param, callback);
    }

    /**
     * 广场Tab里 关注
     *
     * @param callback
     */
    public void getSquareCommendList(Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[dynamicTopicId]", "0");
        param.put("params[gender]", "0");
        param.put("params[tagsFilter]", "0");
        param.put("params[serviceAreaId]", "0");
        param.put("params[sort]", "0");
        requestData(NetConstant.commend_list, param, callback);
    }

    /**
     * 首页 搜索
     *
     * @param keyWord  : 要搜索的关键字
     * @param callback
     */
    public void topicSearch(String keyWord, Callback callback) {
        Map<String, String> param = new HashMap();
        param.put("params[last_id]", "0");
        param.put("params[keyword]", keyWord);

        requestData(NetConstant.topic_search, param, callback);
    }


}
