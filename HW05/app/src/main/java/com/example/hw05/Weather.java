/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * weather.java
 * */

package com.example.hw05;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    String time, temp, maxTemp, minTemp, humidity, description, icon;

    public Weather(String time, String temp, String maxTemp, String minTemp, String humidity, String description, String icon) {
        this.time = time;
        this.temp = temp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.description = description;
        this.icon = icon;
    }

    public Weather(JSONObject json) throws JSONException {

        this.time = json.getString("dt_txt");
        this.temp = json.getJSONObject("main").getString("temp");
        this.maxTemp = json.getJSONObject("main").getString("temp_max");
        this.minTemp = json.getJSONObject("main").getString("temp_min");
        this.humidity = json.getJSONObject("main").getString("humidity");
        this.description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        this.icon = json.getJSONArray("weather").getJSONObject(0).getString("icon");

    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", temp='" + temp + '\'' +
                ", maxTemp='" + maxTemp + '\'' +
                ", minTemp='" + minTemp + '\'' +
                ", humidity='" + humidity + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
