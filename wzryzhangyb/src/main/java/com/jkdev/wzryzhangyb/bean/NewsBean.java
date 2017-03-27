package com.jkdev.wzryzhangyb.bean;

import java.util.List;

/**
 * Created by KJ on 2017/3/20.
 */

public class NewsBean {

    /**
     * code : 200
     * data : {"id":"3124269987686494157","post_id":"3124269987690688462","user_id":"15669697","user_name":"武德哪吒","avatar_url":"http://avatar.zhangyoubao.com/pic/user/avatar/e3/57fdd2a315669697.jpg","certification_title":1,"certification_describes":"装备分析师","publish_time":"1489767294","reply_time":"1490004640","create_time":"1489768022","update_time":"1489768123","type":"2","author":"武德哪吒","is_fav":"0","is_up":"0","is_down":"0","has_video":"0","is_lock":"0","sticky_type":"1","essence_type":"0","is_editor":"1","status":"1","title":"[体验服] 哪吒告别千里送 曹操位移可穿墙","description":"一.英雄平衡调整1.哪吒哪吒的高额减伤一直限制着他输出的表现,低下的输出能力着实和他的战士定位不太相符,守强攻弱无论是哪吒使用者和哪吒的对抗者其实都不太好,在这次的调整,我们用承受去换输出希望哪吒在机会出现时有致死性的威胁,一直被玩家诟病的","content":"<!DOCTYPE html>\n<html>\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\" />\n    <meta name=\"format-detection\" content=\"telephone=no\" />\n    <meta http-equiv=\"x-rim-auto-match\" content=\"none\" />\n    <link type=\"text/css\" rel=\"stylesheet\" href=\"http://static.zhangyoubao.com/zyb_app/webview/css/news.css\">\n<\/head>\n<body>\n<div class=\"wrap\">一.英雄平衡调整<br>1.哪吒<br>哪吒的高额减伤一直限制着他输出的表现,低下的输出能力着实和他的战士定位不太相符,守强攻弱无论是哪吒使用者和哪吒的对抗者其实都不太好,在这次的调整,我们用承受去换输出希望哪吒在机会出现时有致死性的威胁,一直被玩家诟病的大招\u201d千里送人头\u201d我们也一并在这次调整中解决<br>被动：真实伤害：30（+4*英雄等级）（+0.12Ad）（+0.18Ap） → 30（+4*英雄等级）（+0.25Ad）<br>2技能：火莲之印伤害减免：15/18/21/24/27/30% → 15/17/19/21/23/25%<br>3技能：增加效果：可在大招飞行过程中取消操作<br>2.李元芳<br>李元芳在目前的坏境中前期能力过于低下,这次我们主要优化元芳前期的体验感受来确保玩家在使用时元芳时有正常的游戏体验<br>1技能：CD：12/11.2/10.4/9.6/8.8/8 秒 → 10/9.6/9.2/8.8/8.4/8 秒<br>1技能： 修复元芳施放谍影重重后提升射程的持续时长不准的BUG<br>1技能：每层伤害：135（+17/Lv）（+0.48Ad） → 160（+20/Lv）（+0.48Ad）<br>3.牛魔<br>作为一名辅助英雄，除了强力的控制之外，牛魔能够为队友提供的直接帮助太少了，100点防御加成并不能在对抗中获得明显的优势。此次我们大幅增加了被动技能提供的防御加成，希望跟牛魔走在一起时候能够获得更多的安全感。<br>被动：100（+7*英雄等级） → 200（+7*英雄等级）<br>4.东皇太一<br>东皇自登陆体验服以来,优秀的坦克属性显得过于强大,高额血库不是我们所希望看到的,我们要确保东皇有他作为法坦也应该拥有的输出能力.大招的命中难度和收益难度都进行了一定程度的削弱,强化了在对抗东皇时拥有足够的反制空间<br>被动: 伤害 : 5%目标最大生命(+0.25Ap) → 100 （+3%目标最大生命） （+0.4Ap）<br>被动:生命回复: 每球命中回复4%最大生命 →100 (+3%自身最大生命） （+0.4Ap）<br>2技能：三次冲击的伤害统计，取消后两次冲击伤害衰减的设定<br>2技能：三次砸地伤害范围：300/350/400 → 300/325/350<br>3技能：CD：40/35/30 秒 → 50/45/40秒<br>3技能：施法距离：500 → 400<br>3技能：持续时长：3秒 → 2.5秒<br>5.曹操<br>我们一直都在思考，如何让曹操能够更加有效的进行输出，而不是仅仅作为一名半肉战士和其它硬汉在人堆里拉扯。所以我们将曹操1技能的三段冲刺，调整为无视碰撞（触碰英雄也不会停止），这样曹操就可以利用1技能更好地去调整输出位置，而且让他有更多突进到敌方后排的可能。<br>1技能：取消位移时触碰英雄会停止的设定，目前可自由位移（可穿墙）<br>1技能：位移距离：400 → 350<br> <br>二． BUG修复<br>1.    李白<br>修复李白大招伤害分摊机制无效的BUG<br>2.极寒风暴<br>修复描述问题，被动的触发条件应该是受到超过当前生命值10%的伤害，而不是最大生命值<br> <br>三．战场调整<br>1.兵线加速机制<br>兵线速度成长从12分钟开始增加，兵线的移动速度一直成长到16分钟<br>具体数值：<br>原来数值：12分钟/3000→18分钟/3600<br>最新数值：12分钟/3000→16分钟/3600<br>2.黑暗暴君<br>我们提升了击杀黑暗暴君获取BUFF的数值强度，希望在后期给击杀方带来更大的团战优势。现在我们在后期必须更严谨的思考，到底是优先击杀主宰获取推进能力，还是优先击杀黑暗暴君获取团战能力了。<br>暴君的复仇BUFF效果：增加全阵营80+5%物理攻击，120+5%法术强度→增加全阵营80+15%物理攻击，120+15%法术强度。<br>3.关闭无限召唤入口<br>遗憾的通知大家，我们将把新娱乐模式(无限召唤模式)暂时关闭，接下来我们会对该模式进行更多优化，希望之后再开放时能为大家带来更多更好的体验。<br> <br><div style=\"text-align: left;margin-top: 20px;text-align: justify;\"><p class=\"disclaimer_bottom_class\" style=\"font-size: 13px !important;color:#999cad;line-height: 18px !important;\">本文原创于王者荣耀掌游宝，转载请注明出处，侵权抄袭必究。<\/p><\/div><\/div>\n<script>\n    //判断是否来自电竞掌游宝(1为来自电竞掌游宝)\n    var isCommunity = 0;\n    //判断是否为夜间模式\n    var isDarkGame = 0;\n    //不同模式背景颜色\n    var placeholderBg = \"#d3cec6\";\n    if (1 == isDarkGame) {\n        placeholderBg = \"#515151\";\n    }\n    //是否为省流量模式\n    var isSave = 0;\n    //视频相关信息\n    var videoTimeList = {};\n    //分享信息\n    var shareInfo = {\"share_title\":\"[\\u4f53\\u9a8c\\u670d]\\u54ea\\u5412\\u544a\\u522b\\u5343\\u91cc\\u9001\\u66f9\\u64cd\\u4f4d\\u79fb\\u53ef\\u7a7f\\u5899\",\"share_title_long\":\"\",\"share_desc\":\"\\u4e00.\\u82f1\\u96c4\\u5e73\\u8861\\u8c03\\u65741.\\u54ea\\u5412\\u54ea\\u5412\\u7684\\u9ad8\\u989d\\u51cf\\u4f24\\u4e00\\u76f4\\u9650\\u5236\\u7740\\u4ed6\\u8f93\\u51fa\\u7684\\u8868\\u73b0,\\u4f4e\\u4e0b\\u7684\\u8f93\\u51fa\\u80fd\\u529b\\u7740\\u5b9e\\u548c\\u4ed6\\u7684\\u6218\\u58eb\\u5b9a\\u4f4d\\u4e0d\\u592a\\u76f8\\u7b26,\\u5b88\\u5f3a\\u653b\\u5f31\\u65e0\\u8bba\\u662f\\u54ea\",\"share_url\":\"http:\\/\\/m.zhangyoubao.com\\/yxzj\\/detail\\/3124269987686494157?source=shareout\",\"share_img\":\"http:\\/\\/avatar.zhangyoubao.com\\/yxzj\\/topic_cover\\/212\\/855\\/9a7\\/a0fFH_3fx.jpg\"};\n    //当前游戏别称\n    var game = \"yxzj\";\n    //是否编译完成\n    var hasRendered = false;\n    //webapp地址\n    var webappUrl = \"http://m.zhangyoubao.com/\";\n<\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/zepto.min.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/placeholder-mix.2.0.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/public/js/emoj.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/js_sdk/anzogame_sdk_v2.0.5.min.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/base.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/echo-mix.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/common/js/data-echo-mix.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/notSaveModel.js\"><\/script>\n<script src=\"http://static.zhangyoubao.com/zyb_app/webview/js/sdkEvent.js\"><\/script>\n<script>\n    window.onload = function() {\n        \n            };\n<\/script>\n<\/body>\n<\/html>","good_count":"292","down_count":"23","comment_count":90,"image_count":"0","image_urls":[],"cover_url":"http://avatar.zhangyoubao.com/yxzj/topic_cover/212/855/9a7/a0fFH_3fx.jpg","pv_count":"0","author_detail":{"user_id":"15669697","user_name":"武德哪吒","avatar_url":"http://avatar.zhangyoubao.com/pic/user/avatar/e3/57fdd2a315669697.jpg","signature":"新浪微博:武德哪吒","certification_title":1,"certification_describes":"装备分析师"},"is_third":"0","third_url":"","title_long":"","share_url":"http://m.zhangyoubao.com/yxzj/detail/3124269987686494157?source=shareout","videos":[],"userLogoFrameId":0}
     * message : OK
     * list_size : 10
     */

