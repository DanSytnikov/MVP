package com.example.user.weather;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static java.net.Proxy.Type.HTTP;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    private static RetroInterface retroInterface;
    private Retrofit retrofit;
    public Handler handler;
    Response response;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        retroInterface = retrofit.create(RetroInterface.class); //Создаем объект, при помощи которого будем выполнять запросы

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                response = (Response) msg.obj;
            }
        };
    }

    public String tempr ;



    public static RetroInterface getApi() {
        return retroInterface;
    }

    //Response response = getApi().getWeather("Odessa", "a7c76f80c9580699351007ff55f2e86d").execute();


    public void onClick(View view) {
        GetOpenWeather threadWeather = new GetOpenWeather();
        threadWeather.start();
        Log.e("RESPONSE", "");

    }

    public class GetOpenWeather extends Thread {
        @Override
        public void run() {
            try {
                Log.d("RUN", "Thread started.");

                Call<ResponseBody> responseCall = retroInterface.getWeather("Odessa", "a7c76f80c9580699351007ff55f2e86d");
                Response<ResponseBody> res = responseCall.execute();
                ResponseBody weatherResponse = res.body();
                Log.d("RUN", "Response's ready.");

                Message msg = new Message();
                msg.obj = weatherResponse;
                Log.d("RUN", "Object sent");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public class ThreadWeather extends Thread {
        @Override
        public void run() {
            Log.e(TAG, "I'm alive");
            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Odessa&appid=a7c76f80c9580699351007ff55f2e86d");
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    Log.e(TAG, sb.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "I WANT2DIE");
            }
        }
    }
}