/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * NewContactActivity.java
 */

package com.example.inclass09;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewContactActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    static final String TAG = "demo";
    static final String NEW_CONTACT = "new contact";
    static final int REQ_CODE = 100;

    int img;
    Contact newContact;

    ImageView imageView;
    Button buttonSubmit;
    EditText newContactName, newContactEmail, newContactPhone;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        imageView = findViewById(R.id.imageView);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        newContactName = findViewById(R.id.newContactName);
        newContactEmail = findViewById(R.id.newContactEmail);
        newContactPhone = findViewById(R.id.newContactPhone);
        radioGroup = findViewById(R.id.radioGroup);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewContactActivity.this, SelectAvatarActivity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final RadioButton radioButton = findViewById(checkedId);

                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        newContact = new Contact(
                                newContactName.getText().toString(),
                                newContactEmail.getText().toString(),
                                newContactPhone.getText().toString(),
                                radioButton.getText().toString(),
                                img
                        );

                        Log.d(TAG, newContact.toString());
                        myRef.child("users").child(mAuth.getCurrentUser().getUid()).push().setValue(newContact).addOnCompleteListener(NewContactActivity.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    finish();

                                } else {

                                    Toast.makeText(NewContactActivity.this, "Data did not send to firebase", Toast.LENGTH_LONG).show();

                                }

                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            if (resultCode == RESULT_OK) {

                img = data.getExtras().getInt(SelectAvatarActivity.AVATAR);
                Log.d(TAG, "onActivityResult" + img);
                ImageView imageView = (ImageView)findViewById(R.id.imageView);
                imageView.setImageResource(img);

            }
        }

    }
}