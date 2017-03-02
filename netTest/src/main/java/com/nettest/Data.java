package com.nettest;

import java.util.List;

/**
 * Created by Kuang on 2017/3/1.
 */

public class Data {


    /**
     * message : ok
     * nu : 428763978880
     * ischeck : 1
     * condition : F00
     * com : zhongtong
     * status : 200
     * state : 3
     * data : [{"time":"2017-02-27 11:53:11","ftime":"2017-02-27 11:53:11","context":"[北京丰台区] [北京市] [北京丰台区]的派件已签收 感谢使用中通快递,期待再次为您服务!","location":""},{"time":"2017-02-27 08:10:16","ftime":"2017-02-27 08:10:16","context":"[北京丰台区] [北京市] 快件已到达[北京丰台区],业务员玉泉营-新房子村正在第1次派件 电话:18519708299 请保持电话畅通、耐心等待","location":""},{"time":"2017-02-26 23:20:06","ftime":"2017-02-26 23:20:06","context":"[北京市内部] [北京市] 快件离开 [北京市内部]已发往[北京丰台区]","location":""},{"time":"2017-02-26 19:33:44","ftime":"2017-02-26 19:33:44","context":"[天津中转部] [天津市] 快件离开 [天津中转部]已发往[北京]","location":""},{"time":"2017-02-26 19:28:37","ftime":"2017-02-26 19:28:37","context":"[天津中转部] [天津市] 快件到达 [天津中转部]","location":""},{"time":"2017-02-25 23:35:31","ftime":"2017-02-25 23:35:31","context":"[上海浦东中心] [上海市] 快件离开 [上海浦东中心]已发往[天津中转部]","location":""},{"time":"2017-02-25 23:21:57","ftime":"2017-02-25 23:21:57","context":"[上海浦东中心] [上海市] 快件到达 [上海浦东中心]","location":""},{"time":"2017-02-25 19:48:21","ftime":"2017-02-25 19:48:21","context":"[南汇] [上海市] 快件离开 [南汇]已发往[上海浦东中心]","location":""},{"time":"2017-02-25 18:43:03","ftime":"2017-02-25 18:43:03","context":"[南汇] [上海市] [南汇]的宿猛已收件 电话:15821775508","location":""}]
     */

    public String message;
    public String nu;
    public String ischeck;
    public String condition;
    public String com;
    public String status;
    public String state;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * time : 2017-02-27 11:53:11
         * ftime : 2017-02-27 11:53:11
         * context : [北京丰台区] [北京市] [北京丰台区]的派件已签收 感谢使用中通快递,期待再次为您服务!
         * location :
         */

        public String time;
        public String ftime;
        public String context;
        public String location;

        @Override
        public String toString() {
            return "DataBean{" +
                    "time='" + time + '\'' +
                    ", ftime='" + ftime + '\'' +
                    ", context='" + context + '\'' +
                    ", location='" + location + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "message='" + message + '\'' +
                ", nu='" + nu + '\'' +
                ", ischeck='" + ischeck + '\'' +
                ", condition='" + condition + '\'' +
                ", com='" + com + '\'' +
                ", status='" + status + '\'' +
                ", state='" + state + '\'' +
                ", data=" + data +
                '}';
    }
}
