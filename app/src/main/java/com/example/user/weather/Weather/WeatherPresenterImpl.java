package com.example.user.weather.Weather;

import android.view.View;

import com.example.user.weather.Ticket;

import java.util.List;

public class WeatherPresenterImpl implements WeatherPresenter {
    private WeatherView weatherView;
    private WeatherInteractor weatherInteractor;

    public WeatherPresenterImpl(WeatherView wView) {
        weatherView = wView;
        weatherInteractor = new WeatherInteractorImpl();
    }

    @Override
    public void getWeather() {
        weatherView.showProgress();
        weatherInteractor.getWeather(this);
    }

    @Override
    public void onDestroy() {
        weatherInteractor.cancel();
        weatherInteractor = null;
        weatherView = null;
    }

    @Override
    public void onSuccess(List<Ticket> ticketList) {
        weatherView.hideProgress();
        weatherView.showWeather(ticketList);
    }

    @Override
    public void onError() {
        weatherView.showError("Error occurred");
    }
}
