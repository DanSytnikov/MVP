package com.example.user.weather.Weather;

public interface WeatherPresenter extends WeatherInteractor.WeatherCallback {
    void getWeather();
    void onDestroy();
}