    private int code;
    private DataEntity data;
    private String message;
    private int list_size;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getList_size() {
        return list_size;
    }

    public void setList_size(int list_size) {
        this.list_size = list_size;
    }

    public static class DataEntity {
        @Override
        public String toString() {
            return "DataEntity{" +
                    "id='" + id + '\'' +
                    ", post_id='" + post_id + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", certification_title=" + certification_title +
                    ", certification_describes='" + certification_describes + '\'' +
                    ", publish_time='" + publish_time + '\'' +
                    ", reply_time='" + reply_time + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    ", type='" + type + '\'' +
                    ", author='" + author + '\'' +
                    ", is_fav='" + is_fav + '\'' +
                    ", is_up='" + is_up + '\'' +
                    ", is_down='" + is_down + '\'' +
                    ", has_video='" + has_video + '\'' +
                    ", is_lock='" + is_lock + '\'' +
                    ", sticky_type='" + sticky_type + '\'' +
                    ", essence_type='" + essence_type + '\'' +
                    ", is_editor='" + is_editor + '\'' +
                    ", status='" + status + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", content='" + content + '\'' +
                    ", good_count='" + good_count + '\'' +
                    ", down_count='" + down_count + '\'' +
                    ", comment_count=" + comment_count +
                    ", image_count='" + image_count + '\'' +
                    ", cover_url='" + cover_url + '\'' +
                    ", pv_count='" + pv_count + '\'' +
                    ", author_detail=" + author_detail +
                    ", is_third='" + is_third + '\'' +
                    ", third_url='" + third_url + '\'' +
                    ", title_long='" + title_long + '\'' +
                    ", share_url='" + share_url + '\'' +
                    ", userLogoFrameId=" + userLogoFrameId +
                    ", image_urls=" + image_urls +
                    ", videos=" + videos +
                    '}';
        }

