package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText editTextName, editTextEmail;
    SeekBar seekBar;
    Button buttonSubmit;
    String mood;

    public final int REQ_CODE = 100;
    static String STUDENT_KEY = "STUDENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        radioGroup = findViewById(R.id.radioGroup);

        try {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    final RadioButton radioButton = (RadioButton) findViewById(checkedId);
                    final String radioButtonText = radioButton.getText().toString();

                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {

                            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (progress > 50) {
                                        mood = "Positive";
                                    } else {
                                        mood = "Negative";
                                    }

                                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                                    Student student = new Student(editTextName.getText().toString(),
                                            editTextEmail.getText().toString(),
                                            radioButtonText,
                                            mood, progress);
                                    intent.putExtra(STUDENT_KEY, student);
                                    startActivity(intent);
                                }
                            });
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            //Did not use
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            //Did not use
                        }
                    });
                }
            });

        //Do not catch missing inputs
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
