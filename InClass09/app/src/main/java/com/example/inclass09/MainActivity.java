/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * MainActivity.java
 */

package com.example.inclass09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "demo";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mGetReference = mDatabase.getReference();

    Button newContactButton, logoutButton;
    ListView listView;
    ArrayList<Contact> contacts = new ArrayList<>();
    ArrayAdapter<Contact> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        mAuth = FirebaseAuth.getInstance();

        newContactButton = findViewById(R.id.newContactButton);
        logoutButton = findViewById(R.id.logoutButton);

        newContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mGetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Object contact = snapshot.getValue();
                Log.d(TAG, contact.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                //Could not find info

            }
        });

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (mAuth != null) {
//
//            for (UserInfo profile: user.getProviderData()) {
//                String uID = profile.getUid();
//
//            }
//        }

    }
}