package com.example.videogamesstore;

public class Games {

    String name, platform, price, qty, imgurl;

    public Games() {} // 0 arg constructor for firebase

    public Games(String name, String platform, String price, String qty, String imgurl) {
        this.name = name;
        this.platform = platform;
        this.price = price;
        this.qty = qty;
        this.imgurl = imgurl;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
