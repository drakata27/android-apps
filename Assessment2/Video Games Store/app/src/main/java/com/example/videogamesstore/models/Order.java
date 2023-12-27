package com.example.videogamesstore.models;

public class Order {
    private String userId;
    private String userEmail;
    private String name;
    private int qty;
    private double price;
    private String platform;
    private String postcode;
    private String orderDateTime;

    public Order() {

    }

    public Order(String userId, String userEmail, String name, int qty, double price, String platform, String postcode, String orderDateTime) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.platform = platform;
        this.postcode = postcode;
        this.orderDateTime = orderDateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
}
