package com.jkdev.wzryzhangyb.bean;

import java.util.List;

/**
 * Created by Kuang on 2017/3/22.
 */

public class MessageListDataBean {


    /**
     * code : 200
     * data : [{"type":"announcement","title":"掌游小宝","unread":"0","content":"欢迎消息","max_id":"1","create_time":"1489382367"}]
     * message : OK
     * list_size : 10
     */

    private int code;
    private String message;
    private int list_size;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : announcement
         * title : 掌游小宝
         * unread : 0
         * content : 欢迎消息
         * max_id : 1
         * create_time : 1489382367
         */

        private String type;
        private String title;
        private String unread;
        private String content;
        private String max_id;
        private String create_time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnread() {
            return unread;
        }

        public void setUnread(String unread) {
            this.unread = unread;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMax_id() {
            return max_id;
        }

        public void setMax_id(String max_id) {
            this.max_id = max_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
