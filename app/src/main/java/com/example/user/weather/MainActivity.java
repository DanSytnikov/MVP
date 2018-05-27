package com.example.user.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    private static RetroInterface retroInterface;
    private Retrofit retrofit;
    public Response<Example> res;
    public Example data;


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


    public static RetroInterface getApi() {
        return retroInterface;
    }

    public void onClick(View view) {
        GetOpenWeather gow = new GetOpenWeather(this);
        gow.execute();
        Log.e("RESPONSE", "");

    }

    class GetOpenWeather extends AsyncTask<Void, Void, Response<Example>> {
        private WeakReference<MainActivity> activity;

        public GetOpenWeather(MainActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity main = activity.get();
            if (main != null) {

            }
            ProgressBar pg = findViewById(R.id.pb);
            pg.setVisibility(View.VISIBLE);

        }


        @Override
        protected Response<Example> doInBackground(Void... voids) {
            try {
                Log.d("RUN", "Thread started.");

                Call<Example> responseCall = retroInterface.getWeather("46.477474", "30.732622","a7c76f80c9580699351007ff55f2e86d");
                Response<Example> res = responseCall.execute();

                Log.d("RUN", "Resp result: " + res.code());


                //Log.d("RUN", "Res: " + res.raw().body().string());
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
            data = resp.body();
            if (data.getCod().equals("200")) {
                showToast("Success");
                TextView tv = findViewById(R.id.textView);
                tv.setText("" + data.getCity().getName());

            } else {
                showToast("Lost Connection...");
            }
        }
    }

}