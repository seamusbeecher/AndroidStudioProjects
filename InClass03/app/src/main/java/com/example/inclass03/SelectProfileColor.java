package com.example.inclass03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectProfileColor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_profile_color);

        findViewById(R.id.greenView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.greenColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

        findViewById(R.id.yellowView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.yellowColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

        findViewById(R.id.redView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.redColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

        findViewById(R.id.blueView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.blueColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

        findViewById(R.id.orangeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.orangeColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

        findViewById(R.id.grayView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Intent color = intent.putExtra("color", R.color.grayColor);
                setResult(RESULT_OK, color);
                finish();
            }
        });

    }
}
