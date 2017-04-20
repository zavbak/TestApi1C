package com.anit.alex.testapi.api;







import com.anit.alex.testapi.entity.GetModel;
import com.anit.alex.testapi.entity.PostModelReceived;
import com.anit.alex.testapi.entity.PostModelSend;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Alex on 20.04.2017.
 */

public interface ApiInterface {
    @GET("/DB/hs/api/")
    Call<GetModel> getData();

    @POST("/DB/hs/api/")
    Call<PostModelReceived> getPOST(@Header("Authorization") String credentials, @Body PostModelSend postModelSend);

    @POST("/DB/hs/api/")
    Observable<PostModelReceived> getPOSTObservable(@Header("Authorization") String credentials, @Body PostModelSend postModelSend);
}
