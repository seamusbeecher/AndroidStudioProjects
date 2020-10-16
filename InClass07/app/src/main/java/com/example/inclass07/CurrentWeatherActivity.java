package com.example.inclass07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentWeatherActivity extends AppCompatActivity {

    public final String TAG = "demo";
    private final OkHttpClient client = new OkHttpClient();

    TextView temp, maxTemp, minTemp, description, humidity, windSpeed, windDegree, cloudiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        temp = (TextView) findViewById(R.id.temp);
        maxTemp = (TextView) findViewById(R.id.maxTemp);
        minTemp = (TextView) findViewById(R.id.minTemp);
        description = (TextView) findViewById(R.id.description);
        humidity = (TextView) findViewById(R.id.humidity);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDegree = (TextView) findViewById(R.id.windDegree);
        cloudiness = (TextView) findViewById(R.id.cloudiness);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra(MainActivity.WEATHER_INFO);
        String[] temp = fullName.split(",");
        String city = temp[0];
        String country = temp[1];

        Log.d(TAG, "City: " + String.valueOf(city));
        Log.d(TAG, "Country: " + String.valueOf(country));

        setTitle(fullName);

        getWeather(city, country);

    }

    public void getWeather(String city, String country) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.openweathermap.org/data/2.5/weather").newBuilder();
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

                        final JSONArray jsonWeathers = jsonRoot.getJSONArray("weather");
                        Log.d(TAG, "onResponce(JSON-Weather): " + jsonWeathers.toString());

                        final JSONObject jsonMain = jsonRoot.getJSONObject("main");
                        Log.d(TAG, "onResponce(JSON-Main): " + jsonMain.toString());

                        final JSONObject jsonWind = jsonRoot.getJSONObject("wind");
                        Log.d(TAG, "onResponce(JSON-Wind): " + jsonWind.toString());

                        final JSONObject cloudWind = jsonRoot.getJSONObject("clouds");
                        Log.d(TAG, "onResponce(JSON-Cloud): " + cloudWind.toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    temp.setText(jsonMain.getInt("temp") + " F");
                                    maxTemp.setText(jsonMain.getInt("temp_max") + " F");
                                    minTemp.setText(jsonMain.getInt("temp_min") + " F");
                                    description.setText(jsonWeathers.getJSONObject(0).getString("description"));
                                    humidity.setText(jsonMain.getInt("humidity") + "%");
                                    windSpeed.setText(jsonWind.getInt("speed") + " miles/hour");
                                    windDegree.setText(jsonWind.getInt("deg") + " degrees");
                                    cloudiness.setText(cloudWind.getInt("all") + "%");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}