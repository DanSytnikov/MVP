package com.example.user.weather;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;

import io.realm.gradle.Realm;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyIntentService extends IntentService {
    private WeakReference<MainActivity> activity;
    public RetroInterface retroInterface;

    private Retrofit retrofit;

    public MyIntentService() {
        super("myname");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SRVC", "OnCreated.");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        retroInterface = retrofit.create(RetroInterface.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Log.d("RUN", "Thread started.");
            Call<Example> responseCall = retroInterface.getWeather(String.valueOf(46.469391), String.valueOf(30.740883), "a7c76f80c9580699351007ff55f2e86d");
            Response<Example> res = responseCall.execute();
            Log.d("RUN", "Resp result: " + res.code());

//            return res;

        } catch (IOException e) {
            e.printStackTrace();
//            return null;
        }


    }
}

