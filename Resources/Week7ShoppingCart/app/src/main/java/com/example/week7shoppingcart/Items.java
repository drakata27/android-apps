package com.example.week7shoppingcart;

public class Items {
    private int image;
    private String text;
    private int price;
    private int quantity;

    public Items(int image, String text, int price) {
        this.image = image;
        this.text = text;
        this.price = price;
    }

    public Items(String text, int price, int quantity) {
        this.text = text;
        this.price = price;
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Items{" +
                "image=" + image +
                ", text='" + text + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
