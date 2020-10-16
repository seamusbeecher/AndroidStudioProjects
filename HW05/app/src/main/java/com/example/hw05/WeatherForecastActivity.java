/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * WeatherForecastActivity.java
 * */

package com.example.hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherForecastActivity extends AppCompatActivity {

    public final String TAG = "demo";

    private final OkHttpClient client = new OkHttpClient();

    TextView titleForecast;
    ListView listViewForecast;
    ArrayList<Weather> weathersForecast = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        titleForecast = (TextView) findViewById(R.id.titleForecast);
        listViewForecast = (ListView) findViewById(R.id.listViewForecast);

        Intent intentForecast = getIntent();
        final String fullName = intentForecast.getStringExtra(CurrentWeatherActivity.WEATHER_FORE);
        String[] temp = fullName.split(",");
        String city = temp[0];
        String country = temp[1];

        setTitle("Weather Forecast");
        titleForecast.setText(fullName);

        getWeatherForecast(city, country);

    }

    public void getWeatherForecast(String city, String country) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.openweathermap.org/data/2.5/forecast").newBuilder();
        urlBuilder.addQueryParameter("q", city + "," + country);
        urlBuilder.addQueryParameter("appid", "b5a6fd04fcf998a4d31139a3e193005f");
        urlBuilder.addQueryParameter("units", "imperial");

        String url = urlBuilder.build().toString();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {

                    String body = response.body().string();

                    try {

                        JSONObject jsonRoot = new JSONObject(body);
                        Log.d(TAG, "onResponce(JSON-Root): " + body);

                        final JSONArray jsonList = jsonRoot.getJSONArray("list");
                        Log.d(TAG, "onResponce(JSON-Weather): " + jsonList.toString());

                        for (int i = 0; i < jsonList.length(); i++) {
                            JSONObject jsonWeatherF = jsonList.getJSONObject(i);
                            Weather weather = new Weather(jsonWeatherF);
                            weathersForecast.add(weather);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ForecastAdapter forecastAdapter = new ForecastAdapter(WeatherForecastActivity.this, R.layout.forecast, weathersForecast);
                                listViewForecast.setAdapter(forecastAdapter);
                            }
                        });

                        Log.d(TAG, "WetaherObjects:  " + weathersForecast.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }

}