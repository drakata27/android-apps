package com.example.videogamesstore.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.videogamesstore.adapters.CartAdapter;
import com.example.videogamesstore.databinding.FragmentCartBinding;
import com.example.videogamesstore.interfaces.CartTotalListener;
import com.example.videogamesstore.models.Games;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class CartFragment extends Fragment implements CartTotalListener {

    private FragmentCartBinding binding;
    private CartAdapter cartAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false);
        binding.totalTxt.postDelayed(() -> binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", cartAdapter.getTotal())), 500);

        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FirebaseRecyclerOptions<Games> options =
                new FirebaseRecyclerOptions.Builder<Games>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddToCart"), Games.class)
                        .build();

        cartAdapter = new CartAdapter(options, this);
        binding.recyclerView.setAdapter(cartAdapter);


        binding.checkoutBtn.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Proceeding to checkout. Your total is £" + cartAdapter.getTotal(), Toast.LENGTH_SHORT).show();
            binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", cartAdapter.getTotal()));
        });

//        cartAdapter.updateTotalInAdapter();
    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
        Log.d("getTotal()", ""+cartAdapter.getTotal());
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCartTotalUpdated(double total) {
        binding.totalTxt.setText("Total: £" + String.format(Locale.UK, "%.2f", total));
    }
}
