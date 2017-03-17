package com.jkdev.wzryzhangyb.bean;

import java.io.Serializable;

/**
 * Created by Kuang on 2017/3/17.
 */

public class Data implements Serializable {

    String apiVersion;
    String platform;
    String os;
    String osVersion;
    String secretSignature;
    String game;
    String sign;
    String platformVersion;
    String time;
    String secretId;
    String nonce;
    String userToken;
    String secretVersion;
    String userId;
    String api;
    String deviceId;

    public Data() {
    }

    public Data(String apiVersion, String platform, String os, String osVersion, String secretSignature, String game, String sign, String platformVersion, String time, String secretId, String nonce, String userToken, String secretVersion, String userId, String api, String deviceId) {
        this.apiVersion = apiVersion;
        this.platform = platform;
        this.os = os;
        this.osVersion = osVersion;
        this.secretSignature = secretSignature;
        this.game = game;
        this.sign = sign;
        this.platformVersion = platformVersion;
        this.time = time;
        this.secretId = secretId;
        this.nonce = nonce;
        this.userToken = userToken;
        this.secretVersion = secretVersion;
        this.userId = userId;
        this.api = api;
        this.deviceId = deviceId;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getSecretSignature() {
        return secretSignature;
    }

    public void setSecretSignature(String secretSignature) {
        this.secretSignature = secretSignature;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getSecretVersion() {
        return secretVersion;
    }

    public void setSecretVersion(String secretVersion) {
        this.secretVersion = secretVersion;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
