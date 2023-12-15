package com.example.videogamesstore;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.videogamesstore.databinding.ActivityCartBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartTotalListener{
    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;

    @SuppressLint("SetTextI18n")
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

        cartAdapter = new CartAdapter(options, this);
        binding.recyclerView.setAdapter(cartAdapter);

        binding.backArrow.setOnClickListener(v -> finish());



        binding.checkoutBtn.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Proceeding to checkout. Your total is £" + cartAdapter.getTotal(), Toast.LENGTH_SHORT).show();
            binding.totalTxt.setText("Total: £" + String.format(Locale.UK,"%.2f", cartAdapter.getTotal()));
        });

        cartAdapter.updateTotalInAdapter();

    }

    @Override
    protected void onStart() {
        super.onStart();
        cartAdapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onCartTotalUpdated(double total) {
        binding.totalTxt.setText("Total: £" + String.format(Locale.UK,"%.2f", total));
    }

}