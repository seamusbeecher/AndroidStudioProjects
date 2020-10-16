package com.example.hw02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {

    public static int REQ_CODE = 100;
    public static boolean NAME_CHECKED = false;
    public static String NAME_KEY = "NAME";
    public static boolean EMAIL_CHECKED = false;
    public static String EMAIL_KEY = "EMAIL";
    public static boolean DEPT_CHECKED = false;
    public static String DEPT_KEY = "DEPT";
    public static boolean MOOD_CHECKED = false;
    public static String MOOD_KEY = "EMAIL";

    TextView outputName, outputEmail, outputDept, outputMood;
    ImageButton editButtonName, editButtonEmail, editButtonDept, editButtonMood;
    String mood;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {

                if (data.getExtras().getBoolean("NAME_TRUE")) {

                    String name = data.getExtras().getString("NAME");
                    outputName = (TextView) findViewById(R.id.outputName);
                    outputName.setText(name);

                }

                if (data.getExtras().getBoolean("EMAIL_TRUE")) {

                    String email = data.getExtras().getString("EMAIL");
                    outputEmail = (TextView) findViewById(R.id.outputEmail);
                    outputEmail.setText(email);

                }

                if (data.getExtras().getBoolean("DEPT_TRUE")) {

                    String dept = data.getExtras().getString("DEPT");
                    outputDept = (TextView) findViewById(R.id.outputDept);
                    outputDept.setText(dept);

                }

                if (data.getExtras().getBoolean("MOOD_TRUE")) {

                    int progress = data.getExtras().getInt("MOOD");

                    if (progress > 50) {
                        mood = "Positive";
                    } else {
                        mood = "Negative";
                    }

                    outputMood = (TextView) findViewById(R.id.outputMood);
                    outputMood.setText(progress + " % " + mood);

                }

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        outputName = (TextView) findViewById(R.id.outputName);
        outputEmail = (TextView) findViewById(R.id.outputEmail);
        outputDept = (TextView) findViewById(R.id.outputDept);
        outputMood = (TextView) findViewById(R.id.outputMood);

        editButtonName = (ImageButton) findViewById(R.id.editButtonName);
        editButtonEmail = (ImageButton) findViewById(R.id.editButtonEmail);
        editButtonDept = (ImageButton) findViewById(R.id.editButtonDept);
        editButtonMood = (ImageButton) findViewById(R.id.editButtonMood);

        setTitle("Display Activity");

        if (getIntent() != null && getIntent().getExtras() != null) {

            Student student = (Student) getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY);

            outputName.setText(student.name);
            outputEmail.setText(student.email);
            outputDept.setText(student.department);
            outputMood.setText(student.accountState + "% " + student.mood);

        }

        editButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                NAME_CHECKED = true;
                intent.putExtra(NAME_KEY, outputName.getText());
                intent.putExtra("NAME", NAME_CHECKED);
                startActivityForResult(intent, REQ_CODE);

            }
        });

        editButtonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                EMAIL_CHECKED = true;
                intent.putExtra(EMAIL_KEY, outputEmail.getText());
                intent.putExtra("EMAIL", EMAIL_CHECKED);
                startActivityForResult(intent, REQ_CODE);

            }
        });

        editButtonDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                DEPT_CHECKED = true;
                intent.putExtra(DEPT_KEY, outputDept.getText());
                intent.putExtra("DEPT", DEPT_CHECKED);
                startActivityForResult(intent, REQ_CODE);

            }
        });

        editButtonMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                MOOD_CHECKED = true;
                intent.putExtra(MOOD_KEY, outputMood.getText());
                intent.putExtra("MOOD", MOOD_CHECKED);
                startActivityForResult(intent, REQ_CODE);

            }
        });
    }
}