        /**
         * id : 3124269987686494157
         * post_id : 3124269987690688462
         * user_id : 15669697
         * user_name : 武德哪吒
         * avatar_url : http://avatar.zhangyoubao.com/pic/user/avatar/e3/57fdd2a315669697.jpg
         * certification_title : 1
         * certification_describes : 装备分析师
         * publish_time : 1489767294
         * reply_time : 1490004640
         * create_time : 1489768022
         * update_time : 1489768123
         * type : 2
         * author : 武德哪吒
         * is_fav : 0
         * is_up : 0
         * is_down : 0
         * has_video : 0
         * is_lock : 0
         * sticky_type : 1
         * essence_type : 0
         * is_editor : 1
         * status : 1
         * title : [体验服] 哪吒告别千里送 曹操位移可穿墙
         * description : 一.英雄平衡调整1.哪吒哪吒的高额减伤一直限制着他输出的表现,低下的输出能力着实和他的战士定位不太相符,守强攻弱无论是哪吒使用者和哪吒的对抗者其实都不太好,在这次的调整,我们用承受去换输出希望哪吒在机会出现时有致死性的威胁,一直被玩家诟病的
         * content : <!DOCTYPE html>
         * <html>
         * <head>
         * <meta charset="UTF-8">
         * <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
         * <meta name="format-detection" content="telephone=no" />
         * <meta http-equiv="x-rim-auto-match" content="none" />
         * <link type="text/css" rel="stylesheet" href="http://static.zhangyoubao.com/zyb_app/webview/css/news.css">
         * </head>
         * <body>
         * <div class="wrap">一.英雄平衡调整<br>1.哪吒<br>哪吒的高额减伤一直限制着他输出的表现,低下的输出能力着实和他的战士定位不太相符,守强攻弱无论是哪吒使用者和哪吒的对抗者其实都不太好,在这次的调整,我们用承受去换输出希望哪吒在机会出现时有致死性的威胁,一直被玩家诟病的大招”千里送人头”我们也一并在这次调整中解决<br>被动：真实伤害：30（+4*英雄等级）（+0.12Ad）（+0.18Ap） → 30（+4*英雄等级）（+0.25Ad）<br>2技能：火莲之印伤害减免：15/18/21/24/27/30% → 15/17/19/21/23/25%<br>3技能：增加效果：可在大招飞行过程中取消操作<br>2.李元芳<br>李元芳在目前的坏境中前期能力过于低下,这次我们主要优化元芳前期的体验感受来确保玩家在使用时元芳时有正常的游戏体验<br>1技能：CD：12/11.2/10.4/9.6/8.8/8 秒 → 10/9.6/9.2/8.8/8.4/8 秒<br>1技能： 修复元芳施放谍影重重后提升射程的持续时长不准的BUG<br>1技能：每层伤害：135（+17/Lv）（+0.48Ad） → 160（+20/Lv）（+0.48Ad）<br>3.牛魔<br>作为一名辅助英雄，除了强力的控制之外，牛魔能够为队友提供的直接帮助太少了，100点防御加成并不能在对抗中获得明显的优势。此次我们大幅增加了被动技能提供的防御加成，希望跟牛魔走在一起时候能够获得更多的安全感。<br>被动：100（+7*英雄等级） → 200（+7*英雄等级）<br>4.东皇太一<br>东皇自登陆体验服以来,优秀的坦克属性显得过于强大,高额血库不是我们所希望看到的,我们要确保东皇有他作为法坦也应该拥有的输出能力.大招的命中难度和收益难度都进行了一定程度的削弱,强化了在对抗东皇时拥有足够的反制空间<br>被动: 伤害 : 5%目标最大生命(+0.25Ap) → 100 （+3%目标最大生命） （+0.4Ap）<br>被动:生命回复: 每球命中回复4%最大生命 →100 (+3%自身最大生命） （+0.4Ap）<br>2技能：三次冲击的伤害统计，取消后两次冲击伤害衰减的设定<br>2技能：三次砸地伤害范围：300/350/400 → 300/325/350<br>3技能：CD：40/35/30 秒 → 50/45/40秒<br>3技能：施法距离：500 → 400<br>3技能：持续时长：3秒 → 2.5秒<br>5.曹操<br>我们一直都在思考，如何让曹操能够更加有效的进行输出，而不是仅仅作为一名半肉战士和其它硬汉在人堆里拉扯。所以我们将曹操1技能的三段冲刺，调整为无视碰撞（触碰英雄也不会停止），这样曹操就可以利用1技能更好地去调整输出位置，而且让他有更多突进到敌方后排的可能。<br>1技能：取消位移时触碰英雄会停止的设定，目前可自由位移（可穿墙）<br>1技能：位移距离：400 → 350<br> <br>二． BUG修复<br>1.    李白<br>修复李白大招伤害分摊机制无效的BUG<br>2.极寒风暴<br>修复描述问题，被动的触发条件应该是受到超过当前生命值10%的伤害，而不是最大生命值<br> <br>三．战场调整<br>1.兵线加速机制<br>兵线速度成长从12分钟开始增加，兵线的移动速度一直成长到16分钟<br>具体数值：<br>原来数值：12分钟/3000→18分钟/3600<br>最新数值：12分钟/3000→16分钟/3600<br>2.黑暗暴君<br>我们提升了击杀黑暗暴君获取BUFF的数值强度，希望在后期给击杀方带来更大的团战优势。现在我们在后期必须更严谨的思考，到底是优先击杀主宰获取推进能力，还是优先击杀黑暗暴君获取团战能力了。<br>暴君的复仇BUFF效果：增加全阵营80+5%物理攻击，120+5%法术强度→增加全阵营80+15%物理攻击，120+15%法术强度。<br>3.关闭无限召唤入口<br>遗憾的通知大家，我们将把新娱乐模式(无限召唤模式)暂时关闭，接下来我们会对该模式进行更多优化，希望之后再开放时能为大家带来更多更好的体验。<br> <br><div style="text-align: left;margin-top: 20px;text-align: justify;"><p class="disclaimer_bottom_class" style="font-size: 13px !important;color:#999cad;line-height: 18px !important;">本文原创于王者荣耀掌游宝，转载请注明出处，侵权抄袭必究。</p></div></div>
         * <script>
         * //判断是否来自电竞掌游宝(1为来自电竞掌游宝)
         * var isCommunity = 0;
         * //判断是否为夜间模式
         * var isDarkGame = 0;
         * //不同模式背景颜色
         * var placeholderBg = "#d3cec6";
         * if (1 == isDarkGame) {
         * placeholderBg = "#515151";
         * }
         * //是否为省流量模式
         * var isSave = 0;
         * //视频相关信息
         * var videoTimeList = {};
         * //分享信息
         * var shareInfo = {"share_title":"[\u4f53\u9a8c\u670d]\u54ea\u5412\u544a\u522b\u5343\u91cc\u9001\u66f9\u64cd\u4f4d\u79fb\u53ef\u7a7f\u5899","share_title_long":"","share_desc":"\u4e00.\u82f1\u96c4\u5e73\u8861\u8c03\u65741.\u54ea\u5412\u54ea\u5412\u7684\u9ad8\u989d\u51cf\u4f24\u4e00\u76f4\u9650\u5236\u7740\u4ed6\u8f93\u51fa\u7684\u8868\u73b0,\u4f4e\u4e0b\u7684\u8f93\u51fa\u80fd\u529b\u7740\u5b9e\u548c\u4ed6\u7684\u6218\u58eb\u5b9a\u4f4d\u4e0d\u592a\u76f8\u7b26,\u5b88\u5f3a\u653b\u5f31\u65e0\u8bba\u662f\u54ea","share_url":"http:\/\/m.zhangyoubao.com\/yxzj\/detail\/3124269987686494157?source=shareout","share_img":"http:\/\/avatar.zhangyoubao.com\/yxzj\/topic_cover\/212\/855\/9a7\/a0fFH_3fx.jpg"};
         * //当前游戏别称
         * var game = "yxzj";
         * //是否编译完成
         * var hasRendered = false;
         * //webapp地址
         * var webappUrl = "http://m.zhangyoubao.com/";
         * </script>
         * <script src="http://static.zhangyoubao.com/zyb_app/common/js/zepto.min.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/webview/js/placeholder-mix.2.0.js"></script>
         * <script src="http://static.zhangyoubao.com/public/js/emoj.js"></script>
         * <script src="http://static.zhangyoubao.com/js_sdk/anzogame_sdk_v2.0.5.min.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/webview/js/base.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/common/js/echo-mix.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/common/js/data-echo-mix.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/webview/js/notSaveModel.js"></script>
         * <script src="http://static.zhangyoubao.com/zyb_app/webview/js/sdkEvent.js"></script>
         * <script>
         * window.onload = function() {
         * <p>
         * };
         * </script>
         * </body>
         * </html>
         * good_count : 292
         * down_count : 23
         * comment_count : 90
         * image_count : 0
         * image_urls : []
         * cover_url : http://avatar.zhangyoubao.com/yxzj/topic_cover/212/855/9a7/a0fFH_3fx.jpg
         * pv_count : 0
         * author_detail : {"user_id":"15669697","user_name":"武德哪吒","avatar_url":"http://avatar.zhangyoubao.com/pic/user/avatar/e3/57fdd2a315669697.jpg","signature":"新浪微博:武德哪吒","certification_title":1,"certification_describes":"装备分析师"}
         * is_third : 0
         * third_url :
         * title_long :
         * share_url : http://m.zhangyoubao.com/yxzj/detail/3124269987686494157?source=shareout
         * videos : []
         * userLogoFrameId : 0
         */

