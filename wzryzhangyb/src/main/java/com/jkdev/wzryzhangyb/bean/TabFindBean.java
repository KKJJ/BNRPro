package com.jkdev.wzryzhangyb.bean;

/**
 * Created by Kuang on 2017/3/23.
 */

public class TabFindBean {

    private int img;
    private String title;

    public TabFindBean(int img, String title) {
        this.img = img;
        this.title = title;
    }

    @Override
    public String toString() {
        return "TabFindBean{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}
