package com.example.user.weather.Weather;

import android.util.Log;

import com.example.user.weather.Example;
import com.example.user.weather.RetroInterface;
import com.example.user.weather.Ticket;
import com.example.user.weather.respModel.Data;
import com.example.user.weather.respModel.Weather;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherInteractorImpl implements WeatherInteractor {

    private RetroInterface retroInterface;
    private Realm realm;
    private WeatherCallback callback;
    private Call<Example> responseCall;

    private OrderedRealmCollectionChangeListener<RealmResults<Example>> ordRealmColl
            = new OrderedRealmCollectionChangeListener<RealmResults<Example>>() {

        @Override
        public void onChange(RealmResults<Example> examples, OrderedCollectionChangeSet changeSet) {

            callback.onSuccess(mapData(examples));


        }
    };

    public WeatherInteractorImpl() {
        Log.d("SRVC", "OnCreated.");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        retroInterface = retrofit.create(RetroInterface.class); //Создаем объект, при помощи которого будем выполнять запросы
        realm = Realm.getDefaultInstance();

    }

    public ArrayList<Ticket> mapData(RealmResults<Example> examples) {
        RealmList<Data> dataList = examples.last().getData();
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < examples.last().getCnt() - 1; i++) {
            tickets.add(new Ticket(dateFormatter(new java.util.Date((long) dataList.get(i).getDt() * 1000L)),
                    (int) Math.round(dataList.get(i).getMain().getTemp() - 273.15),
                    dataList.get(i).getMain().getPressure(),
                    dataList.get(i).getMain().getHumidity(),
                    dataList.get(i).getWeather().get(0).getDescription(),
                    dataList.get(i).getWind().getSpeed(),
                    dataList.get(i).getWeather().get(0).getIcon()));
            Log.e("DATE", dateFormatter(new java.util.Date((long) dataList.get(i).getDt() * 1000L)));
        }
        return tickets;
    }

    public String dateFormatter(Date date) {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        String formattedDate = sdf.format(date);
        formattedDate = formattedDate.substring(5, formattedDate.length() - 13);
        return formattedDate;
    }

    @Override
    public void getWeather(final WeatherCallback callback) {

        this.callback = callback;

        final RealmResults<Example> realmResult = realm.where(Example.class).findAll();
        ArrayList<Ticket> arrTickets;
        if (realmResult.isEmpty()) {
            arrTickets = new ArrayList<>();
        } else {
            arrTickets = mapData(realmResult);
        }
        callback.onSuccess(arrTickets);
        realmResult.addChangeListener(ordRealmColl);


        Log.d("RUN", "Thread started.");
        responseCall = retroInterface.getWeather(String.valueOf(46.469391),
                String.valueOf(30.740883),
                "a7c76f80c9580699351007ff55f2e86d");
        responseCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> res) {
                Log.d("RUN", "Resp result: " + res.code());
                realm.beginTransaction();
                realm.insert(res.body());
                realm.commitTransaction();
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                callback.onError();
            }
        });
    }

    @Override
    public void cancel() {
        responseCall.cancel();
    }
}

