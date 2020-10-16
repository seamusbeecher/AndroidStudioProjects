/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * SelectAvatarActivity.java
 */

package com.example.inclass09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatarActivity extends AppCompatActivity {

    ImageView female1, female2, female3, male1, male2, male3;
    static final String AVATAR = "avatar";
    static final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);

        female1 = findViewById(R.id.female1);
        female2 = findViewById(R.id.female2);
        female3 = findViewById(R.id.female3);
        male1 = findViewById(R.id.male1);
        male2 = findViewById(R.id.male2);
        male3 = findViewById(R.id.male3);

        female1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_f_1;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        female2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_f_2;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        female3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_f_3;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        male1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_m_1;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        male2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_m_2;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        male3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1img = R.drawable.avatar_m_3;
                Intent intent = new Intent();
                intent.putExtra(AVATAR, f1img);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}