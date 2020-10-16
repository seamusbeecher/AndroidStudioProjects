package com.example.inclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "demo";
    public static final String CONTACT = "contact";
    private final OkHttpClient client = new OkHttpClient();
    public final int REQ_CODE = 200;
    public final int QER_CODE = 100;

    ListView listView;
    ArrayList<Contact> contactArrayList = new ArrayList<>();
    ArrayList<String> contactNames = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Contact List");

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, contactNames);
        listView.setAdapter(arrayAdapter);

        getContacts();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(TAG, contactNames.get(position));
                //Log.d(TAG, String.valueOf(contactArrayList.get(position)));
                Intent intent = new Intent(MainActivity.this, ContactDetailsActivity.class);
                intent.putExtra(CONTACT, contactArrayList.get(position));
                //startActivity(intent);
                startActivityForResult(intent, QER_CODE);
            }
        });

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick(Add):");
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, REQ_CODE);
            }
        });

    }

    private void getContacts() {

        Request request = new Request.Builder()
                .url("http://ec2-18-234-222-229.compute-1.amazonaws.com/contacts")
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                e.printStackTrace();
                Log.d(TAG, "onFailure: " + e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                ResponseBody responseBody = response.body();

                if (response.isSuccessful()) {

                    String body = responseBody.string();
                    Log.d(TAG, "onResponse: " + body);

                    contactArrayList.clear();
                    contactNames.clear();
                    String[] contactsLines = body.split("\n");
                    for (String line: contactsLines) {
                        Contact contact = new Contact(line);
                        contactNames.add(contact.getName());
                        contactArrayList.add(contact);
                    }

                    //Log.d(TAG, "onResponse: " + contactArrayList);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            arrayAdapter.notifyDataSetChanged();
                        }
                    });

                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {

            Contact newContact = (Contact) data.getSerializableExtra(NewContactActivity.NEW_CONTACT);
            contactArrayList.add(newContact);
            getContacts();

        }

        if (requestCode == QER_CODE && resultCode == RESULT_OK) {

            getContacts();

        }

    }

}