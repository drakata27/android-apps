package com.example.videogamesstore.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.videogamesstore.activities.RegisterActivity;
import com.example.videogamesstore.activities.SignInActivity;
import com.example.videogamesstore.databinding.FragmentAccountBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        
        if (user == null) {
            binding.userTextview.setText("You are not signed in");
            binding.signInBtn.setOnClickListener(v -> startActivity(new Intent(getContext(), SignInActivity.class)));
            binding.registerBtn.setOnClickListener(v -> startActivity(new Intent(getContext(), RegisterActivity.class)));
        } else {

        }
        return binding.getRoot();
    }
}