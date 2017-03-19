package com.jkdev.wzryzhangyb.bean;

import java.util.List;

/**
 * 首页标签bean
 * <p>
 * Created by KJ on 2017/3/18.
 */

public class TagListBean {

    /**
     * code : 200
     * data : [{"id":"0","name":"最新"},{"id":"5","name":"资讯"},{"id":"4","name":"攻略"},{"id":"3","name":"视频"},{"id":"2","name":"娱乐"},{"id":"98","name":"赛事"}]
     * message : OK
     * list_size : 10
     */

    private int code;
    private String message;
    private int list_size;
    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * id : 0
         * name : 最新
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
