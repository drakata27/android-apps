package com.example.videogamesstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.videogamesstore.R;
import com.example.videogamesstore.databinding.ActivityMainBinding;
import com.example.videogamesstore.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backArrow.setOnClickListener(v -> finish());
    }
}