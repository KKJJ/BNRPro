package com.jkdev.wzryzhangyb.ui.fragment.first;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jkdev.wzryzhangyb.R;
import com.jkdev.wzryzhangyb.bean.NewsBean;
import com.jkdev.wzryzhangyb.net.NetworkClient;
import com.jkdev.wzryzhangyb.net.download.DownloadService;
import com.jkdev.wzryzhangyb.utils.DeviceUtils;
import com.jkdev.wzryzhangyb.utils.LogUtil;
import com.jkdev.wzryzhangyb.utils.TimeUtils;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * 点击某一条 展示页
 * <p>
 * Created by KJ on 2017/3/20.
 */

public class NewsContentFragment extends SupportFragment implements View.OnClickListener {

    private static final String TAG = "--NewsContentFragment";
    private static final String NEWS_ID = "NEWS_ID";
    private String encoding = "UTF-8";
    private String mimeType = "text/html";
    private NetworkClient mNetworkClient;
    private Gson mGson;
    private String newsId;

    // 标题栏
    private LinearLayout llActionRoot;
    private ImageView btnImageBack;
    private ImageView btnImageOption;
    private TextView tvActionTitle;

    // 正文
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvTime;
    private WebView mWebView;

    private TextView tvGoodCount; // 点赞数
    private TextView tvDownCount; // down数
    private TextView tvCommentCount; // 评论数

    private LinearLayout llContentGood;
    private LinearLayout llContentDown;
    private LinearLayout llContentComment;

    private NewsBean.DataEntity mData; // 本页数据
    private JCVideoPlayerStandard mJcVideoPlayer;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

    };

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

        llActionRoot = (LinearLayout) view.findViewById(R.id.include); // 此处要用include布局的id，因为在include中再指定id的时候会将原来根布局的id替代
        btnImageBack = (ImageView) view.findViewById(R.id.img_action_back);
        btnImageOption = (ImageView) view.findViewById(R.id.img_action_setting);
        tvActionTitle = (TextView) view.findViewById(R.id.tv_action_title);

        tvActionTitle.setText(R.string.news_content_title);
        btnImageBack.setOnClickListener(this);
        btnImageOption.setOnClickListener(this);
        btnImageOption.setImageDrawable(getResources().getDrawable(R.drawable.btn_more_selector));

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvAuthor = (TextView) view.findViewById(R.id.tv_author);
        tvTime = (TextView) view.findViewById(R.id.tv_time);

        tvGoodCount = (TextView) view.findViewById(R.id.tv_good_count);
        tvDownCount = (TextView) view.findViewById(R.id.tv_down_count);
        tvCommentCount = (TextView) view.findViewById(R.id.tv_comment);

        llContentGood = (LinearLayout) view.findViewById(R.id.ll_content_good);
        llContentDown = (LinearLayout) view.findViewById(R.id.ll_content_down);
        llContentComment = (LinearLayout) view.findViewById(R.id.ll_content_comment);
        llContentGood.setOnClickListener(this);
        llContentDown.setOnClickListener(this);
        llContentComment.setOnClickListener(this);

        mWebView = (WebView) view.findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true); // 启用js
        mWebView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
        mWebView.setWebViewClient(new WebViewClient());

        mJcVideoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.video_player);

        getNewFromNet();

//  //          mWebView.loadData(URLEncoder.encode(webData, encoding), mimeType, encoding); // 不支持图片
        // 原内容img src经过base64编码，不能显示 ，此处将其转换为原始图片地址
