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
    String currentOperation;
    int multiCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        initialNumText = binding.initialNum.getText().toString();
        chainNumText = "0";

        multiCount = 0;

        // all clear
        binding.buttonAc.setOnClickListener(v -> {
            binding.chainNum.setText("");
            binding.initialNum.setText("0");
            initialNumText = "0";
            chainNumText = "0";
        });

        // clear entry
        binding.buttonCe.setOnClickListener(v -> {
            binding.initialNum.setText("0");
            initialNumText = "0";
        });

        // delete
        binding.buttonDelete.setOnClickListener(v -> {
            String currentText = binding.initialNum.getText().toString();

            if (currentText.length() > 0) {
                // Remove the last character from the text
                currentText = currentText.substring(0, currentText.length() - 1);

                if (currentText.length() > 0) {
                    // If there are characters remaining, update the text
                    binding.initialNum.setText(currentText);
                } else {
                    // If there are no characters remaining, set the text to "0"
                    binding.initialNum.setText("0");
                }
            }
        });

        // addition
        binding.buttonAdd.setOnClickListener(v -> {
            currentOperation = "+";
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

        // subtraction
        binding.buttonSubtract.setOnClickListener(v -> {
            currentOperation = "-";

            double difference = Double.parseDouble(binding.initialNum.getText().toString()) - Double.parseDouble(chainNumText);

            if (binding.chainNum.getText().toString().contains("=")) {
                chainNumText = binding.initialNum.getText().toString();
            } else {
                chainNumText = decimalFormat.format(difference);
            }
            binding.chainNum.setText(chainNumText  + " -");
            binding.initialNum.setText("0");
            initialNumText = "0";
        });

        // multiplication
        binding.buttonMultiply.setOnClickListener(v -> {
            currentOperation = "*";

//            double product = Double.parseDouble(binding.initialNum.getText().toString()) * Double.parseDouble(chainNumText);
//
//            if (binding.chainNum.getText().toString().contains("=")) {
//                chainNumText = binding.initialNum.getText().toString();
//            } else {
//                chainNumText = decimalFormat.format(product);
//            }
//            binding.chainNum.setText(chainNumText  + " *");
//            binding.initialNum.setText("0");
//            initialNumText = "0";

            multiCount ++;



            if (multiCount > 0) {
                chainNumText = binding.initialNum.getText().toString();
                binding.chainNum.setText(chainNumText + " *");

                initialNumText = binding.initialNum.getText().toString();
                binding.initialNum.setText(initialNumText);

                binding.initialNum.setText("0");
                initialNumText = "0";
            }

            double product = Double.parseDouble(binding.initialNum.getText().toString()) * Double.parseDouble(chainNumText);
            if (multiCount > 2) {
                binding.chainNum.setText(decimalFormat.format(product) + " *");
                multiCount = 0;
            }
            Log.d("multiCount", String.valueOf(multiCount));
            Log.d("initNumText", String.valueOf(initialNumText));
            Log.d("chainNumText", String.valueOf(chainNumText));
            Log.d("product", String.valueOf(product));

        });

        // equals
        binding.buttonEquals.setOnClickListener(v -> {
            if (currentOperation.equals("+")) {
                double sum = Double.parseDouble(chainNumText) + Double.parseDouble(initialNumText);
                binding.chainNum.setText(chainNumText + " + " + initialNumText + " =");
                binding.initialNum.setText(decimalFormat.format(sum));

                // TODO test
                if (binding.chainNum.getText().toString().contains("=")) {
                    chainNumText = decimalFormat.format(sum);
                }
            } else if (currentOperation.equals("-")) {
                double diff = Double.parseDouble(chainNumText) - Double.parseDouble(initialNumText);
                binding.chainNum.setText(chainNumText + " - " + initialNumText + " =");
                binding.initialNum.setText(decimalFormat.format(diff));

                // TODO test
                if (binding.chainNum.getText().toString().contains("=")) {
                    chainNumText = decimalFormat.format(diff);
                }
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