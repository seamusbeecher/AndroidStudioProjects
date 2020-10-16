package com.example.hw01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    SeekBar seekBar;
    EditText userInputBillTotal;
    TextView outputTipValue, outputTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String error = "Enter Bill Total";

        userInputBillTotal = (EditText) findViewById(R.id.userInputBillTotal);
        outputTipValue = (TextView) findViewById(R.id.outputTipValue);
        outputTotalValue = (TextView) findViewById(R.id.outputTotalValue);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = (RadioButton) findViewById(checkedId);

                if (Double.parseDouble(userInputBillTotal.getText().toString()) > 0.00) {
                    if (radioGroup.getCheckedRadioButtonId() == R.id.tenPercentButton) {

                        String userInputString = userInputBillTotal.getText().toString();
                        double inputNumber = Double.parseDouble(userInputString);
                        double newTipValue = inputNumber * .10;
                        double newTotalValue = newTipValue + inputNumber;

                        outputTipValue.setText(Double.toString(newTipValue));
                        outputTotalValue.setText(Double.toString(newTotalValue));

                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.fifteenPercentButton) {

                        String userInputString = userInputBillTotal.getText().toString();
                        double inputNumber = Double.parseDouble(userInputString);
                        double newTipValue = inputNumber * .15;
                        double newTotalValue = newTipValue + inputNumber;

                        outputTipValue.setText(Double.toString(newTipValue));
                        outputTotalValue.setText(Double.toString(newTotalValue));

                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.eighteenPercentButton) {

                        String userInputString = userInputBillTotal.getText().toString();
                        double inputNumber = Double.parseDouble(userInputString);
                        double newTipValue = inputNumber * .18;
                        double newTotalValue = newTipValue + inputNumber;
                        outputTipValue.setText(Double.toString(newTipValue));
                        outputTotalValue.setText(Double.toString(newTotalValue));

                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.customButton) {

                        seekBar = (SeekBar) findViewById(R.id.seekBar);
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                                TextView customTipProgress = (TextView) findViewById(R.id.customTipProgressText);
                                customTipProgress.setText(String.valueOf(progress) + "%");

                                double value = Double.valueOf(progress) / 100.00;
                                String userInputString = userInputBillTotal.getText().toString();
                                double inputNumber = Double.parseDouble(userInputString);
                                double newTipValue = inputNumber * value;
                                double newTotalValue = newTipValue + inputNumber;
                                outputTipValue.setText(Double.toString(newTipValue));
                                outputTotalValue.setText(Double.toString(newTotalValue));

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
                } else {
                    userInputBillTotal.setError(error);
                }

            }
        });

        findViewById(R.id.exitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }
}
