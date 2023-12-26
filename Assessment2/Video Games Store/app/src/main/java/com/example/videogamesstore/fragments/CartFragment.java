package com.example.videogamesstore.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.videogamesstore.activities.CheckoutActivity;
import com.example.videogamesstore.activities.MainActivity;
import com.example.videogamesstore.activities.RegisterActivity;
import com.example.videogamesstore.adapters.CartAdapter;
import com.example.videogamesstore.databinding.FragmentCartBinding;
import com.example.videogamesstore.interfaces.CartTotalListener;
import com.example.videogamesstore.models.Games;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class CartFragment extends Fragment implements CartTotalListener {

    private FragmentCartBinding binding;
    private CartAdapter cartAdapter;
    FirebaseAuth auth;
    FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.totalTxt.postDelayed(() -> {
            binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", cartAdapter.getTotal()));
        }, 30);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<Games> options =
                new FirebaseRecyclerOptions.Builder<Games>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddToCart"), Games.class)
                        .build();

        cartAdapter = new CartAdapter(options, this);
        binding.recyclerView.setAdapter(cartAdapter);

        binding.checkoutBtn.setOnClickListener(v -> {
            if(user == null) {
                Toast.makeText(requireContext(), "Sign in to proceed", Toast.LENGTH_SHORT).show();
            } else {
                if(cartAdapter.getTotal() == 0) {
                    Toast.makeText(requireContext(), "You don't have any items in your cart", Toast.LENGTH_SHORT).show();
                    binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", cartAdapter.getTotal()));
                } else {
                    binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", cartAdapter.getTotal()));
                    startActivity(new Intent(getContext(), CheckoutActivity.class));
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
        cartAdapter.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCartTotalUpdated(double total) {

        binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", total));
    }
}
