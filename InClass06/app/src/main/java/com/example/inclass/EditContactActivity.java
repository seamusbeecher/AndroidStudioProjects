package com.example.inclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditContactActivity extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    public static final String TAG = "demo";
    public static final String UPDATE_CONTACT = "update contact";

    TextView textViewNameEdit, textViewEmailEdit, textViewPhoneEdit, textViewGroupEdit;
    EditText editNameInput, editEmailInput, editPhoneInput, editGroupInput;
    Button updateButtonEdit, cancelButtonEdit;
    Contact editContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        setTitle("Edit Contact");

        textViewNameEdit = (TextView) findViewById(R.id.textViewNameEdit);
        textViewEmailEdit = (TextView) findViewById(R.id.textViewEmailEdit);
        textViewPhoneEdit = (TextView) findViewById(R.id.textViewPhoneEdit);
        textViewGroupEdit = (TextView) findViewById(R.id.textViewGroupEdit);

        editNameInput = (EditText)findViewById(R.id.editNameInput);
        editEmailInput = (EditText)findViewById(R.id.editEmailInput);
        editPhoneInput = (EditText)findViewById(R.id.editPhoneInput);
        editGroupInput = (EditText)findViewById(R.id.editGroupInput);

        updateButtonEdit = (Button) findViewById(R.id.updateButtonEdit);
        cancelButtonEdit = (Button) findViewById(R.id.cancelButtonEdit);


        setTitle("Edit Contact");

        Intent intentEdit = getIntent();

        if (intentEdit.getSerializableExtra(ContactDetailsActivity.EDIT_CONTACT) != null) {

            editContact = (Contact) intentEdit.getSerializableExtra(ContactDetailsActivity.EDIT_CONTACT);

            textViewNameEdit.setText(editContact.getName());
            textViewEmailEdit.setText(editContact.getEmail());
            textViewPhoneEdit.setText(editContact.getPhone());
            textViewGroupEdit.setText(editContact.getType());

        }

        updateButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contact updatedContact = new Contact(editContact.getId(), editNameInput.getText().toString(), editEmailInput.getText().toString(), editPhoneInput.getText().toString(), editGroupInput.getText().toString());
                updateContact(updatedContact);

                Intent intent = new Intent();
                Intent contact = intent.putExtra(UPDATE_CONTACT, updatedContact);
                setResult(RESULT_OK, contact);
                finish();

            }
        });


        cancelButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////Call getContacts again to refresh the list
                //Intent intentCancel = new Intent(EditContactActivity.this, MainActivity.class);
                //startActivity(intentCancel);
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void updateContact(Contact contact) {

        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(contact.getId()))
                .add("name", contact.getName())
                .add("email", contact.getEmail())
                .add("phone", contact.getPhone())
                .add("type", contact.getType())
                .build();

        Request request  = new Request.Builder()
                .url("http://ec2-18-234-222-229.compute-1.amazonaws.com/contact/update")
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