package com.example.user.weather.Weather;

import com.example.user.weather.Ticket;

import java.util.List;

import javax.security.auth.callback.Callback;

public interface WeatherInteractor {

    void getWeather(WeatherCallback callback);
    void cancel();
    interface WeatherCallback{
        void onSuccess(List<Ticket> ticketList);
        void onError();
    }
}
