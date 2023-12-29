package com.example.videogamesstore.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.videogamesstore.R;
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
            signOut();
        } else {
            binding.userTextview.setText(user.getEmail());

            binding.btn1.setText("Sign Out");
            binding.btn1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.red));

            binding.btn2.setText("Reset Password");

            binding.btn1.setOnClickListener(v -> {
                signOut();
                Toast.makeText(getContext(), "You are signed out", Toast.LENGTH_SHORT).show();
            });

            binding.btn2.setOnClickListener(v -> {
                String email = auth.getCurrentUser().getEmail();

                auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Password reset email sent.", Toast.LENGTH_SHORT).show();
                        signOut();
                    } else {
                        Toast.makeText(getContext(), "Failed to reset password.", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        }
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}