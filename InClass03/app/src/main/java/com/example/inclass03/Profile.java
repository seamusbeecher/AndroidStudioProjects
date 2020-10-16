package com.example.inclass03;

import java.io.Serializable;

public class Profile implements Serializable {

    public String name, email, dept;
    public int color;

    public Profile(String name, String email, String dept, int color) {

        this.name = name;
        this.email = email;
        this.dept = dept;
        this.color = color;

    }

    @Override
    public String toString() {

        return "Profile{" +
                "name=" + name + '\'' +
                ", email=" + email + '\'' +
                ", dept=" + dept + '\'' +
                ", color=" + color +
                '}';

    }
}
