/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * SignUpActivity.java
 */

package com.example.inclass09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText editTextTextFirstName, editTextTextLastName, editTextTextEmailAddress2, editTextTextPassword2, editTextTextPasswordConfirm;
    Button signUpButton, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        editTextTextFirstName = findViewById(R.id.editTextTextFirstName);
        editTextTextLastName = findViewById(R.id.editTextTextLastName);
        editTextTextEmailAddress2 = findViewById(R.id.editTextTextEmailAddress2);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextPasswordConfirm = findViewById(R.id.editTextTextPasswordConfirm);

        signUpButton = findViewById(R.id.signUpButton);
        buttonCancel = findViewById(R.id.buttonCancel);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = editTextTextFirstName.getText().toString();
                String lastName = editTextTextLastName.getText().toString();
                String email = editTextTextEmailAddress2.getText().toString();
                String password = editTextTextPassword2.getText().toString();
                String confirmPassword = editTextTextPasswordConfirm.getText().toString();

                if (firstName.isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "First name empty", Toast.LENGTH_LONG).show();

                } else if (lastName.isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "Last name empty", Toast.LENGTH_LONG).show();

                } else if (email.isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "Email name empty", Toast.LENGTH_LONG).show();

                } else if (password.isEmpty()) {

                    Toast.makeText(SignUpActivity.this, "Password name empty", Toast.LENGTH_LONG).show();

                } else if (password != confirmPassword) {

                    Toast.makeText(SignUpActivity.this, "Password not the same", Toast.LENGTH_LONG).show();

                } else {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //successful
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                //not successful
                            }

                        }
                    });

                }

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}