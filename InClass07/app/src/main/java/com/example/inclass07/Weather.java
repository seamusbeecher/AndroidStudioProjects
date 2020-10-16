package com.example.inclass07;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;


public class Weather {

    public double temp, maxTemp, minTemp, humidity;
    public String description;
    public double windSpeed, windDegree;
    public double cloudiness;

    public Weather() {

    }

    public Weather(JSONObject json) throws JSONException {

    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", humidity=" + humidity +
                ", description='" + description + '\'' +
                ", windSpeed=" + windSpeed +
                ", windDegree=" + windDegree +
                ", cloudiness=" + cloudiness +
                '}';
    }
}
