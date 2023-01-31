package com.project.m_ajira.Model;

public class User {
    public String fullName,email, phone, password;

    public User(){

    }

    public User(String fullName, String email, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
