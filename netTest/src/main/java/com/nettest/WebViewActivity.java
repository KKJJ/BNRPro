package com.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    String encoding = "UTF-8";
    String mimeType = "text/html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true); // 启用js
        mWebView.getSettings().setBlockNetworkImage(false); // 解决图片不显示
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.setWebViewClient(new WebViewClient());

//            mWebView.loadData(URLEncoder.encode(webData, encoding), mimeType, encoding); // 不支持图片

        // 原内容img src经过base64编码，不能显示 ，此处将其转换为原始图片地址
//        htmlDataImg = replaceDataSrc(htmlDataImg);  // 图片展示类型
//        mWebView.loadDataWithBaseURL(null, htmlDataImg, mimeType, encoding, null);


//        htmlDataVideo = replaceDataSrc(htmlDataVideo);  // 视频类型
//        content = content.replace("src=\"data:image/gif;base64", "src=\"" + cover_url + "\" aaa=\"data:image/gif;base64");
        mWebView.loadDataWithBaseURL(null, htmlDataVideo, mimeType, encoding, null);

    }

    private String replaceDataSrc(String htmlData) {
        htmlData = htmlData.replace("data-src=", "replaceData=");
        htmlData = htmlData.replace("src=", "data-src=");
        htmlData = htmlData.replace("replaceData=", "src=");
        LogUtil.e(TAG, "onCreate: " + htmlData);
        return htmlData;
    }

    private static final String TAG = "--WebViewActivity";
    String body = "<img  src=\"http://avatar.zhangyoubao.com/yxzj/postimages/1df/1d9/b3c/f9acq0xGY.jpg\"/>";
    String html = "<html><body>" + body + "</body></html>";

    private String htmlDataImg = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\" />\n" +
            "    <meta http-equiv=\"x-rim-auto-match\" content=\"none\" />\n" +
            "    <link type=\"text/css\" rel=\"stylesheet\" href=\"http://static.zhangyoubao.com/zyb_app/webview/css/news.css\">\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"wrap\"><p>这次体验服大更新，也有很多没有展示出来的东西，哪吒给大家汇总一篇。</p>\n" +
            "<p>| 前言</p>\n" +
            "<p>首先我爆料出来的东西，基本都是经过核实的。不像网上流传出来的图片。</p>\n" +
            "<p>| S7奖励皮肤。</p>\n" +
            "<p><img  options=\"size=1137x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/1df/1d9/b3c/f9acq0xGY.jpg\" width=\"1137\" height=\"640\" title=\"3122716606509993466\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122716606509993466\"></p>\n" +
            "<p>这是网上流传出来的图，没有官方渠道说是谁。但是经过辩证应该是虞姬，这是某位小伙伴的看腿识人。</p>\n" +
            "<p>| 庞统</p>\n" +
            "<p><br></p>\n" +
            "<p><img  options=\"size=640x360\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/535/ece/aac/7f6Iky7Su.jpg\" width=\"640\" height=\"360\" title=\"3122717941653424584\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122717941653424584\"></p>\n" +
            "<p><img  options=\"size=640x360\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/bb0/aaa/2dc/926t0LJRg.jpg\" width=\"640\" height=\"360\" title=\"3122717942364359113\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122717942364359113\"></p>秘术千鸟，让我想起了佐助[坏笑] <p>| 花木兰 水晶猎龙者</p>\n" +
            "<p><br></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/592/d32/6dc/f79laM_aj.jpg\" width=\"1138\" height=\"640\" title=\"3122718188857313897\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718188857313897\"></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/7e1/ddd/5e8/b2fl7acBm.jpg\" width=\"1138\" height=\"640\" title=\"3122718190085844454\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718190085844454\"></p>\n" +
            "<p>这个皮肤爆了很多次了，之所以在拿出来是因为胸，胸没了。。</p>\n" +
            "<p>| 雅典娜 冰冠女神</p>\n" +
            "<p><br></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/8e4/32f/c1f/4a5Zhzn7N.jpg\" width=\"1138\" height=\"640\" title=\"3122718448904162057\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718448904162057\"></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/0cc/527/305/4a2cLhZfz.jpg\" width=\"1138\" height=\"640\" title=\"3122718450445168262\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718450445168262\"></p>别看了，穿了安全裤。<p>| 东皇太一 东海龙王</p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/f51/a2e/f94/d3f8p7c1T.jpg\" width=\"1138\" height=\"640\" title=\"3122718701650423595\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718701650423595\"></p>\n" +
            "<p>周围盘了一条龙，感觉不够霸气！</p>\n" +
            "<p>| 刘邦 吸血鬼伯爵 </p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/144/240/d61/9aaZtA635.jpg\" width=\"1138\" height=\"640\" title=\"3122718795841908589\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718795841908589\"></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/c7c/09d/7f5/644SvHrWj.jpg\" width=\"1138\" height=\"640\" title=\"3122718850833828904\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122718850833828904\"></p>\n" +
            "<p>这个皮肤也提过很多次了，这次做一个汇总（掌游宝）皮肤模型看着很棒！</p>\n" +
            "<p>| 墨子 进击号</p>\n" +
            "<p><br></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/3e9/bd4/d16/b56D8Cb4O.jpg\" width=\"1138\" height=\"640\" title=\"3122719113243764820\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122719113243764820\"></p>\n" +
            "<p><img  options=\"size=1138x640\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/234/67c/306/f26iAalJ_.jpg\" width=\"1138\" height=\"640\" title=\"3122719114290243669\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122719114290243669\"></p>\n" +
            "<p>| 干将莫邪 第七人偶</p>\n" +
            "<p><img  options=\"size=640x360\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://avatar.zhangyoubao.com/yxzj/postimages/056/3b3/26d/e61x_2Vhg.jpg\" width=\"640\" height=\"360\" title=\"3122719311764367578\" class=\"placeholder app-event\" data-type2=\"image\" data-id2=\"3122719311764367578\"></p>\n" +
            "<p>这个皮肤略微丑了一点，不出意外是要改的。</p><div style=\"text-align: left;margin-top: 20px;text-align: justify;\"><p class=\"disclaimer_bottom_class\" style=\"font-size: 13px !important;color:#999cad;line-height: 18px !important;\">本文原创于王者荣耀掌游宝，转载请注明出处，侵权抄袭必究。</p></div></div>\n" +
            "<script>\n" +
            "    //判断是否来自电竞掌游宝(1为来自电竞掌游宝)\n" +
            "    var isCommunity = 0;\n" +
            "    //判断是否为夜间模式\n" +
            "    var isDarkGame = 0;\n" +
            "    //不同模式背景颜色\n" +
            "    var placeholderBg = \"#d3cec6\";\n" +
            "    if (1 == isDarkGame) {\n" +
            "        placeholderBg = \"#515151\";\n" +
            "    }\n" +
            "    //是否为省流量模式\n" +
            "    var isSave = 0;\n" +
            "    //视频相关信息\n" +
            "    var videoTimeList = {};\n" +
            "    //分享信息\n" +
            "    var shareInfo = {\"share_title\":\"[\\u7206\\u6599]S7\\u5956\\u52b1\\u82f1\\u96c4\\u76ae\\u80a4|\\u5e9e\\u7edf\\u6280\\u80fd\\u7206\\u6599\\u5404\\u82f1\\u96c4\\u76ae\\u80a4\",\"share_title_long\":\"\",\"share_desc\":\"\\u8fd9\\u6b21\\u4f53\\u9a8c\\u670d\\u5927\\u66f4\\u65b0\\uff0c\\u4e5f\\u6709\\u5f88\\u591a\\u6ca1\\u6709\\u5c55\\u793a\\u51fa\\u6765\\u7684\\u4e1c\\u897f\\uff0c\\u54ea\\u5412\\u7ed9\\u5927\\u5bb6\\u6c47\\u603b\\u4e00\\u7bc7\\u3002|\\u524d\\u8a00\\u9996\\u5148\\u6211\\u7206\\u6599\\u51fa\\u6765\\u7684\\u4e1c\\u897f\\uff0c\\u57fa\\u672c\\u90fd\\u662f\\u7ecf\\u8fc7\\u6838\\u5b9e\\u7684\\u3002\\u4e0d\\u50cf\\u7f51\",\"share_url\":\"http:\\/\\/m.zhangyoubao.com\\/yxzj\\/detail\\/3122719580833163665?source=shareout\",\"share_img\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/topic_cover\\/0bb\\/eee\\/034\\/98dYVEfiQ.png\"};\n" +
            "    //当前游戏别称\n" +
            "    var game = \"yxzj\";\n" +
            "    var hasRendered = false;\n" +
            "    var webappUrl = \"http://m.zhangyoubao.com/\";\n" +
            "</script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/zepto.min.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/placeholder-mix.2.0.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/public/js/emoj.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/js_sdk/anzogame_sdk_v2.0.5.min.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/base.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/echo-mix.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/data-echo-mix.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/notSaveModel.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/sdkEvent.js\"></script>\n" +
            "<script>\n" +
            "    window.onload = function() {\n" +
            "        \n" +
            "            };\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>";


    private String htmlDataVideoType = "{\"code\":200,\"data\":{\"id\":\"3123621281773629056\",\"post_id\":\"3123621281777823361\",\"user_id\":\"15669697\",\"user_name\":\"\\u6b66\\u5fb7\\u54ea\\u5412\",\"avatar_url\":\"http:\\/\\/avatar.zhangyoubao.com\\/pic\\/user\\/avatar\\/e3\\/57fdd2a315669697.jpg\",\"certification_title\":1,\"certification_describes\":\"\\u88c5\\u5907\\u5206\\u6790\\u5e08\",\"publish_time\":\"1489458660\",\"reply_time\":\"1489469952\",\"create_time\":\"1489458695\",\"update_time\":\"1489458695\",\"type\":\"2\",\"author\":\"\\u5367\\u9f99@\\u817e\\u8baf\",\"is_fav\":\"0\",\"is_up\":\"0\",\"is_down\":\"0\",\"has_video\":\"1\",\"is_lock\":\"0\",\"sticky_type\":\"0\",\"essence_type\":\"0\",\"is_editor\":\"1\",\"status\":\"1\",\"title\":\"[\\u5367\\u9f99] \\u975eBAN\\u5fc5\\u9009\\u6768\\u622c\",\"description\":\"\",\"content\":\"<!DOCTYPE html>\\n<html>\\n<head>\\n    <meta charset=\\\"UTF-8\\\">\\n    <meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1, user-scalable=no\\\" \\/>\\n    <meta name=\\\"format-detection\\\" content=\\\"telephone=no\\\" \\/>\\n    <meta http-equiv=\\\"x-rim-auto-match\\\" content=\\\"none\\\" \\/>\\n    <link type=\\\"text\\/css\\\" rel=\\\"stylesheet\\\" href=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/webview\\/css\\/news.css\\\">\\n<\\/head>\\n<body>\\n<div class=\\\"wrap\\\"><p><img class=\\\"placeholder ke-video\\\"  options=\\\"size=125x125\\\" src=\\\"data:image\\/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\\\" data-src=\\\"http:\\/\\/static.zhangyoubao.com\\/public\\/img\\/video.png\\\" width=\\\"308\\\" height=\\\"210\\\" data-type=\\\"video\\\" data-id=\\\"4221\\\"><\\/p><div style=\\\"text-align: left;margin-top: 20px;text-align: justify;\\\"><p class=\\\"disclaimer_bottom_class\\\" style=\\\"font-size: 13px !important;color:#999cad;line-height: 18px !important;\\\">\\u638c\\u6e38\\u5b9d\\u520a\\u767b\\u6b64\\u6587\\u76ee\\u7684\\u662f\\u4e3a\\u4e86\\u4f20\\u9012\\u66f4\\u591a\\u4fe1\\u606f\\uff0c\\u5e76\\u4e0d\\u610f\\u5473\\u7740\\u8d5e\\u540c\\u5176\\u89c2\\u70b9\\u6216\\u8bc1\\u5b9e\\u5176\\u63cf\\u8ff0\\u3002\\u7248\\u6743\\u5f52\\u539f\\u4f5c\\u8005\\u6240\\u6709\\uff0c\\u5982\\u6709\\u4fb5\\u6743\\u8bf7\\u8054\\u7cfb\\u6211\\u4eec\\u5220\\u9664\\u3002<\\/p><\\/div><\\/div>\\n<script>\\n    \\/\\/\\u5224\\u65ad\\u662f\\u5426\\u6765\\u81ea\\u7535\\u7ade\\u638c\\u6e38\\u5b9d(1\\u4e3a\\u6765\\u81ea\\u7535\\u7ade\\u638c\\u6e38\\u5b9d)\\n    var isCommunity = 0;\\n    \\/\\/\\u5224\\u65ad\\u662f\\u5426\\u4e3a\\u591c\\u95f4\\u6a21\\u5f0f\\n    var isDarkGame = 0;\\n    \\/\\/\\u4e0d\\u540c\\u6a21\\u5f0f\\u80cc\\u666f\\u989c\\u8272\\n    var placeholderBg = \\\"#d3cec6\\\";\\n    if (1 == isDarkGame) {\\n        placeholderBg = \\\"#515151\\\";\\n    }\\n    \\/\\/\\u662f\\u5426\\u4e3a\\u7701\\u6d41\\u91cf\\u6a21\\u5f0f\\n    var isSave = 0;\\n    \\/\\/\\u89c6\\u9891\\u76f8\\u5173\\u4fe1\\u606f\\n    var videoTimeList = {\\\"4221\\\":{\\\"is_live\\\":\\\"0\\\",\\\"time\\\":\\\"707.50\\\",\\\"from\\\":\\\"\\\\u817e\\\\u8baf\\\",\\\"cover\\\":\\\"http:\\\\\\/\\\\\\/avatar.zhangyoubao.com\\\\\\/yxzj\\\\\\/video_cover\\\\\\/7a8\\\\\\/0c0\\\\\\/46b\\\\\\/a5c_tohvz.png\\\",\\\"source_url\\\":\\\"https:\\\\\\/\\\\\\/v.qq.com\\\\\\/x\\\\\\/page\\\\\\/p03839vna0l.html\\\"}};\\n    \\/\\/\\u5206\\u4eab\\u4fe1\\u606f\\n    var shareInfo = {\\\"share_title\\\":\\\"[\\\\u5367\\\\u9f99]\\\\u975eBAN\\\\u5fc5\\\\u9009\\\\u6768\\\\u622c\\\",\\\"share_title_long\\\":\\\"\\\",\\\"share_desc\\\":\\\"\\\\u638c\\\\u6e38\\\\u5b9d\\\\u520a\\\\u767b\\\\u6b64\\\\u6587\\\\u76ee\\\\u7684\\\\u662f\\\\u4e3a\\\\u4e86\\\\u4f20\\\\u9012\\\\u66f4\\\\u591a\\\\u4fe1\\\\u606f\\\\uff0c\\\\u5e76\\\\u4e0d\\\\u610f\\\\u5473\\\\u7740\\\\u8d5e\\\\u540c\\\\u5176\\\\u89c2\\\\u70b9\\\\u6216\\\\u8bc1\\\\u5b9e\\\\u5176\\\\u63cf\\\\u8ff0\\\\u3002\\\\u7248\\\\u6743\\\\u5f52\\\\u539f\\\\u4f5c\\\\u8005\\\\u6240\\\\u6709\\\\uff0c\\\\u5982\\\\u6709\\\\u4fb5\\\\u6743\\\\u8bf7\\\\u8054\\\\u7cfb\\\\u6211\\\\u4eec\\\\u5220\\\\u9664\\\\u3002\\\",\\\"share_url\\\":\\\"http:\\\\\\/\\\\\\/m.zhangyoubao.com\\\\\\/yxzj\\\\\\/detail\\\\\\/3123621281773629056?source=shareout\\\",\\\"share_img\\\":\\\"http:\\\\\\/\\\\\\/avatar.zhangyoubao.com\\\\\\/yxzj\\\\\\/topic_cover\\\\\\/e6f\\\\\\/0ac\\\\\\/0ef\\\\\\/5e8JKxATw.jpg\\\"};\\n    \\/\\/\\u5f53\\u524d\\u6e38\\u620f\\u522b\\u79f0\\n    var game = \\\"yxzj\\\";\\n    var hasRendered = false;\\n    var webappUrl = \\\"http:\\/\\/m.zhangyoubao.com\\/\\\";\\n<\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/common\\/js\\/zepto.min.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/webview\\/js\\/placeholder-mix.2.0.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/public\\/js\\/emoj.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/js_sdk\\/anzogame_sdk_v2.0.5.min.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/webview\\/js\\/base.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/common\\/js\\/echo-mix.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/common\\/js\\/data-echo-mix.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/webview\\/js\\/notSaveModel.js\\\"><\\/script>\\n<script src=\\\"http:\\/\\/static.zhangyoubao.com\\/zyb_app\\/webview\\/js\\/sdkEvent.js\\\"><\\/script>\\n<script>\\n    window.onload = function() {\\n        \\n            };\\n<\\/script>\\n<\\/body>\\n<\\/html>\",\"good_count\":\"6\",\"down_count\":\"1\",\"comment_count\":5,\"image_count\":\"0\",\"image_urls\":[],\"cover_url\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/topic_cover\\/e6f\\/0ac\\/0ef\\/5e8JKxATw.jpg\",\"pv_count\":\"0\",\"author_detail\":{},\"is_third\":\"0\",\"third_url\":\"\",\"title_long\":\"\",\"share_url\":\"http:\\/\\/m.zhangyoubao.com\\/yxzj\\/detail\\/3123621281773629056?source=shareout\",\"videos\":[{\"id\":\"4221\",\"title\":\"\\u738b\\u8005\\u8363\\u8000\\u3010\\u5367\\u9f99\\u89e3\\u8bf4\\u3011\\u975eBAN\\u5fc5\\u9009\\u6768\\u622c\",\"is_live\":\"0\",\"image_url\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/video_cover\\/7a8\\/0c0\\/46b\\/a5c_tohvz.png\",\"video_type\":\"m3u8\",\"source_url\":\"https:\\/\\/v.qq.com\\/x\\/page\\/p03839vna0l.html\",\"video_width\":\"308\",\"video_height\":\"210\",\"video_length\":\"707.50\",\"source_site\":\"\\u817e\\u8baf\",\"video_urls\":{\"sd\":\"http:\\/\\/video.dispatch.tc.qq.com\\/77035704\\/p03839vna0l.mp4?sdtfrom=v1001&type=mp4&vkey=EBC825BFE3D2F12676A1C8B2FAE116A7D3426BB70B882D70BEBC77EFF52D82D1E2EE35E1C7E190A7C53F4140B7FDB8E1A3C67A784272E7C089D8988FA6EF1ED9FBC3364E926732BE4767F066227AF79CB73729088C7F94B1C2AC98CB606CD70F08D337D9A938D8997CEE8B64AF783149&platform=11&fmt=auto&sp=350&guid=168882926692841ECE357C7D132C0397\",\"hd\":\"http:\\/\\/video.dispatch.tc.qq.com\\/77035704\\/p03839vna0l.mp4?sdtfrom=v1001&type=mp4&vkey=3DC10239EBB20AF564CC9B96C9D9624BD919D1B1FB05DD780FBFD2AAE249E934A7EB1C43FEED7762AF35032BA1EE1587B13F9DBECA3A726718F2EF420A0FDAC5CDC6450F6CE6CA5D107BC1D4CC0B08ECB3CAB45E57D388AABBA034861945D9DF52776E5D8BE014DE2D7FC3C27F00C852&platform=11&fmt=auto&sp=350&guid=F361E92EA1216C49CB2B9786505E793F\",\"shd\":\"\",\"multi_mp4_sd\":[],\"multi_mp4_hd\":[],\"multi_mp4_shd\":[]},\"iframe_src\":\"https:\\/\\/v.qq.com\\/x\\/page\\/p03839vna0l.html\",\"iframe_width\":\"640\",\"iframe_height\":\"498\",\"last_parse_time\":\"1489479788\",\"video_sizes\":{\"sd\":44237375,\"hd\":0,\"shd\":0,\"multi_mp4_sd\":0,\"multi_mp4_hd\":0,\"multi_mp4_shd\":0}}],\"userLogoFrameId\":0},\"message\":\"OK\",\"list_size\":10}\n";

    String htmlDataVideo = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\n" +
            "    <meta name=\"format-detection\" content=\"telephone=no\" />\n" +
            "    <meta http-equiv=\"x-rim-auto-match\" content=\"none\" />\n" +
            "    <link type=\"text/css\" rel=\"stylesheet\" href=\"http://static.zhangyoubao.com/zyb_app/webview/css/news.css\">\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"wrap\"><p><img class=\"placeholder ke-video\"  options=\"size=125x125\" src=\"data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==\" data-src=\"http://static.zhangyoubao.com/public/img/video.png\" width=\"308\" height=\"210\" data-type=\"video\" data-id=\"4281\"></p><div style=\"text-align: left;margin-top: 20px;text-align: justify;\"><p class=\"disclaimer_bottom_class\" style=\"font-size: 13px !important;color:#999cad;line-height: 18px !important;\">掌游宝刊登此文目的是为了传递更多信息，并不意味着赞同其观点或证实其描述。版权归原作者所有，如有侵权请联系我们删除。</p></div></div>\n" +
            "<script>\n" +
            "    //判断是否来自电竞掌游宝(1为来自电竞掌游宝)\n" +
            "    var isCommunity = 0;\n" +
            "    //判断是否为夜间模式\n" +
            "    var isDarkGame = 0;\n" +
            "    //不同模式背景颜色\n" +
            "    var placeholderBg = \"#d3cec6\";\n" +
            "    if (1 == isDarkGame) {\n" +
            "        placeholderBg = \"#515151\";\n" +
            "    }\n" +
            "    //是否为省流量模式\n" +
            "    var isSave = 0;\n" +
            "    //视频相关信息\n" +
            "    var videoTimeList = {\"4281\":{\"is_live\":\"0\",\"time\":\"412.00\",\"from\":\"\\u672a\\u77e5\",\"cover\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/video_cover\\/66a\\/1a7\\/7ab\\/e00SKxsWG.jpg\",\"source_url\":\"http:\\/\\/www.bilibili.com\\/video\\/av9280659\\/?from=search&seid=12680540182880405242\"}};\n" +
            "    //分享信息\n" +
            "    var shareInfo = {\"share_title\":\"[FA\\u4e3b\\u64ad\\u7c97\\u4e8b\\u4e86]\\u55e8\\u6c0f\\u55e8\\u8fc7\\u5934\\uff01\\u6b22\\u58f0\\u7b11\\u8bed\\u4e2d\\u6253\\u51faGG\",\"share_title_long\":\"\",\"share_desc\":\"\\u738b\\u8005\\u7bc704\",\"share_url\":\"http:\\/\\/m.zhangyoubao.com\\/yxzj\\/detail\\/3124766533504969473?source=shareout\",\"share_img\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/topic_cover\\/37e\\/093\\/67b\\/64d16wvh1.jpg\"};\n" +
            "    //当前游戏别称\n" +
            "    var game = \"yxzj\";\n" +
            "    //是否编译完成\n" +
            "    var hasRendered = false;\n" +
            "    //webapp地址\n" +
            "    var webappUrl = \"http://m.zhangyoubao.com/\";\n" +
            "</script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/zepto.min.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/placeholder-mix.2.0.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/public/js/emoj.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/js_sdk/anzogame_sdk_v2.0.5.min.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/base.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/echo-mix.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/data-echo-mix.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/notSaveModel.js\"></script>\n" +
            "<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/sdkEvent.js\"></script>\n" +
            "<script>\n" +
            "    window.onload = function() {\n" +
            "        \n" +
            "            };\n" +
            "</script>\n" +
            "</body>\n" +
            "</html>";

}


