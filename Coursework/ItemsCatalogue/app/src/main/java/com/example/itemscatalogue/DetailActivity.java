package com.example.itemscatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.itemscatalogue.databinding.ActivityDetailBinding;
public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // details intent
        Intent intent = getIntent();
        int detailsResource = intent.getIntExtra("key", 0);
        String details = getString(detailsResource);
        binding.details.setText(details);

        int imageResource = getIntent().getIntExtra("imageRes", 0);
        binding.logo.setImageResource(imageResource);

        binding.back.setOnClickListener(v -> finish());
        binding.backArrow.setOnClickListener(v -> finish());
    }
}