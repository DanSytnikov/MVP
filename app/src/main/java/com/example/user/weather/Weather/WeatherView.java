package com.example.user.weather.Weather;

import com.example.user.weather.Example;
import com.example.user.weather.Ticket;

import java.util.List;

public interface WeatherView {
     void showWeather (List<Ticket> examples);
     void showProgress();
     void hideProgress();
     void showError(String e);
}