        private String id;
        private String post_id;
        private String user_id;
        private String user_name;
        private String avatar_url;
        private int certification_title;
        private String certification_describes;
        private String publish_time;
        private String reply_time;
        private String create_time;
        private String update_time;
        private String type;
        private String author;
        private String is_fav;
        private String is_up;
        private String is_down;
        private String has_video;
        private String is_lock;
        private String sticky_type;
        private String essence_type;
        private String is_editor;
        private String status;
        private String title;
        private String description;
        private String content;
        private String good_count;
        private String down_count;
        private int comment_count;
        private String image_count;
        private String cover_url;
        private String pv_count;
        private AuthorDetailEntity author_detail;
        private String is_third;
        private String third_url;
        private String title_long;
        private String share_url;
        private int userLogoFrameId;
        private List<?> image_urls;
        private List<VideosBean> videos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getCertification_title() {
            return certification_title;
        }

        public void setCertification_title(int certification_title) {
            this.certification_title = certification_title;
        }

        public String getCertification_describes() {
            return certification_describes;
        }

        public void setCertification_describes(String certification_describes) {
            this.certification_describes = certification_describes;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getReply_time() {
            return reply_time;
        }

        public void setReply_time(String reply_time) {
            this.reply_time = reply_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIs_fav() {
            return is_fav;
        }

        public void setIs_fav(String is_fav) {
            this.is_fav = is_fav;
        }

        public String getIs_up() {
            return is_up;
        }

        public void setIs_up(String is_up) {
            this.is_up = is_up;
        }

        public String getIs_down() {
            return is_down;
        }

        public void setIs_down(String is_down) {
            this.is_down = is_down;
        }

        public String getHas_video() {
            return has_video;
        }

        public void setHas_video(String has_video) {
            this.has_video = has_video;
        }

        public String getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(String is_lock) {
            this.is_lock = is_lock;
        }

        public String getSticky_type() {
            return sticky_type;
        }

        public void setSticky_type(String sticky_type) {
            this.sticky_type = sticky_type;
        }

        public String getEssence_type() {
            return essence_type;
        }

        public void setEssence_type(String essence_type) {
            this.essence_type = essence_type;
        }

        public String getIs_editor() {
            return is_editor;
        }

        public void setIs_editor(String is_editor) {
            this.is_editor = is_editor;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGood_count() {
            return good_count;
        }

        public void setGood_count(String good_count) {
            this.good_count = good_count;
        }

        public String getDown_count() {
            return down_count;
        }

        public void setDown_count(String down_count) {
            this.down_count = down_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getImage_count() {
            return image_count;
        }

        public void setImage_count(String image_count) {
            this.image_count = image_count;
        }

        public String getCover_url() {
            return cover_url;
        }

        public void setCover_url(String cover_url) {
            this.cover_url = cover_url;
        }

        public String getPv_count() {
            return pv_count;
        }

        public void setPv_count(String pv_count) {
            this.pv_count = pv_count;
        }

        public AuthorDetailEntity getAuthor_detail() {
            return author_detail;
        }

        public void setAuthor_detail(AuthorDetailEntity author_detail) {
            this.author_detail = author_detail;
        }

        public String getIs_third() {
            return is_third;
        }

        public void setIs_third(String is_third) {
            this.is_third = is_third;
        }

        public String getThird_url() {
            return third_url;
        }

        public void setThird_url(String third_url) {
            this.third_url = third_url;
        }

        public String getTitle_long() {
            return title_long;
        }

        public void setTitle_long(String title_long) {
            this.title_long = title_long;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getUserLogoFrameId() {
            return userLogoFrameId;
        }

        public void setUserLogoFrameId(int userLogoFrameId) {
            this.userLogoFrameId = userLogoFrameId;
        }

        public List<?> getImage_urls() {
            return image_urls;
        }

        public void setImage_urls(List<?> image_urls) {
            this.image_urls = image_urls;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class AuthorDetailEntity {
            /**
             * user_id : 15669697
             * user_name : 武德哪吒
             * avatar_url : http://avatar.zhangyoubao.com/pic/user/avatar/e3/57fdd2a315669697.jpg
             * signature : 新浪微博:武德哪吒
             * certification_title : 1
             * certification_describes : 装备分析师
             */

            private String user_id;
            private String user_name;
            private String avatar_url;
            private String signature;
            private int certification_title;
            private String certification_describes;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public int getCertification_title() {
                return certification_title;
            }

            public void setCertification_title(int certification_title) {
                this.certification_title = certification_title;
            }

            public String getCertification_describes() {
                return certification_describes;
            }

            public void setCertification_describes(String certification_describes) {
                this.certification_describes = certification_describes;
            }
        }

        public static class VideosBean {


            /**
             * id : 4327
             * iframe_height : 400
             * iframe_src : http://www.bilibili.com/video/av9353327/?from=search&seid=12625368896553444185
             * iframe_width : 480
             * image_url : http://avatar.zhangyoubao.com/yxzj/video_cover/2e2/495/2a3/bad3l7Ovm.jpg
             * is_live : 0
             * last_parse_time : 1490436787
             * source_site : 未知
             * source_url : http://www.bilibili.com/video/av9353327/?from=search&seid=12625368896553444185
             * title : 【王者荣耀欢乐吐槽】《煌段子峡谷》第四期
             * video_height : 210
             * video_length : 341.00
             * video_sizes : {"hd":51600272,"multi_mp4_hd":154800816,"multi_mp4_sd":0,"multi_mp4_shd":0,"sd":24443109,"shd":0}
             * video_type : m3u8
             * video_urls : {"hd":"","multi_mp4_hd":[{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://cn-hbcd-cu-v-05.acgvideo.com/vg1/9/96/15457077-1-hd.mp4?expires=1490360100&platform=pc&ssig=rdLq49H4bM8XmPP61kpaVQ&oi=1992174840&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1"},{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://cn-zjwz-cu-v-01.acgvideo.com/vg7/7/f3/15457077-1.mp4?expires=1490360100&platform=pc&ssig=WI6DpkwcjwtAM_npcGwGvw&oi=1992174840&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1"},{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://ws.acgvideo.com/f/f3/15457077-1-hd.mp4?wsTime=1490360356&platform=pc&wsSecret2=d5bcf5bb480e42d0971341e1c6cb808e&oi=1992174840&rate=5"}],"multi_mp4_sd":[],"multi_mp4_shd":[],"sd":"http://cn-zjwz-cu-v-01.acgvideo.com/vg7/7/f3/15457077-1.mp4?expires=1490447400&platform=pc&ssig=4M4O5s899yjCyqCdknGYzA&oi=1992174503&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1","shd":""}
             * video_width : 308
             */

            private String id;
            private String iframe_height;
            private String iframe_src;
            private String iframe_width;
            private String image_url;
            private String is_live;
            private String last_parse_time;
            private String source_site;
            private String source_url;
            private String title;
            private String video_height;
            private String video_length;
            private VideoSizesEntity video_sizes;
            private String video_type;
            private VideoUrlsEntity video_urls;
            private String video_width;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIframe_height() {
                return iframe_height;
            }

            public void setIframe_height(String iframe_height) {
                this.iframe_height = iframe_height;
            }

            public String getIframe_src() {
                return iframe_src;
            }

            public void setIframe_src(String iframe_src) {
                this.iframe_src = iframe_src;
            }

            public String getIframe_width() {
                return iframe_width;
            }

            public void setIframe_width(String iframe_width) {
                this.iframe_width = iframe_width;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getIs_live() {
                return is_live;
            }

            public void setIs_live(String is_live) {
                this.is_live = is_live;
            }

            public String getLast_parse_time() {
                return last_parse_time;
            }

            public void setLast_parse_time(String last_parse_time) {
                this.last_parse_time = last_parse_time;
            }

            public String getSource_site() {
                return source_site;
            }

            public void setSource_site(String source_site) {
                this.source_site = source_site;
            }

            public String getSource_url() {
                return source_url;
            }

            public void setSource_url(String source_url) {
                this.source_url = source_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVideo_height() {
                return video_height;
            }

            public void setVideo_height(String video_height) {
                this.video_height = video_height;
            }

            public String getVideo_length() {
                return video_length;
            }

            public void setVideo_length(String video_length) {
                this.video_length = video_length;
            }

            public VideoSizesEntity getVideo_sizes() {
                return video_sizes;
            }

            public void setVideo_sizes(VideoSizesEntity video_sizes) {
                this.video_sizes = video_sizes;
            }

            public String getVideo_type() {
                return video_type;
            }

            public void setVideo_type(String video_type) {
                this.video_type = video_type;
            }

            public VideoUrlsEntity getVideo_urls() {
                return video_urls;
            }

            public void setVideo_urls(VideoUrlsEntity video_urls) {
                this.video_urls = video_urls;
            }

            public String getVideo_width() {
                return video_width;
            }

            public void setVideo_width(String video_width) {
                this.video_width = video_width;
            }

            public static class VideoSizesEntity {
                /**
                 * hd : 51600272
                 * multi_mp4_hd : 154800816
                 * multi_mp4_sd : 0
                 * multi_mp4_shd : 0
                 * sd : 24443109
                 * shd : 0
                 */

                private int hd;
                private int multi_mp4_hd;
                private int multi_mp4_sd;
                private int multi_mp4_shd;
                private int sd;
                private int shd;

                public int getHd() {
                    return hd;
                }

                public void setHd(int hd) {
                    this.hd = hd;
                }

                public int getMulti_mp4_hd() {
                    return multi_mp4_hd;
                }

                public void setMulti_mp4_hd(int multi_mp4_hd) {
                    this.multi_mp4_hd = multi_mp4_hd;
                }

                public int getMulti_mp4_sd() {
                    return multi_mp4_sd;
                }

                public void setMulti_mp4_sd(int multi_mp4_sd) {
                    this.multi_mp4_sd = multi_mp4_sd;
                }

                public int getMulti_mp4_shd() {
                    return multi_mp4_shd;
                }

                public void setMulti_mp4_shd(int multi_mp4_shd) {
                    this.multi_mp4_shd = multi_mp4_shd;
                }

                public int getSd() {
                    return sd;
                }

                public void setSd(int sd) {
                    this.sd = sd;
                }

                public int getShd() {
                    return shd;
                }

                public void setShd(int shd) {
                    this.shd = shd;
                }
            }

            public static class VideoUrlsEntity {
                /**
                 * hd :
                 * multi_mp4_hd : [{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://cn-hbcd-cu-v-05.acgvideo.com/vg1/9/96/15457077-1-hd.mp4?expires=1490360100&platform=pc&ssig=rdLq49H4bM8XmPP61kpaVQ&oi=1992174840&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1"},{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://cn-zjwz-cu-v-01.acgvideo.com/vg7/7/f3/15457077-1.mp4?expires=1490360100&platform=pc&ssig=WI6DpkwcjwtAM_npcGwGvw&oi=1992174840&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1"},{"bytes":51600272,"no":0,"seconds":341,"type":"mp4","url":"http://ws.acgvideo.com/f/f3/15457077-1-hd.mp4?wsTime=1490360356&platform=pc&wsSecret2=d5bcf5bb480e42d0971341e1c6cb808e&oi=1992174840&rate=5"}]
                 * multi_mp4_sd : []
                 * multi_mp4_shd : []
                 * sd : http://cn-zjwz-cu-v-01.acgvideo.com/vg7/7/f3/15457077-1.mp4?expires=1490447400&platform=pc&ssig=4M4O5s899yjCyqCdknGYzA&oi=1992174503&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1
                 * shd :
                 */

                private String hd;
                private String sd;
                private String shd;
                private List<MultiMp4HdEntity> multi_mp4_hd;
                private List<?> multi_mp4_sd;
                private List<?> multi_mp4_shd;

                public String getHd() {
                    return hd;
                }

                public void setHd(String hd) {
                    this.hd = hd;
                }

                public String getSd() {
                    return sd;
                }

                public void setSd(String sd) {
                    this.sd = sd;
                }

                public String getShd() {
                    return shd;
                }

                public void setShd(String shd) {
                    this.shd = shd;
                }

                public List<MultiMp4HdEntity> getMulti_mp4_hd() {
                    return multi_mp4_hd;
                }

                public void setMulti_mp4_hd(List<MultiMp4HdEntity> multi_mp4_hd) {
                    this.multi_mp4_hd = multi_mp4_hd;
                }

                public List<?> getMulti_mp4_sd() {
                    return multi_mp4_sd;
                }

                public void setMulti_mp4_sd(List<?> multi_mp4_sd) {
                    this.multi_mp4_sd = multi_mp4_sd;
                }

                public List<?> getMulti_mp4_shd() {
                    return multi_mp4_shd;
                }

                public void setMulti_mp4_shd(List<?> multi_mp4_shd) {
                    this.multi_mp4_shd = multi_mp4_shd;
                }

                public static class MultiMp4HdEntity {
                    /**
                     * bytes : 51600272
                     * no : 0
                     * seconds : 341
                     * type : mp4
                     * url : http://cn-hbcd-cu-v-05.acgvideo.com/vg1/9/96/15457077-1-hd.mp4?expires=1490360100&platform=pc&ssig=rdLq49H4bM8XmPP61kpaVQ&oi=1992174840&nfa=cBb9LpM4vzCHtlWJ7C8l1g==&dynamic=1
                     */

                    private int bytes;
                    private int no;
                    private int seconds;
                    private String type;
                    private String url;

                    public int getBytes() {
                        return bytes;
                    }

                    public void setBytes(int bytes) {
                        this.bytes = bytes;
                    }

                    public int getNo() {
                        return no;
                    }

                    public void setNo(int no) {
                        this.no = no;
                    }

                    public int getSeconds() {
                        return seconds;
                    }

                    public void setSeconds(int seconds) {
                        this.seconds = seconds;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }
                }
            }
        }

    }


}
