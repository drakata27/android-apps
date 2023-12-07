package com.example.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    Button cancelBtn;
    Button resetBtn;

    TextInputEditText editTextEmail;
    FirebaseAuth auth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        progressBar = findViewById(R.id.progress_bar);
        cancelBtn = findViewById(R.id.btn_cancel);
        resetBtn = findViewById(R.id.btn_reset);

        cancelBtn.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        resetBtn.setOnClickListener(v -> {
            String email = String.valueOf(editTextEmail.getText());

            if (!TextUtils.isEmpty(email)) {
                resetPassword(email);
            } else {
                editTextEmail.setError("Email can't be empty");
            }

        });

    }

    private void resetPassword(String email) {
        resetBtn.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ResetPassword.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(ResetPassword.this, "Failed to send reset email. Check your email address.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}