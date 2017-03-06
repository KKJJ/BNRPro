package com.nettest;

/**
 * Created by Kuang on 2017/3/3.
 */

public class ListViewModle {

    private int imhId;
    private String desc;

    public ListViewModle(int imhId, String desc) {
        this.imhId = imhId;
        this.desc = desc;
    }

    public int getImhId() {
        return imhId;
    }

    public void setImhId(int imhId) {
        this.imhId = imhId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ListViewModle{" +
                "imhId=" + imhId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