//        htmlDataImg = replaceDataSrc(htmlDataImg);  // 图片展示类型
//        mWebView.loadDataWithBaseURL(null, htmlDataImg, mimeType, encoding, null);
    }

    /**
     * 初始化VideoView组件
     */
    private void initVideoView(String videoUrl, String videoTitle, String videoImg) {

        mJcVideoPlayer.setUp(videoUrl
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoTitle);
        Glide.with(_mActivity).load(videoImg).into(mJcVideoPlayer.thumbImageView);
    }

    private void setVideoVisiable(boolean visiable) {
        if (visiable) {
            mJcVideoPlayer.setVisibility(View.VISIBLE);
        } else {
            mJcVideoPlayer.setVisibility(View.GONE);
        }
    }

    /**
     * 根据id从网络获取数据
     */
    private void getNewFromNet() {
        mNetworkClient.getNewsById(newsId, new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String responseData = response.body().toString();
                mData = mGson.fromJson(responseData, NewsBean.class).getData();
                LogUtil.d(TAG, "getNewFromNet onResponse: ");

                String title = mData.getTitle();
                String author = mData.getAuthor();
                String publish_time = TimeUtils.millisToStringDate(mData.getPublish_time());
                String cover_url = mData.getCover_url();
                String content = mData.getContent();

                String comment_count = mData.getComment_count() + "";
                String good_count = mData.getGood_count();
                String down_count = mData.getDown_count();

                tvTitle.setText(title);
                tvAuthor.setText(author);
                tvTime.setText(publish_time);

                tvCommentCount.setText(comment_count);
                tvGoodCount.setText(good_count);
                tvDownCount.setText(down_count);

                if (mData.getVideos() != null) {
                    if (mData.getVideos().size() == 0) { // 说明是非视频类型，即要将src 和 data-src值互换，否则不换
                        content = replaceDataSrc(content);  // 图片展示类型
                    } else {
                        content = content.replace("src=\"data:image/gif;base64", "src=\"" + cover_url + "\" aaa=\"data:image/gif;base64");
                    }
                }
                mWebView.loadDataWithBaseURL(null, content, mimeType, encoding, null);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.e(TAG, "getNewFromNet onFailure: " + t.getMessage());
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

        return htmlData;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.img_action_back:
                pop();
                JCVideoPlayer.releaseAllVideos();
                break;
            case R.id.img_action_setting:
//                showPopMenu();
                showPopWindow();
                break;
            case R.id.ll_operation_collect: // 播放视频
                if (mData != null) {
                    dismissPopWindow();
                    List<NewsBean.DataEntity.VideosBean> videos = mData.getVideos();
                    if (videos == null || videos.size() == 0) {
                        Toast.makeText(_mActivity, "这里没有视频", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    setVideoVisiable(true);
                    String sd = videos.get(0).getVideo_urls().getSd();
                    Log.d(TAG, "video url: " + sd);
                    initVideoView(sd, "", mData.getCover_url());
                }
                Toast.makeText(_mActivity, R.string.operation_collect, Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_operation_share:
                // 下载视频
                Intent intent = new Intent(_mActivity, DownloadService.class);
                _mActivity.startService(intent); // 启动服务
                _mActivity.bindService(intent, connection, BIND_AUTO_CREATE); // 绑定服务
                if (ContextCompat.checkSelfPermission(_mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(_mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                Toast.makeText(_mActivity, R.string.operation_share, Toast.LENGTH_SHORT).show();
                dismissPopWindow();
                break;
            case R.id.ll_operation_refresh:
                Toast.makeText(_mActivity, R.string.operation_refresh, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder dialog = new AlertDialog.Builder(_mActivity);
                dialog.setTitle("Dialog")
                        .setMessage("Message")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                dismissPopWindow();
                break;
            case R.id.ll_content_good:
                if (downloadBinder == null) {
                    return;
                }
                List<NewsBean.DataEntity.VideosBean> videos = mData.getVideos();
                if (videos == null || videos.size() == 0) {
                    Toast.makeText(_mActivity, "这里没有视频", Toast.LENGTH_SHORT).show();
                    return;
                }
                downloadBinder.startDownload(videos.get(0).getVideo_urls().getSd());
                break;
            case R.id.ll_content_down:
                if (downloadBinder == null) {
                    return;
                }
                downloadBinder.pauseDownload();
                break;
            case R.id.ll_content_comment:
                if (downloadBinder == null) {
                    return;
                }
                downloadBinder.cancelDownload();
                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(_mActivity, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }


    private PopupWindow popupWindow;

    private void dismissPopWindow() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    private void showPopWindow() {
        View popView = LayoutInflater.from(_mActivity).inflate(R.layout.popup_window_layout, null);
        popupWindow = new PopupWindow(popView, DeviceUtils.dip2px(180),
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        LinearLayout llOperationCollect = (LinearLayout) popView.findViewById(R.id.ll_operation_collect);
        LinearLayout llOperationShare = (LinearLayout) popView.findViewById(R.id.ll_operation_share);
        LinearLayout llOperationRefresh = (LinearLayout) popView.findViewById(R.id.ll_operation_refresh);

        llOperationCollect.setOnClickListener(this);
        llOperationShare.setOnClickListener(this);
        llOperationRefresh.setOnClickListener(this);

        // //设置这2个使得点击pop以外区域可以去除pop
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 出现在父布局底端
//        popupWindow.showAtLocation(llOperationCollect, Gravity.TOP, 0, 0);

        popupWindow.showAsDropDown(btnImageOption, 40,  // x 轴方向偏移没作用 why
                (llActionRoot.getHeight() - btnImageOption.getHeight()) / 2);
    }

    /**
     * 显示菜单
     */
    private void showPopMenu() {
        PopupMenu popupMenu = new PopupMenu(getContext(), btnImageOption);

        _mActivity.getMenuInflater().inflate(R.menu.more_operation, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.operation_collect:
                        Toast.makeText(_mActivity, R.string.operation_collect, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.operation_share:
                        Toast.makeText(_mActivity, R.string.operation_share, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.operation_refresh:
                        Toast.makeText(_mActivity, R.string.operation_refresh, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (JCVideoPlayer.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (downloadBinder != null) {
            _mActivity.unbindService(connection);
        }

    }

}
