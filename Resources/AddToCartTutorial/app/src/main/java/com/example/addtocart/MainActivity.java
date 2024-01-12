package com.example.addtocart;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.addtocart.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IDrinkLoadListener, ICartLoadListener {

    private ActivityMainBinding binding;
    IDrinkLoadListener drinkLoadListener;
    ICartLoadListener cartLoadListener;
    MyDrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        LoadDrinkFromFirebase();
    }

    private void LoadDrinkFromFirebase() {
        List<DrinkModel> drinkModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot drinkSnapshot:snapshot.getChildren()) {
                                DrinkModel drinkModel = drinkSnapshot.getValue(DrinkModel.class);
                                drinkModel.setKey(drinkSnapshot.getKey());
                                drinkModels.add(drinkModel);
                            }
                            drinkLoadListener.onDrinkLoadSuccess(drinkModels);
                        } else {
                            drinkLoadListener.onDrinkLoadFailed("Can't find Drink");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        drinkLoadListener.onDrinkLoadFailed(error.getMessage());
                    }
                });
    }

    private void init() {
        drinkLoadListener = this;
        cartLoadListener  = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.recyclerDrink.setLayoutManager(gridLayoutManager);
        binding.recyclerDrink.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }

    @Override
    public void onDrinkLoadSuccess(List<DrinkModel> drinkModelList) {
        adapter = new MyDrinkAdapter(this, drinkModelList);
        binding.recyclerDrink.setAdapter(adapter);
    }

    @Override
    public void onDrinkLoadFailed(String message) {
        Log.d("TAG", "Can't load Drink");
        Snackbar.make(binding.mainLayout, message, Snackbar.LENGTH_LONG).show();
    }
}