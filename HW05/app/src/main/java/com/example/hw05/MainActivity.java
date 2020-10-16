/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * MainActivity.java
 * */

package com.example.hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final String TAG = "demo";
    public static final String WEATHER_INFO = "weather info";

    ListView listView;
    ArrayList<String> countriesList= new ArrayList<>(Data.cities.keySet());
    ArrayList<String> citiesList = new ArrayList<String>();
    ArrayAdapter<String> citiesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Cities");

        listView = (ListView) findViewById(R.id.listView);

        for (String key : countriesList) {
            ArrayList<String> citiesKey = Data.cities.get(key);
            for (String city : citiesKey) {
                citiesList.add(city + ", " + key);
            }
        }

        citiesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, citiesList);
        listView.setAdapter(citiesArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CurrentWeatherActivity.class);
                intent.putExtra(WEATHER_INFO, citiesList.get(position));
                startActivity(intent);

            }
        });

    }
}