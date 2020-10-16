/*
* Seamus Beecber & Cam McIntosh
* HW04
* AppsListActivity
* */

package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import edu.uncc.hw04.utils.App;
import edu.uncc.hw04.utils.Data;

public class AppsListActivity extends AppCompatActivity {

    public static final String APP_POSITION = "APP POSITION";
    public static final String APP_DATA = "APP DATA";
    ListView listView2;
    ArrayList<App> categoriesData;

    EditText appListInput;
    ImageButton appListFilterButton, appListDecButton, appListAscButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        final Intent intent = getIntent();
        listView2 = (ListView) findViewById(R.id.listView2);

        appListInput = (EditText) findViewById(R.id.appListInput);
        appListFilterButton = (ImageButton) findViewById(R.id.appListFilterButton);
        appListDecButton = (ImageButton) findViewById(R.id.appListDecButton);
        appListAscButton = (ImageButton) findViewById(R.id.appListAscButton);

        if (intent.hasExtra(AppCategoriesActivity.FREE_APPS)) {

            setTitle("Top Free Apps");
            final String category = getIntent().getExtras().getString(AppCategoriesActivity.FREE_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            //Log.d("demo", "OnCreate: " + categoriesData);

            //FILTER
            appListFilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = appListInput.getText().toString();
                    ArrayList<App> filterArray = new ArrayList<>();
                    for (int count = 0; count < categoriesData.size(); count++) {
                        if (categoriesData.get(count).getName().toString().startsWith(text)) {
                            filterArray.add(categoriesData.get(count));
                        }
                    }

                    ListAdapter filterAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, filterArray);
                    listView2.setAdapter(filterAdapter);
                    categoriesData = filterArray;
                }
            });

            //ASC
            appListAscButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList);

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> acsList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                acsList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter ascAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, acsList);
                    listView2.setAdapter(ascAdapter);
                    categoriesData = acsList;
                }
            });

            //DEC
            appListDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList, Collections.<Date>reverseOrder());

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> decList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                decList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter decAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, decList);
                    listView2.setAdapter(decAdapter);
                    categoriesData = decList;
                }
            });

        } else if (intent.hasExtra(AppCategoriesActivity.GROSSING_APPS)) {

            setTitle("Top Grossing Apps");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.GROSSING_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            //Log.d("demo", "OnCreate: " + categoriesData);

            //FILTER
            appListFilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = appListInput.getText().toString();
                    ArrayList<App> filterArray = new ArrayList<>();
                    for (int count = 0; count < categoriesData.size(); count++) {
                        if (categoriesData.get(count).getName().toString().startsWith(text)) {
                            filterArray.add(categoriesData.get(count));
                        }
                    }

                    ListAdapter filterAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, filterArray);
                    listView2.setAdapter(filterAdapter);
                    categoriesData = filterArray;
                }
            });

            //ASC
            appListAscButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList);

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> acsList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                acsList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter ascAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, acsList);
                    listView2.setAdapter(ascAdapter);
                    categoriesData = acsList;
                }
            });

            //DEC
            appListDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList, Collections.<Date>reverseOrder());

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> decList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                decList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter decAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, decList);
                    listView2.setAdapter(decAdapter);
                    categoriesData = decList;
                }
            });

        } else if (intent.hasExtra(AppCategoriesActivity.PAID_APPS)) {

            setTitle("Top Paid Apps");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.PAID_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            //Log.d("demo", "OnCreate: " + categoriesData);

            //FILTER
            appListFilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = appListInput.getText().toString();
                    ArrayList<App> filterArray = new ArrayList<>();
                    for (int count = 0; count < categoriesData.size(); count++) {
                        if (categoriesData.get(count).getName().toString().startsWith(text)) {
                            filterArray.add(categoriesData.get(count));
                        }
                    }

                    ListAdapter filterAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, filterArray);
                    listView2.setAdapter(filterAdapter);
                    categoriesData = filterArray;
                }
            });

            //ASC
            appListAscButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList);

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> acsList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                acsList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter ascAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, acsList);
                    listView2.setAdapter(ascAdapter);
                    categoriesData = acsList;
                }
            });

            //DEC
            appListDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList, Collections.<Date>reverseOrder());

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> decList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                decList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter decAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, decList);
                    listView2.setAdapter(decAdapter);
                    categoriesData = decList;
                }
            });

        } else if (intent.hasExtra(AppCategoriesActivity.NEW_APPS)) {

            setTitle("New Apps We Love");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.NEW_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            //Log.d("demo", "OnCreate: " + categoriesData);

            //FILTER
            appListFilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = appListInput.getText().toString();
                    ArrayList<App> filterArray = new ArrayList<>();
                    for (int count = 0; count < categoriesData.size(); count++) {
                        if (categoriesData.get(count).getName().toString().startsWith(text)) {
                            filterArray.add(categoriesData.get(count));
                        }
                    }

                    ListAdapter filterAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, filterArray);
                    listView2.setAdapter(filterAdapter);
                    categoriesData = filterArray;
                }
            });

            //ASC
            appListAscButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList);

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> acsList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                acsList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter ascAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, acsList);
                    listView2.setAdapter(ascAdapter);
                    categoriesData = acsList;
                }
            });

            //DEC
            appListDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList, Collections.<Date>reverseOrder());

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> decList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                decList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter decAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, decList);
                    listView2.setAdapter(decAdapter);
                    categoriesData = decList;
                }
            });

        } else if (intent.hasExtra(AppCategoriesActivity.GAME_APPS)) {

            setTitle("New Games We Love");
            String category = getIntent().getExtras().getString(AppCategoriesActivity.GAME_APPS);
            categoriesData = Data.apps.get(category);
            ListAdapter listAdapter = new ListAdapter(this, R.layout.app_list, categoriesData);
            listView2.setAdapter(listAdapter);
            //Log.d("demo", "OnCreate: " + categoriesData);

            //FILTER
            appListFilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = appListInput.getText().toString();
                    ArrayList<App> filterArray = new ArrayList<>();
                    for (int count = 0; count < categoriesData.size(); count++) {
                        if (categoriesData.get(count).getName().toString().startsWith(text)) {
                            filterArray.add(categoriesData.get(count));
                        }
                    }

                    ListAdapter filterAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, filterArray);
                    listView2.setAdapter(filterAdapter);
                    categoriesData = filterArray;
                }
            });

            //ASC
            appListAscButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList);

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> acsList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                acsList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter ascAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, acsList);
                    listView2.setAdapter(ascAdapter);
                    categoriesData = acsList;
                }
            });

            //DEC
            appListDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<Date> dateList = new ArrayList<>();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    for (int count = 0; count < categoriesData.size(); count++) {
                        String relDate = categoriesData.get(count).getReleaseDate();
                        Date date = null;
                        try {
                            date = format.parse(relDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dateList.add(date);
                    }

                    Collections.sort(dateList, Collections.<Date>reverseOrder());

                    ArrayList<String> stringArrayList = new ArrayList<>();
                    for (int z = 0; z < dateList.size(); z++) {
                        String dateStr = dateToString(dateList.get(z));
                        stringArrayList.add(dateStr);
                    }

                    ArrayList<App> decList = new ArrayList<>();
                    for (int x = 0; x < stringArrayList.size(); x++) {
                        for (int y = 0; y < categoriesData.size(); y++) {
                            if (stringArrayList.get(x).equals(categoriesData.get(y).getReleaseDate())) {
                                decList.add(categoriesData.get(y));
                            }
                        }
                    }

                    ListAdapter decAdapter = new edu.uncc.hw04.ListAdapter(getApplicationContext(), R.layout.app_list, decList);
                    listView2.setAdapter(decAdapter);
                    categoriesData = decList;
                }
            });

        } else {
            Toast.makeText(this, "Nothing was sent to intent", Toast.LENGTH_SHORT).show();
        }

        //ListView
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Log.d(AppCategoriesActivity.TAG, "onItemClick: " + categoriesData.get(position));

                Intent intent = new Intent(AppsListActivity.this, AppDetailsActivity.class);
                intent.putExtra(APP_POSITION, position);
                intent.putExtra(APP_DATA, categoriesData.get(position));
                //Log.d("demo",  categoriesData.get(position).toString());
                //Log.d("demo", String.valueOf(position));
                startActivity(intent);

            }
        });

    }

    public static String dateToString(Date date) {
        String convertedDate = "";
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            convertedDate = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

}
