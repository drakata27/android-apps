package com.example.videogamesstore.models;

public class Game {

    private String userId, gameId, name, platform, imgurl;
    private Double price;
    private int qty, currQty, position;

    public Game() {}

    public Game(String userId, String gameId, String name, String platform,
                String imgurl, Double price, int qty, int currQty, int position) {
        this.userId = userId;
        this.gameId = gameId;
        this.name = name;
        this.platform = platform;
        this.imgurl = imgurl;
        this.price = price;
        this.qty = qty;
        this.currQty = currQty;
        this.position = position;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getCurrQty() {
        return currQty;
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

    public String getImgurl() {
        return imgurl;
    }

    public Double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
