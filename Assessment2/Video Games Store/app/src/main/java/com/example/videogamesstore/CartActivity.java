package com.example.videogamesstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.videogamesstore.databinding.ActivityCartBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Games> options =
                new FirebaseRecyclerOptions.Builder<Games>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddToCart"), Games.class)
                        .build();

        cartAdapter = new CartAdapter(options);
        binding.recyclerView.setAdapter(cartAdapter);

        binding.backArrow.setOnClickListener(v -> finish());
    }

    @Override
    protected void onStart() {
        super.onStart();
        cartAdapter.startListening();
//        updateTotalPrice();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cartAdapter.startListening();
    }

//    // Calculate and update the total price for all items in the cart
//    private void updateTotalPrice() {
//        double total = 0;
//        for (int i = 0; i < cartAdapter.getItemCount(); i++) {
//            Games game = cartAdapter.getItem(i);
//            if (game != null) {
//                total += game.getPrice() * game.getQty();
//            }
//        }
//        binding.totalTxt.setText(String.format(Locale.UK, "Total: £%.2f", total));
//    }
}