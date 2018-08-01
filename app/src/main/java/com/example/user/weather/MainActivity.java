package com.example.user.weather;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weather.Weather.WeatherPresenter;
import com.example.user.weather.Weather.WeatherPresenterImpl;
import com.example.user.weather.Weather.WeatherView;
import com.example.user.weather.respModel.Data;

import java.util.List;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements WeatherView {

    public Example data;
    private WeatherAdapter adapter;
    WeatherPresenter weatherPresenter;
    RecyclerView rv;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("PERMISSION", "granted");    // permission granted
                } else {
                    Log.e("PERMISSION", "denied");
                    finish();   //permission denied
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recView);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapter = new WeatherAdapter();
        rv.setAdapter(adapter);
        weatherPresenter = new WeatherPresenterImpl(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick(View view) {
        weatherPresenter.getWeather();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void showWeather(List<Ticket> examples) {
        adapter.swapItems(examples);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void showError(String e) {
        Toast.makeText(this, e, Toast.LENGTH_LONG).show();
    }
}