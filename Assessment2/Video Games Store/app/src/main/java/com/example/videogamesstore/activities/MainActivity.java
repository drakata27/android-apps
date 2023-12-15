package com.example.videogamesstore.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.videogamesstore.R;
import com.example.videogamesstore.databinding.ActivityMainBinding;
import com.example.videogamesstore.fragments.AccountFragment;
import com.example.videogamesstore.fragments.CartFragment;
import com.example.videogamesstore.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragments(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home) {
                replaceFragments(new HomeFragment());
            } else if (item.getItemId() == R.id.cart) {
                replaceFragments(new CartFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragments(new AccountFragment());
            }
            return true;
        });
    }

    private void replaceFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_start, fragment);
        fragmentTransaction.commit();
    }
}