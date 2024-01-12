package com.example.week7shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BasketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        TextView txtTotalPay = findViewById(R.id.txtTotalPay);
        Button btnGoBack = findViewById(R.id.btnGoBack);
        Button btnBuy = findViewById(R.id.btnBuy);

        Intent intent = getIntent();

        String everythingInTheBasketText = Objects.requireNonNull(intent.getExtras()).getString("everythingInTheBasket");

        txtTotalPay.setText(everythingInTheBasketText);

        btnGoBack.setOnClickListener(view -> {

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });

        btnBuy.setOnClickListener(view -> Toast.makeText(getApplicationContext(),"This should go to buy screen",Toast.LENGTH_SHORT).show());

    }
}