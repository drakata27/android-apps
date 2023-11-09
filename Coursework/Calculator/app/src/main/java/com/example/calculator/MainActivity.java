package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    String initialNumText;
    String chainNumText;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        initialNumText = binding.initialNum.getText().toString();
        chainNumText = "0";

        Log.d("InitNum in on create", "InitNum " + initialNumText);

        // clear
        binding.buttonAc.setOnClickListener(v -> {
            binding.chainNum.setText("");
            binding.initialNum.setText("0");
            initialNumText = "0";
            chainNumText = "0";
        });

        // addition
        binding.buttonAdd.setOnClickListener(v -> {
            double sum = Double.parseDouble(binding.initialNum.getText().toString()) + Double.parseDouble(chainNumText);

            if (binding.chainNum.getText().toString().contains("=")) {
                chainNumText = binding.initialNum.getText().toString();
            } else {
                chainNumText = decimalFormat.format(sum);
            }
            binding.chainNum.setText(chainNumText  + " +");
            binding.initialNum.setText("0");
            initialNumText = "0";
        });

        // equals
        binding.buttonEquals.setOnClickListener(v -> {
            double sum = Double.parseDouble(chainNumText) + Double.parseDouble(initialNumText);
            binding.chainNum.setText(chainNumText + " + " + initialNumText + " =");
            binding.initialNum.setText(decimalFormat.format(sum));

            // TODO test
            if (binding.chainNum.getText().toString().contains("=")) {
                chainNumText = decimalFormat.format(sum);
            }
        });
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();



        // TODO test
        if (binding.chainNum.getText().toString().contains("=")) {
            binding.chainNum.setText("");
            initialNumText = "";
            chainNumText = "0";
        }

        if (initialNumText.length() < 10) {
            if (initialNumText.equals("0")) {
                initialNumText = buttonText;
            } else {
                initialNumText += buttonText;
            }
        }

        binding.initialNum.setText(initialNumText);
    }
}