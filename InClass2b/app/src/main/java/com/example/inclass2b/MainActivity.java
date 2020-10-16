//Homework 1
//In Class 2b
//Seamus Beecher & Cam Mclntosh

package com.example.inclass2b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText user;
    TextView resultsText, outputText;
    String userInput, result;
    double input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.userInput);
        resultsText = (TextView) findViewById(R.id.results);
        outputText = (TextView) findViewById(R.id.output);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final Button convert = (Button) findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioButtonId);

                if (radioButtonId == R.id.inchesButton) {
                    userInput = user.getText().toString();
                    input = Double.parseDouble(userInput);
                    final double inchConv = input * 39.3701;
                    final String inchText = "Inches";
                    result = Double.toString(inchConv);

                    resultsText.setText(inchText);
                    outputText.setText(result);
                } else if (radioButtonId == R.id.feetButton) {
                    userInput = user.getText().toString();
                    input = Double.parseDouble(userInput);
                    final double feetConv = input * 3.28;
                    final String feetText = "Feet";
                    result = Double.toString(feetConv);

                    resultsText.setText(feetText);
                    outputText.setText(result);
                } else if (radioButtonId == R.id.milesButton) {
                    userInput = user.getText().toString();
                    input = Double.parseDouble(userInput);
                    final double mileConv = input * 0.0006;
                    final String mileText = "Miles";
                    result = Double.toString(mileConv);

                    resultsText.setText(mileText);
                    outputText.setText(result);
                } else {
                    user.setText("");
                    resultsText.setText("");
                    outputText.setText("");
                }

            }
        });
    }
}
