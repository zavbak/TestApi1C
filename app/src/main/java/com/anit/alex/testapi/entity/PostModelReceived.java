package com.anit.alex.testapi.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 20.04.2017.
 */

public class PostModelReceived {

    @SerializedName("strsendclient")
    @Expose
    private String strsendclient;
    @SerializedName("strsendserver")
    @Expose
    private String strsendserver;

    public String getStrsendclient() {
        return strsendclient;
    }

    public void setStrsendclient(String strsendclient) {
        this.strsendclient = strsendclient;
    }

    public String getStrsendserver() {
        return strsendserver;
    }

    public void setStrsendserver(String strsendserver) {
        this.strsendserver = strsendserver;
    }

    @Override
    public String toString() {
        return "PostModelReceived{" +
                "strsendclient='" + strsendclient + '\'' +
                ", strsendserver='" + strsendserver + '\'' +
                '}';
    }
}
