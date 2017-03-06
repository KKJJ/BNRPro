package com.nettest;

/**
 * Created by Kuang on 2017/3/3.
 */

public class DataModel {

    private int imgId;
    private String desc;

    public DataModel(int imgId, String desc) {
        this.imgId = imgId;
        this.desc = desc;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "imgId=" + imgId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
