/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * SelectAvatarFragment.java
 * */

package com.example.inclass08;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectAvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectAvatarFragment extends Fragment {

    public static final String TAG = "demo";
    public static final String BUNDLE = "bundle";//Tried sending data from this fragment to newcontactfragment with bundle, couldn't get to work
    ISelectAvatarFragment mListener;
    ImageView female_1, female_2, female_3, male_1, male_2, male_3;


    public SelectAvatarFragment() {
        // Required empty public constructor
    }

    public static SelectAvatarFragment newInstance() {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
        return fragment;
    }

    //mListener was here!!!

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (ISelectAvatarFragment) context;
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
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);

        female_1 = view.findViewById(R.id.imageViewFemale1);
        female_2 = view.findViewById(R.id.imageViewFemale2);
        female_3 = view.findViewById(R.id.imageViewFemale3);
        male_1 = view.findViewById(R.id.imageViewMale1);
        male_2 = view.findViewById(R.id.imageViewMale2);
        male_3 = view.findViewById(R.id.imageViewMale3);

        female_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable female_1_IMG = getResources().getDrawable(R.drawable.avatar_f_1);
                mListener.avatarChoosen(female_1_IMG);
                Toast.makeText(getContext(), "Female Image 1 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();

            }
        });

        female_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable female_2_IMG = getResources().getDrawable(R.drawable.avatar_f_2);
                mListener.avatarChoosen(female_2_IMG);
                Toast.makeText(getContext(), "Female Image 2 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();

            }
        });

        female_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable female_3_IMG = getResources().getDrawable(R.drawable.avatar_f_3);
                mListener.avatarChoosen(female_3_IMG);
                Toast.makeText(getContext(), "Female Image 3 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();
            }
        });

        male_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable male_1_IMG = getResources().getDrawable(R.drawable.avatar_m_1);
                mListener.avatarChoosen(male_1_IMG);
                Toast.makeText(getContext(), "Male Image 1 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();
            }
        });

        male_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable male_2_IMG = getResources().getDrawable(R.drawable.avatar_m_2);
                mListener.avatarChoosen(male_2_IMG);
                Toast.makeText(getContext(), "Male Image 3 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();
            }
        });

        male_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Drawable male_3_IMG = getResources().getDrawable(R.drawable.avatar_m_3);
                mListener.avatarChoosen(male_3_IMG);
                Toast.makeText(getContext(), "Male Image 3 Chosen, Now Press Back Button", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public interface ISelectAvatarFragment {
        void avatarChoosen(Drawable img);
    }

}