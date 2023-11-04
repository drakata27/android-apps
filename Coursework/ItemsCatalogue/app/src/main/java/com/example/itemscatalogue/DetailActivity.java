package com.example.itemscatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.itemscatalogue.databinding.ActivityDetailBinding;
import com.example.itemscatalogue.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // back intent
        Intent back = new Intent(DetailActivity.this, MainActivity.class);

        // details intent
        Intent intent = getIntent();
        String data = intent.getStringExtra("key");
        binding.details.setText(data);

        int imageResource = getIntent().getIntExtra("imageRes", 0);
        binding.logo.setImageResource(imageResource);


        binding.back.setOnClickListener(v -> {
            startActivity(back);
        });
    }
}