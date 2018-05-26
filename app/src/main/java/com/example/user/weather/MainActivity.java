package com.example.user.weather;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

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
    public Response<Example> res;
    public Example data;
    public int errorCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        retroInterface = retrofit.create(RetroInterface.class); //Создаем объект, при помощи которого будем выполнять запросы


    }


    public void showToast(String tstmsg) {
        Toast toast = Toast.makeText(getApplicationContext(), tstmsg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public String tempr;


    public static RetroInterface getApi() {
        return retroInterface;
    }

    //Response response = getApi().getWeather("Odessa", "a7c76f80c9580699351007ff55f2e86d").execute();
    //http://samples.openweathermap.org/data/2.5/forecast?id=698740&appid=a7c76f80c9580699351007ff55f2e86d


    public void onClick(View view) {
        GetOpenWeather gow = new GetOpenWeather();
        gow.execute();
        Log.e("RESPONSE", "");

    }

    class GetOpenWeather extends AsyncTask<Void, Void, Response<Example>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar pg = findViewById(R.id.pb);
            pg.setVisibility(View.VISIBLE);

        }

      /*  @Override
        public void doInBackground() {
            try {
                Log.d("RUN", "Thread started.");

                Call<Example> responseCall = retroInterface.getWeather("Odessa,ua", "a7c76f80c9580699351007ff55f2e86d");
                Response<Example> res = responseCall.execute();

                Log.d("RUN", "Response's ready.");

                Message msg = new Message();
                msg.obj = res;
                msg.arg1 = 1;
                handler.sendMessage(msg);
                Log.d("RUN", "Object sent");


            } catch (IOException e) {
                Message msg = new Message();
                msg.obj = e.getLocalizedMessage();
                msg.arg1 = 0;
                handler.sendMessage(msg);
                e.printStackTrace();
            }
            interrupt();
        }*/

        @Override
        protected Response<Example> doInBackground(Void... voids) {
            try {
                Log.d("RUN", "Thread started.");

                Call<Example> responseCall = retroInterface.getWeather("Odessa,ua", "a7c76f80c9580699351007ff55f2e86d");
                Response<Example> res = responseCall.execute();

                Log.d("RUN", "Response's ready.");


                Log.d("RUN", "Object sent");
                return res;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Response<Example> resp) {
            super.onPostExecute(resp);
            ProgressBar pg = findViewById(R.id.pb);
            pg.setVisibility(View.INVISIBLE);
            if (resp != null) {
                showToast("Connected");
                data = resp.body();
                Log.e("sa", String.valueOf(data.getCoord().getLat()));
                TextView tv = findViewById(R.id.textView);

                tv.setText(
                        "City: " + data.getName() +
                                "\nCoordinates: " + data.getCoord().getLon() + "   " + data.getCoord().getLat() +
                                "\nTemp: " + data.getMain().getTemp() + " Humidity: " + data.getMain().getHumidity() +
                                "\nVisibility: " + data.getVisibility() +
                                "\nWind: " + data.getWind().getSpeed() +
                                "\nClouds: " + data.getWeather().get(0).description);
            }
            else{
                showToast("Lost Connection...");
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