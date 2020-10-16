//Homework 1
//In Class 2a
//Seamus Beecher & Cam Mclntosh

package com.example.inclass2a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText user = (EditText) findViewById(R.id.userInput);
        final TextView resultsText = (TextView) findViewById(R.id.result);
        final TextView outputText = (TextView) findViewById(R.id.output);

        //Inches Button Listener
        final Button inch = (Button) findViewById(R.id.inchesButton);
        inch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = user.getText().toString();
                double input = Double.parseDouble(userInput);
                final double inchConv = input * 39.3701;
                final String inchText = "Inches";
                final String result = Double.toString(inchConv);

                resultsText.setText(inchText);
                outputText.setText(result);
            }
        });

        //Feet Button Listener
        Button feet = (Button) findViewById(R.id.feetButton);
        feet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = user.getText().toString();
                double input = Double.parseDouble(userInput);
                final double feetConv = input * 3.28;
                final String feetText = "Feet";
                final String result = Double.toString(feetConv);

                resultsText.setText(feetText);
                outputText.setText(result);
            }
        });

        //Feet Button Listener
        Button mile = (Button) findViewById(R.id.milesButton);
        mile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userInput = user.getText().toString();
                double input = Double.parseDouble(userInput);
                final double milesConv = input * 0.0006;
                final String mileText = "Miles";
                final String result = Double.toString(milesConv);

                resultsText.setText(mileText);
                outputText.setText(result);
            }
        });

        //Clear Button Listener
        Button clear = (Button) findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                resultsText.setText("");
                outputText.setText("Result:");
            }
        });
    }
}
