package com.example.firebase_crud_app;

public class MainModel {
    String name, course, email, surl;

    MainModel() {

    }

    public MainModel(String name, String course, String email, String surl) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }


    public String getCourse() {
        return course;
    }


    public String getEmail() {
        return email;
    }


    public String getSurl() {
        return surl;
    }
}
