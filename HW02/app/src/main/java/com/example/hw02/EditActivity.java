package com.example.hw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    public static String NAME_UPDATE = "updatedName";
    public static EditText editTextName, editTextEmail;
    RadioGroup radioGroup2;
    RadioButton radioButtonSIS, radioButtonCS, radioButtonBIO, radioButtonOthers;
    TextView textViewDept, textViewCurrentMood;
    SeekBar seekBarEdit;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setTitle("Edit Activity");

        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioButtonSIS = (RadioButton) findViewById(R.id.radioButtonSIS);
        radioButtonCS = (RadioButton) findViewById(R.id.radioButtonCS);
        radioButtonBIO = (RadioButton) findViewById(R.id.radioButtonBIO);
        radioButtonOthers = (RadioButton) findViewById(R.id.radioButtonOthers);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        textViewDept = (TextView) findViewById(R.id.textViewDept);
        textViewCurrentMood = (TextView) findViewById(R.id.textViewCurrentMood);

        seekBarEdit = (SeekBar) findViewById(R.id.seekBarEdit);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        if (getIntent() != null && getIntent().getExtras() != null) {

            if (getIntent().getExtras().getBoolean("NAME")) {

                String oldName = getIntent().getExtras().getString(DisplayActivity.NAME_KEY);
                editTextName.setText(oldName);

                editTextEmail.setVisibility(View.INVISIBLE);
                seekBarEdit.setVisibility(View.INVISIBLE);
                radioButtonSIS.setVisibility(View.INVISIBLE);
                radioButtonCS.setVisibility(View.INVISIBLE);
                radioButtonBIO.setVisibility(View.INVISIBLE);
                radioButtonOthers.setVisibility(View.INVISIBLE);
                textViewDept.setVisibility(View.INVISIBLE);
                textViewCurrentMood.setVisibility(View.INVISIBLE);

                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra("NAME", editTextName.getText().toString());
                        intent.putExtra("NAME_TRUE", DisplayActivity.NAME_CHECKED);
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                });

            }

            if (getIntent().getExtras().getBoolean("EMAIL")) {

                String oldEmail = getIntent().getExtras().getString(DisplayActivity.EMAIL_KEY);
                editTextEmail.setText(oldEmail);

                radioButtonSIS.setVisibility(View.INVISIBLE);
                radioButtonCS.setVisibility(View.INVISIBLE);
                radioButtonBIO.setVisibility(View.INVISIBLE);
                radioButtonOthers.setVisibility(View.INVISIBLE);
                editTextName.setVisibility(View.INVISIBLE);
                textViewDept.setVisibility(View.INVISIBLE);
                textViewCurrentMood.setVisibility(View.INVISIBLE);
                seekBarEdit.setVisibility(View.INVISIBLE);

                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra("EMAIL", editTextEmail.getText().toString());
                        intent.putExtra("EMAIL_TRUE", DisplayActivity.EMAIL_CHECKED);
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                });

            }

            if (getIntent().getExtras().getBoolean("DEPT")) {

                editTextName.setVisibility(View.INVISIBLE);
                editTextEmail.setVisibility(View.INVISIBLE);
                seekBarEdit.setVisibility(View.INVISIBLE);
                textViewCurrentMood.setVisibility(View.INVISIBLE);

                String oldDept = getIntent().getExtras().getString(DisplayActivity.DEPT_KEY);

                if (findViewById(R.id.radioButtonSIS).equals(oldDept)) {
                    radioButtonSIS.setChecked(true);
                }

                if (findViewById(R.id.radioButtonCS).equals(oldDept)) {
                    radioButtonCS.setChecked(true);
                }

                if (findViewById(R.id.radioButtonBIO).equals(oldDept)) {
                    radioButtonBIO.setChecked(true);
                }

                if (findViewById(R.id.radioButtonOthers).equals(oldDept)) {
                    radioButtonOthers.setChecked(true);
                }

                radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        final RadioButton rb = (RadioButton) findViewById(checkedId);

                        buttonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent();
                                intent.putExtra("DEPT", rb.getText().toString());
                                intent.putExtra("DEPT_TRUE", DisplayActivity.DEPT_CHECKED);
                                setResult(RESULT_OK, intent);
                                finish();

                            }
                        });

                    }
                });

            }

            if (getIntent().getExtras().getBoolean("MOOD")) {

                int oldSB = getIntent().getExtras().getInt(DisplayActivity.MOOD_KEY);
                seekBarEdit.setProgress(oldSB);

                editTextName.setVisibility(View.INVISIBLE);
                editTextEmail.setVisibility(View.INVISIBLE);
                textViewDept.setVisibility(View.INVISIBLE);
                radioButtonSIS.setVisibility(View.INVISIBLE);
                radioButtonCS.setVisibility(View.INVISIBLE);
                radioButtonBIO.setVisibility(View.INVISIBLE);
                radioButtonOthers.setVisibility(View.INVISIBLE);

                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent();
                        intent.putExtra("MOOD", seekBarEdit.getProgress());
                        intent.putExtra("MOOD_TRUE", DisplayActivity.MOOD_CHECKED);
                        setResult(RESULT_OK, intent);
                        finish();

                    }
                });

            }

        }

    }
}
