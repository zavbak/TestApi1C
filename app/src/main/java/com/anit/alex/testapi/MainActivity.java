package com.anit.alex.testapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anit.alex.testapi.api.ApiInterface;
import com.anit.alex.testapi.entity.PostModelReceived;
import com.anit.alex.testapi.entity.PostModelSend;
import com.anit.alex.testapi.api.autorithation.AutoritationManager;
import com.anit.alex.testapi.app.App;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btTestPost, btTestRx;
    TextView tvResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GetResponse myTack = new GetResponse();
        //myTack.execute();
        btTestPost = (Button) findViewById(R.id.btTestPost);
        btTestRx = (Button) findViewById(R.id.btTestRx);
        tvResponse = (TextView) findViewById(R.id.tvResponse);

        btTestPost.setOnClickListener(this);
        btTestRx.setOnClickListener(this);


        PostModelSend postModelSend = new PostModelSend();
        postModelSend.setStrsendclient("Hello from client " + new Date());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btTestPost:



                PostModelSend postModelSend = new PostModelSend();
                postModelSend.setStrsendclient("Hello from Client " + new Date());

                App.getApiInterface().getPOST(AutoritationManager.getStringAutorization("Админ", "админ1")
                        , postModelSend).enqueue(new Callback<PostModelReceived>() {

                    @Override
                    public void onResponse(Call<PostModelReceived> call, Response<PostModelReceived> response) {

                        String str = ((PostModelReceived) response.body()).toString();

                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostModelReceived> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        tvResponse.setText(t.getMessage());
                    }
                });


                //new POSTResponse().execute();
                break;

            case R.id.btTestRx:


                final PostModelSend postModelSend1 = new PostModelSend();
                postModelSend1.setStrsendclient("Hello from Client " + new Date());

                final String aut = AutoritationManager.getStringAutorization("Админ1", "админ1");

                Observable<PostModelReceived> postModelReceivedObserver = App.getApiInterface().getPOSTObservable(aut, postModelSend1);


                postModelReceivedObserver.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PostModelReceived>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(PostModelReceived postModelReceived) {
                                Toast.makeText(MainActivity.this, postModelReceived.toString(), Toast.LENGTH_SHORT).show();
                                tvResponse.setText(postModelReceived.toString());
                            }
                        });


                break;


        }
    }


    class POSTResponse extends AsyncTask<Void, Void, Void> {

        private ApiInterface sGetApiI;
        private Retrofit mRetrofit;

        @Override
        protected Void doInBackground(Void... params) {


            PostModelSend postModelSend = new PostModelSend();
            postModelSend.setStrsendclient("Hello from client " + new Date());


            try {

                //String login = URLEncoder.encode("Админ", "UTF-8");
                //String pass = URLEncoder.encode("админ1", "UTF-8");
                //Response response = sGetApiI.getPOST(Credentials.basic(login,pass),postModelSend).execute();

                Response response = App.getApiInterface().getPOST(AutoritationManager.getStringAutorization("Админ", "админ1")
                        , postModelSend).execute();

                PostModelReceived postModelReceived = (PostModelReceived) response.body();

                Log.d("anit", postModelReceived.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}
