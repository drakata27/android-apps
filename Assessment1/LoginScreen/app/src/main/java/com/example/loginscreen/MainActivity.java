package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.loginscreen.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int attempts;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        username = "admin";

        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);

        attempts = 3;

        binding.submitBtn.setOnClickListener(v -> submitInput(intent));
        binding.clearBtn.setOnClickListener(v -> clearInput());

    }

     private boolean validateUsername() {
         String usernameText = Objects.requireNonNull(binding.textInputUsername.getEditText()).getText().toString().trim();

         if (usernameText.isEmpty()) {
             binding.textInputUsername.setError("Field cannot be empty");
             return false;
         } else if (usernameText.length() < 4) {
             binding.textInputUsername.setError("Username is too short");
             return false;
         } else if (usernameText.length() > 20) {
             binding.textInputUsername.setError("Username is too long");
             return false;
         } else if (!usernameText.equals(username)) {
             showCredentialsError();
             return false;
         } else {
             binding.textInputUsername.setError(null);
             return true;
         }
     }

     private boolean validatePassword() {
        String passwordText = Objects.requireNonNull(binding.textInputPassword.getEditText()).getText().toString().trim();
        String password = "admin";

         if (passwordText.isEmpty()) {
             binding.textInputPassword.setError("Field cannot be empty");
             return false;
         } else if (passwordText.length() < 4) {
             binding.textInputPassword.setError("Password is too short");
             return false;
         } else if (passwordText.length() > 20) {
             binding.textInputPassword.setError("Password is too long");
             return false;
         } else if (!passwordText.equals(password)) {
             showCredentialsError();
             return false;
         } else {
             binding.textInputPassword.setError(null);
             return true;
         }
     }

     @SuppressLint("SetTextI18n")
     private void submitInput(Intent intent) {
        if (!validateUsername() | !validatePassword()) {
            attempts--;
        } else {
            intent.putExtra("username", username);
            Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

         if (attempts == 0) {
             Toast.makeText(this, "No more attempts left", Toast.LENGTH_LONG).show();
             this.finishAffinity();
         }

        binding.attempts.setText("" + attempts);
     }

     private void clearInput() {
        Objects.requireNonNull(binding.textInputUsername.getEditText()).setText("");
        binding.textInputUsername.setError(null);
        Objects.requireNonNull(binding.textInputPassword.getEditText()).setText("");
        binding.textInputPassword.setError(null);
     }

     private void showCredentialsError() {
         binding.textInputUsername.setError("Wrong credentials");
         binding.textInputPassword.setError("Wrong credentials");
     }
}