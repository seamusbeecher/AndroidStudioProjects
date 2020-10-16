/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * NewContactFragment.java
 * */


package com.example.inclass08;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewContactFragment extends Fragment {

    public NewContactFragment() {
        // Required empty public constructor
    }

    public static NewContactFragment newInstance() {
        NewContactFragment fragment = new NewContactFragment();
        return fragment;
    }

    ICreateContactFragment mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (ICreateContactFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString() + " should implement ISelectAvatarFragment");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_new_contact, container, false);

        view.findViewById(R.id.imageViewNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showAvatarsFragment();
            }
        });

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final RadioButton radioButton = view.findViewById(checkedId);

                view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TextView newContactName = view.findViewById(R.id.newContactName);
                        TextView newContactEmail = view.findViewById(R.id.newContactEmail);
                        TextView newContactPhone = view.findViewById(R.id.newContactPhone);

                        Contact newContact = new Contact(
                                newContactName.getText().toString(),
                                newContactEmail.getText().toString(),
                                newContactPhone.getText().toString(),
                                radioButton.getText().toString(),
                                getResources().getDrawable(R.drawable.avatar_f_1) //Only able to show hard coded image, not image from SelectAvatarFragment

                        );
                        mListener.newContactMade(newContact);

                        Toast.makeText(getContext(), "Submit Button Pressed, Now Press Back Button", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        return view;
    }

    interface ICreateContactFragment {
        void showAvatarsFragment();
        void newContactMade(Contact contact);
    }

}