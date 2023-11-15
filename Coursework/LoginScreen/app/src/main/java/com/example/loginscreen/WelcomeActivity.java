package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginscreen.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // binding
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent logOutIntent = new Intent(WelcomeActivity.this, MainActivity.class);

        // username intent
        Intent intent = getIntent();
        String data = intent.getStringExtra("username");
        binding.username.setText(data);

        // log out
        binding.logOutBtn.setOnClickListener(v -> {
            Toast.makeText(this, "You have been logged out", Toast.LENGTH_SHORT).show();
            startActivity(logOutIntent);
        });
    }
}