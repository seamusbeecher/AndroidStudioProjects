package com.example.inclass03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final int REQ_CODE = 100;
    static String PROFILE_KEY = "DEPARTMENT";

    TextView prifileColorView;
    EditText nameInput, emailInput;
    RadioGroup radioGroup;
    ColorDrawable backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prifileColorView = (TextView) findViewById(R.id.prifileColorView);
        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        backgroundColor = (ColorDrawable) prifileColorView.getBackground();

        findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectProfileColor.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final RadioButton radioButton = (RadioButton) findViewById(checkedId);

                findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        final int colorID = backgroundColor.getColor();
                        Profile profile = new Profile(nameInput.getText().toString(), emailInput.getText().toString(), radioButton.getText().toString(), colorID);
                        intent.putExtra(PROFILE_KEY, profile);
                        startActivity(intent);
                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                int color = data.getExtras().getInt("color");
                prifileColorView.setBackgroundColor(getResources().getColor(color));
            }
        }

    }
}
