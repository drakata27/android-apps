package com.example.videogamesstore.interfaces;

public interface CartTotalListener {
    void onCartTotalUpdated(double total);
    void onCartQuantityUpdated(double qty);
}