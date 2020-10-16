package com.example.hw02;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {

    public String name, email, department, mood;
    public int accountState;

    public Student(String name, String email, String department, String mood, int accountState) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.accountState = accountState;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name + '\'' +
                " email=" + email + '\'' +
                " department=" + department + '\'' +
                " mood=" + mood + '\'' +
                " account state=" + accountState +
                '}';
    }
}
