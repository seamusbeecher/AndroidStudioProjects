/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * ContactsFragment.java
 * */


package com.example.inclass08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

   ListView listView;
   ArrayList<Contact> contacts = new ArrayList<>();
   ContactsAdapter adapter;
   Button newContactButton;

    public ContactsFragment() {
        // Required empty public constructor
    }

    IContactsFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (IContactsFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString() + " should implement ISelectAvatarFragment");
        }
    }

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        contacts.clear();
        contacts.addAll(mListener.getCurrentContacts());

        listView = view.findViewById(R.id.listView);
        adapter = new ContactsAdapter(getActivity(), R.layout.contact_row_item, contacts);
        listView.setAdapter(adapter);

        newContactButton = view.findViewById(R.id.newContactButton);
        newContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Change to new fragment
                mListener.showNewContactFragment();
            }
        });


        return view;
    }

    class ContactsAdapter extends ArrayAdapter<Contact> {


        public ContactsAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.contact_row_item, parent, false);
            }

            Contact contact = getItem(position);

            ((TextView)convertView.findViewById(R.id.textViewName)).setText(contact.name);
            ((TextView)convertView.findViewById(R.id.textViewEmail)).setText(contact.email);
            ((TextView)convertView.findViewById(R.id.textViewPhone)).setText(contact.phone);
            ((TextView)convertView.findViewById(R.id.textViewDept)).setText(contact.dept);
            ((ImageView)convertView.findViewById(R.id.imageView)).setImageDrawable(contact.image);

            return convertView;
        }
    }

    interface IContactsFragment {
        ArrayList<Contact> getCurrentContacts();
        void showNewContactFragment();
    }

}