package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginscreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialise intent
        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);

        attempts = 3;

        binding.submitBtn.setOnClickListener(v -> submitInput(intent));
        binding.clearBtn.setOnClickListener(v -> clearInput());

    }

     private boolean validateUsername() {
         String username = binding.textInputUsername.getEditText().getText().toString().trim();

         if (username.isEmpty()) {
             binding.textInputUsername.setError("Field can't be empty");
             return false;
         } else if (username.length() < 4) {
             binding.textInputUsername.setError("Username is too short");
             return false;
         } else if (username.length() > 20) {
             binding.textInputUsername.setError("Username is too long");
             return false;
         } else if (!username.equals("admin")) {
             binding.textInputUsername.setError("Wrong username");
             return false;
         } else {
             binding.textInputUsername.setError(null);
             return true;
         }
     }

     private boolean validatePassword() {
        String password = binding.textInputPassword.getEditText().getText().toString().trim();

         if (password.isEmpty()) {
             binding.textInputPassword.setError("Field cannot be empty");
             return false;
         } else if (password.length() < 4) {
             binding.textInputPassword.setError("Password is too short");
             return false;
         } else if (password.length() > 20) {
             binding.textInputPassword.setError("Password is too long");
             return false;
         } else if (!password.equals("admin")) {
             binding.textInputPassword.setError("Wrong password");
             return false;
         } else {
             binding.textInputPassword.setError(null);
             return true;
         }
     }

     private void submitInput(Intent intent) {
        if (!validateUsername() | !validatePassword()) {
            attempts -= 1;
        } else {
            intent.putExtra("username", binding.textInputUsername.getEditText().getText());
            Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        if (attempts == 2) {
            binding.attempts.setTextColor(ContextCompat.getColor(this, R.color.orange));
        } else if (attempts == 1) {
            binding.attempts.setTextColor(ContextCompat.getColor(this, R.color.red));
        } else if (attempts == 0) {
             Toast.makeText(this, "No more attempts left", Toast.LENGTH_LONG).show();
             this.finishAffinity();
         }
        binding.attempts.setText("" + attempts);
     }

     private void clearInput() {
        binding.textInputUsername.getEditText().setText("");
        binding.textInputPassword.getEditText().setText("");
     }
}