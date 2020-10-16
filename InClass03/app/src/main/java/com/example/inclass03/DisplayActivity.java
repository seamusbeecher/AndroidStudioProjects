package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView nameResultProfile, emailResultProfile, depResultProfile;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        setTitle("Display Activity");

        if (getIntent() != null && getIntent().getExtras() != null) {
            Profile profile = (Profile) getIntent().getExtras().getSerializable(MainActivity.PROFILE_KEY);

            nameResultProfile = (TextView) findViewById(R.id.nameResultProfile);
            emailResultProfile = (TextView) findViewById(R.id.emailResultProfile);
            depResultProfile = (TextView) findViewById(R.id.depResultProfile);
            v = (View) findViewById(R.id.layout);

            nameResultProfile.setText(profile.name);
            emailResultProfile.setText(profile.email);
            depResultProfile.setText(profile.dept);
            v.setBackgroundColor(profile.color);

        }
    }
}
