package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.NewsBean;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.jkdev.wzryzhangyb.utils.TimeUtils;

import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 点击某一条 展示页
 * <p>
 * Created by KJ on 2017/3/20.
 */

public class NewsContentFragment extends SupportFragment {

    private static final String TAG = "--NewsContentFragment";
    private static final String NEWS_ID = "NEWS_ID";
    private String encoding = "UTF-8";
    private String mimeType = "text/html";
    private NetworkClient mNetworkClient;
    private Gson mGson;
    private String newsId;
    private WebView mWebView;


    public static NewsContentFragment getInstance(String id) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);
        mNetworkClient = NetworkClient.getInstance(); // 初始网络manager
        mGson = new Gson();
        initView(view);
        return view;
    }

    private void initView(View view) {
        newsId = getArguments().getString(NEWS_ID);

        mWebView = (WebView) view.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true); // 启用js
        mWebView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        mWebView.setWebViewClient(new WebViewClient());

        getNewFromNet();

//  //          mWebView.loadData(URLEncoder.encode(webData, encoding), mimeType, encoding); // 不支持图片
        // 原内容img src经过base64编码，不能显示 ，此处将其转换为原始图片地址
//        htmlDataImg = replaceDataSrc(htmlDataImg);  // 图片展示类型
//        mWebView.loadDataWithBaseURL(null, htmlDataImg, mimeType, encoding, null);
    }

    /**
     * 根据id从网络获取数据
     */
    private void getNewFromNet() {
        mNetworkClient.getNewsById(newsId, new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                NewsBean.DataEntity data = mGson.fromJson(responseData, NewsBean.class).getData();

                String title = data.getTitle();
                String author = data.getAuthor();
                String publish_time = TimeUtils.millisToStringDate(Integer.parseInt(data.getPublish_time()), "yyyy-MM-dd HH:mm");
                String cover_url = data.getCover_url();
                String content = data.getContent();

                if (data.getVideos() != null) {
                    if (data.getVideos().size() == 0) { // 说明是非视频类型，即要将src 和 data-src值互换，否则不换
                        content = replaceDataSrc(content);  // 图片展示类型
                    } else {
                        content = content.replace("src=\"data:image/gif;base64", "src=\"" + cover_url + "\" aaa=\"data:image/gif;base64");
                    }
                }
                mWebView.loadDataWithBaseURL(null, content, mimeType, encoding, null);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    /**
     * 将src 和 data-src值互换
     * 原内容img src经过base64编码，不能显示 ，此处将其转换为原始图片地址
     *
     * @param htmlData
     * @return
     */
    private String replaceDataSrc(String htmlData) {
        htmlData = htmlData.replace("data-src=", "replaceData=");
        htmlData = htmlData.replace("src=", "data-src=");
        htmlData = htmlData.replace("replaceData=", "src=");
        LogUtil.e(TAG, "onCreate: " + htmlData);
        return htmlData;
    }

}
