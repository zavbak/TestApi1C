package com.anit.alex.testapi.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Alex on 20.04.2017.
 */

public class PostModelSend {

    @SerializedName("strsendclient")
    @Expose
    private String strsendclient;

    public String getStrsendclient() {
        return strsendclient;
    }

    public void setStrsendclient(String strsendclient) {
        this.strsendclient = strsendclient;
    }



}
