package com.example.inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NewContactActivity extends AppCompatActivity {

    public static final String TAG = "demo";
    private final OkHttpClient client = new OkHttpClient();
    public static final String NEW_CONTACT = "new contact";

    EditText nameInput, phoneInput, emailInput, groupInput;
    Button submitButton, cancelButtonNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        setTitle("New Contact");

        nameInput = (EditText) findViewById(R.id.nameInput);
        phoneInput = (EditText) findViewById(R.id.phonesInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        groupInput = (EditText) findViewById(R.id.groupInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        cancelButtonNew = (Button) findViewById(R.id.cancelButtonNew);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact contact = new Contact(0, nameInput.getText().toString(), emailInput.getText().toString(), phoneInput.getText().toString(), groupInput.getText().toString());

                createContacts(contact);

                Intent intent = new Intent();
                Intent contactIntent = intent.putExtra(NEW_CONTACT, contact);
                setResult(RESULT_OK, contactIntent);

                finish();
            }
        });

        cancelButtonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ////Call getContacts again to refresh the list
                //setResult(RESULT_OK);
                //finish();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    private void createContacts(final Contact contact) {

        RequestBody formBody = new FormBody.Builder()
                .add("name", contact.getName())
                .add("email", contact.getEmail())
                .add("phone", contact.getPhone())
                .add("type", contact.getType())
                .build();

        Request request  = new Request.Builder()
                .url("http://ec2-18-234-222-229.compute-1.amazonaws.com/contact/create")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                e.printStackTrace();
                Log.d(TAG, "onFailure: " + e.getMessage());

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                ResponseBody responseBody = response.body();
                String body = responseBody.string();
                Log.d(TAG, "onResponse: " + body);
                Log.d(TAG, "Success: " + response);

            }
        });

    }

}