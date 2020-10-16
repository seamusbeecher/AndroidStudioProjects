/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * ContactAdapter.java
 */

package com.example.inclass09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact contact = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_adapter, parent, false);

        ImageView adapterImageView = (ImageView) convertView.findViewById(R.id.adapterImageView);
        TextView adapterName = (TextView) convertView.findViewById(R.id.adapterName);
        TextView adapterEmail = (TextView) convertView.findViewById(R.id.adapterEmail);
        TextView adapterPhone = (TextView) convertView.findViewById(R.id.adapterPhone);
        TextView adapterDept = (TextView) convertView.findViewById(R.id.adapterDept);

        adapterName.setText(contact.name);
        adapterEmail.setText(contact.email);
        adapterPhone.setText(contact.phone);
        adapterDept.setText(contact.type);
        adapterImageView.setImageResource(contact.image);

        return convertView;
    }
}
