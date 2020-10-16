/**
 * In Class 05
 * AppListActivity
 * Seamus Beecher & Cam McIntosh
 */

package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

import edu.uncc.inclass05.utils.App;
import edu.uncc.inclass05.utils.Data;

public class AppsListActivity extends AppCompatActivity {

    ListView listView2;
    ArrayList<App> categoriesData;
    //TEST//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        final Intent intent = getIntent();
        listView2 = (ListView) findViewById(R.id.listView2);

        if (intent.hasExtra(AppCategoriesActivity.FREE_APPS)) {

            setTitle("Top Free Apps");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.FREE_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            Log.d("demo", "OnCreate: " + categoriesData);


        } else if (intent.hasExtra(AppCategoriesActivity.GROSSING_APPS)) {

            setTitle("Top Grossing Apps");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.GROSSING_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            Log.d("demo", "OnCreate: " + categoriesData);

        } else if (intent.hasExtra(AppCategoriesActivity.PAID_APPS)) {

            setTitle("Top Paid Apps");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.PAID_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            Log.d("demo", "OnCreate: " + categoriesData);

        } else if (intent.hasExtra(AppCategoriesActivity.NEW_APPS)) {

            setTitle("New Apps We Love");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.NEW_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            Log.d("demo", "OnCreate: " + categoriesData);

        } else if (intent.hasExtra(AppCategoriesActivity.GAME_APPS)) {

            setTitle("New Games We Love");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.GAME_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            Log.d("demo", "OnCreate: " + categoriesData);

        } else {
            Toast.makeText(this, "Nothing was sent to intent", Toast.LENGTH_SHORT).show();
        }

    }
}
