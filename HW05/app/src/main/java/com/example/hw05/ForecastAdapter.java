/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * ForecastAdapter.java
 * */

package com.example.hw05;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import okhttp3.Callback;

public class ForecastAdapter extends ArrayAdapter<Weather> {
    public ForecastAdapter(@NonNull Context context, int resource, @NonNull List<Weather> objects) {
        super((Context) context, resource, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Weather weather = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.forecast, parent, false);

        TextView adapterTime = (TextView) convertView.findViewById(R.id.adapterTime);
        TextView adapterTemp = (TextView) convertView.findViewById(R.id.adapterTemp);
        TextView adapterMaxTemp = (TextView) convertView.findViewById(R.id.adapterMaxTemp);
        TextView adapterMinTemp = (TextView) convertView.findViewById(R.id.adapterMinTemp);
        TextView adapterHumidity = (TextView) convertView.findViewById(R.id.adapterHumidity);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        ImageView adapterImageView = (ImageView) convertView.findViewById(R.id.adapterImageView);

        adapterTime.setText(weather.getTime());
        adapterTemp.setText(weather.getTemp() + " F");
        adapterMaxTemp.setText(weather.getMaxTemp() + " F");
        adapterMinTemp.setText(weather.getMinTemp() + " F");
        adapterHumidity.setText(weather.getHumidity() + "%");
        description.setText(weather.getDescription());

        String urlBegin = "http://openweathermap.org/img/wn/";
        String urlMiddle = weather.getIcon();
        String urlEnd = "@2x.png";

        String url = urlBegin + urlMiddle + urlEnd;

        Picasso.get().load(url).into(adapterImageView);

        return convertView;
    }
}
