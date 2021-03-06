package com.example.user.weather;



import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetroInterface {
        @GET("/data/2.5/forecast")
        Call<Example> getWeather(@Query("lat") String cityLat, @Query("lon") String cityLon, @Query("appid") String appId);
}
