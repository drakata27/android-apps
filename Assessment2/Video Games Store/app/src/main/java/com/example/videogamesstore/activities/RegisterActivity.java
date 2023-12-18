package com.example.videogamesstore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.videogamesstore.R;
import com.example.videogamesstore.databinding.ActivityRegisterBinding;
import com.example.videogamesstore.databinding.ActivitySignInBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backArrow.setOnClickListener(v -> finish());
    }
}