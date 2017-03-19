package com.jkdev.wzryzhangyb.bean;

import java.util.List;

/**
 * 首页轮播图数据bean
 * <p>
 * Created by KJ on 2017/3/19.
 */

public class AdListDataBean {


    /**
     * code : 200
     * data : {"is_closable":"0","is_closed":"0","list":[{"id":"89","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/2a9/5ef/6aa/67bPJRd8p.jpg","redirect_data":"3121148949862141647","redirect_type":"3"},{"id":"88","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/123/63b/a9f/70aPhfK5w.jpg","redirect_data":"3119816892663842856","redirect_type":"3"},{"id":"74","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/a76/681/149/503TkI2hv.jpg","redirect_data":"3112439029774011752","redirect_type":"3"},{"id":"41","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/ff6/0a3/c19/23dlYCW10.jpg","redirect_data":"3123861481258985856","redirect_type":"3"},{"id":"23","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/60b/942/e60/104VxgxD8.jpg","redirect_data":"http://m.zhangyoubao.com/down/index","redirect_type":"2"}]}
     * list_size : 10
     * message : OK
     */

    private int code;
    private DataEntity data;
    private int list_size;
    private String message;

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

    public int getList_size() {
        return list_size;
    }

    public void setList_size(int list_size) {
        this.list_size = list_size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataEntity {
        /**
         * is_closable : 0
         * is_closed : 0
         * list : [{"id":"89","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/2a9/5ef/6aa/67bPJRd8p.jpg","redirect_data":"3121148949862141647","redirect_type":"3"},{"id":"88","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/123/63b/a9f/70aPhfK5w.jpg","redirect_data":"3119816892663842856","redirect_type":"3"},{"id":"74","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/a76/681/149/503TkI2hv.jpg","redirect_data":"3112439029774011752","redirect_type":"3"},{"id":"41","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/ff6/0a3/c19/23dlYCW10.jpg","redirect_data":"3123861481258985856","redirect_type":"3"},{"id":"23","image_url":"http://avatar.zhangyoubao.com/yxzj/ad/60b/942/e60/104VxgxD8.jpg","redirect_data":"http://m.zhangyoubao.com/down/index","redirect_type":"2"}]
         */

        private String is_closable;
        private String is_closed;
        private List<ListEntity> list;

        public String getIs_closable() {
            return is_closable;
        }

        public void setIs_closable(String is_closable) {
            this.is_closable = is_closable;
        }

        public String getIs_closed() {
            return is_closed;
        }

        public void setIs_closed(String is_closed) {
            this.is_closed = is_closed;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            /**
             * id : 89
             * image_url : http://avatar.zhangyoubao.com/yxzj/ad/2a9/5ef/6aa/67bPJRd8p.jpg
             * redirect_data : 3121148949862141647
             * redirect_type : 3
             */

            private String id;
            private String image_url;
            private String redirect_data;
            private String redirect_type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getRedirect_data() {
                return redirect_data;
            }

            public void setRedirect_data(String redirect_data) {
                this.redirect_data = redirect_data;
            }

            public String getRedirect_type() {
                return redirect_type;
            }

            public void setRedirect_type(String redirect_type) {
                this.redirect_type = redirect_type;
            }
        }
    }
}
