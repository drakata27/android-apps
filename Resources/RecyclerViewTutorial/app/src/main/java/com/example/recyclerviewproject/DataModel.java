package com.example.recyclerviewproject;

public class DataModel {
    String heading;
    String text1;
    String text2;
    int image;

    public DataModel(String heading, String text1, String text2, int image) {
        this.heading = heading;
        this.text1 = text1;
        this.text2 = text2;
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    public int getImage() {
        return image;
    }
}
