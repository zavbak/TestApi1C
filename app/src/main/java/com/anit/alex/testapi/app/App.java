package com.anit.alex.testapi.app;

import android.app.Application;
import android.content.Context;

import com.anit.alex.testapi.api.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 20.04.2017.
 */

public class App extends Application{

    public static ApiInterface sApiInterface;
    private Retrofit retrofit;


    @Override
    public void onCreate() {
        super.onCreate();
        init();

    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.31.255.150/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        sApiInterface = retrofit.create(ApiInterface.class);
    }

    public static ApiInterface getApiInterface() {
        return sApiInterface;
    }


}
