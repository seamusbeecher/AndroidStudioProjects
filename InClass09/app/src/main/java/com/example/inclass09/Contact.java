/**
 * Seamus Beecher & Cam McIntosh
 * InClass09
 * Contact.java
 */

package com.example.inclass09;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {

    public String name, email, phone, type;
    public int image;

    public Contact(String name, String email, String phone, String type, int image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                ", image=" + image +
                '}';
    }
}
