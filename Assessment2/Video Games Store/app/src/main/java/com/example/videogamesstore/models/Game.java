package com.example.videogamesstore.models;

public class Game {

    private String name, platform, imgurl;
    private Double price;
    private int qty, currQty;

    public Game() {}

    public Game(String name, String platform, String imgurl, Double price, int qty, int currQty) {
        this.name = name;
        this.platform = platform;
        this.imgurl = imgurl;
        this.price = price;
        this.qty = qty;
        this.currQty = currQty;
    }

    public int getCurrQty() {
        return currQty;
    }

    public void setCurrQty(int currQty) {
        this.currQty = currQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
