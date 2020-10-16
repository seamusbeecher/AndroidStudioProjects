package com.example.inclass;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Contact implements Serializable {

    String name, email, phone, type;
    int id;

    public Contact(int id, String name, String email, String phone, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    public Contact(String line) {
        String[] items = line.split(",");
        try {
            this.id = Integer.valueOf(items[0]);
            this.name = items[1];
            this.email = items[2];
            this.phone = items[3];
            this.type = items[4];
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id= " + id +
                ", name= " + name + '\'' +
                ", email= " + email + '\'' +
                ", phone= " + phone + '\'' +
                ", type= " + type + '\'' +
                '}';
    }
}
