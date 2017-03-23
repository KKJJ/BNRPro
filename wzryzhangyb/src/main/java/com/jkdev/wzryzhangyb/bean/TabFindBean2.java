package com.jkdev.wzryzhangyb.bean;

/**
 * Created by Kuang on 2017/3/23.
 */

public class TabFindBean2 {

    private int img;
    private String title;
    private String desc;

    public TabFindBean2(int img, String title, String desc) {
        this.img = img;
        this.title = title;
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TabFindBean2{" +
                "img=" + img +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
