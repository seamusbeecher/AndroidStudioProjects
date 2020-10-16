/**
 * Seamus Beecher & Cam McIntosh
 * HW05
 * MainActivity.java
 * */

package com.example.inclass08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactsFragment.IContactsFragment, NewContactFragment.ICreateContactFragment, SelectAvatarFragment.ISelectAvatarFragment{

    public final String TAG = "demo";

    ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable female_1 = getResources().getDrawable(R.drawable.avatar_f_1);
        Drawable female_2 = getResources().getDrawable(R.drawable.avatar_f_2);
        Drawable female_3 = getResources().getDrawable(R.drawable.avatar_f_3);
        Drawable male_1 = getResources().getDrawable(R.drawable.avatar_m_1);
        Drawable male_2 = getResources().getDrawable(R.drawable.avatar_m_2);
        Drawable male_3 = getResources().getDrawable(R.drawable.avatar_m_3);

        contacts.add(new Contact("Becca Berkeley", "b@b.com", "111-111-1111", "CS", female_1));
        contacts.add(new Contact("Joan Smith", "j@j.com", "222-222-2222", "BIO", female_2));
        contacts.add(new Contact("Seamus Beecher", "s@s.com", "333-333-3333", "CS", male_1));
        contacts.add(new Contact("Dylan Drum", "d@d.com", "444-444-4444", "SIS", male_2));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentView, ContactsFragment.newInstance(), "contactsFragment")
                .commit();
    }

    @Override
    public ArrayList<Contact> getCurrentContacts() {
        return this.contacts;
    }

    @Override
    public void showNewContactFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, NewContactFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showAvatarsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentView, new SelectAvatarFragment().newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void newContactMade(Contact contact) {
        Log.d(TAG, contact.toString());
        contacts.add(contact);
    }

    @Override
    public void avatarChoosen(Drawable img) {
        Log.d(TAG, String.valueOf(img)); //Was able to log the specific img choose
        contacts.get(contacts.size() - 1).setImage(img); //Tried to pull the last entry in arraylist
                                                         // and set the img from selected image from SelectAvatarFragment,
                                                         // didn't work
    }
}