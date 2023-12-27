package com.example.videogamesstore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.videogamesstore.databinding.ActivityCheckoutBinding;
import com.example.videogamesstore.fragments.CartFragment;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity {
    private ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .setup(CheckoutActivity.this);

        binding.cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        binding.backArrow.setOnClickListener(v -> finish());

        binding.payBtn.setOnClickListener(v -> {
            if(binding.cardForm.isValid()) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CheckoutActivity.this);
                alertBuilder.setTitle("Confirm before purchase");
                alertBuilder.setMessage("Card number: " + binding.cardForm.getCardNumber() + "\n" +
                        "Card expiry date: " + Objects.requireNonNull(binding.cardForm.getExpirationDateEditText().getText()) + "\n" +
                        "Card CVV: " + binding.cardForm.getCvv() + "\n" +
                        "Postal code: " + binding.cardForm.getPostalCode());

                alertBuilder.setPositiveButton("Confirm", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Toast.makeText(CheckoutActivity.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                    clearCart();
                    startActivity(new Intent(getApplicationContext(), CartFragment.class));

                });
                alertBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            } else {
                Toast.makeText(CheckoutActivity.this, "Please complete the form", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearCart() {
        FirebaseDatabase.getInstance().getReference().child("AddToCart").removeValue();
    }
}