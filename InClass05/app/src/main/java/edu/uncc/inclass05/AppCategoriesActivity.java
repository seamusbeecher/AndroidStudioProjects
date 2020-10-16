/**
 * In Class 05
 * AppCategoriesActivity
 * Seamus Beecher & Cam McIntosh
 */

package edu.uncc.inclass05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.inclass05.utils.Data;

 public class AppCategoriesActivity extends AppCompatActivity {


    public static final String TAG = "demo";
    public static final String FREE_APPS = "FREE APPS";
    public static final String GROSSING_APPS = "GROSSING APPS";
    public static final String PAID_APPS = "PAID APPS";
    public static final String NEW_APPS = "NEW APPS";
    public static final String GAME_APPS = "GAME APPS";

    ListView listView;
    ArrayList<String> categories;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_categories);

        setTitle("App Categories");

        categories = new ArrayList<String>(Data.apps.keySet());
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, categories);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + categories.get(position));

                if (categories.get(position) == "Top Free Apps") {

                    Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                    intent.putExtra(FREE_APPS, "Top Free Apps");
                    startActivity(intent);

                } else if (categories.get(position) == "Top Grossing Apps") {

                    Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                    intent.putExtra(GROSSING_APPS, "Top Grossing Apps");
                    startActivity(intent);

                } else if (categories.get(position) == "Top Paid Apps") {

                    Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                    intent.putExtra(PAID_APPS, "Top Paid Apps");
                    startActivity(intent);

                } else if (categories.get(position) == "New Apps We Love") {

                    Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                    intent.putExtra(NEW_APPS, "New Apps We Love");
                    startActivity(intent);

                } else if (categories.get(position) == "New Games We Love") {

                    Intent intent = new Intent(AppCategoriesActivity.this, AppsListActivity.class);
                    intent.putExtra(GAME_APPS, "New Games We Love");
                    startActivity(intent);

                } else {
                    Toast.makeText(AppCategoriesActivity.this, "No Option Selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        Data.apps.keySet(); //to get the keys which are the app categories.
//        Log.d(TAG, "onCreate: " + Data.apps.get("Top Free Apps"));
    }
}