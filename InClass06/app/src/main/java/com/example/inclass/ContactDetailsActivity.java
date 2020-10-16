package com.example.inclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class ContactDetailsActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    public static final String TAG = "demo";
    public static final String EDIT_CONTACT = "edit contact";
    public final int REQ_CODE = 100;

    TextView detailsName, detailsNumber, detailsEmail, detailsGroup;
    Button updateButton, deleteButtonDetails, backButton;
    Contact displayContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        setTitle("Contact Details");

        detailsName = (TextView) findViewById(R.id.detailsName);
        detailsNumber = (TextView) findViewById(R.id.detailsNumber);
        detailsEmail = (TextView) findViewById(R.id.detailsEmail);
        detailsGroup = (TextView) findViewById(R.id.detailsGroup);

        Intent intentData = getIntent();

        if (intentData.getSerializableExtra(MainActivity.CONTACT) != null) {

            displayContact = (Contact) intentData.getSerializableExtra(MainActivity.CONTACT);
            detailsName.setText(displayContact.getName());
            detailsNumber.setText(displayContact.getPhone());
            detailsEmail.setText(displayContact.getEmail());
            detailsGroup.setText(displayContact.getType());

        }

        updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetailsActivity.this, EditContactActivity.class);
                intent.putExtra(EDIT_CONTACT, displayContact);
                //startActivity(intent);
                startActivityForResult(intent, REQ_CODE);
            }
        });

        deleteButtonDetails = (Button) findViewById(R.id.deleteButton);
        deleteButtonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(displayContact);
                //Call getContacts again to refresh the list
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void deleteContact(Contact contact) {
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(contact.getId()))
                .build();

        Request request  = new Request.Builder()
                .url("http://ec2-18-234-222-229.compute-1.amazonaws.com/contact/delete")
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE || resultCode == RESULT_OK) {

            Contact contact = (Contact) data.getSerializableExtra(EditContactActivity.UPDATE_CONTACT);

            detailsName.setText(contact.getName());
            detailsNumber.setText(contact.getPhone());
            detailsEmail.setText(contact.getEmail());
            detailsGroup.setText(contact.getType());

        }

    }

}