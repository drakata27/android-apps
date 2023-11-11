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
            multiCount = 0;
        });

        // clear entry
        binding.buttonCe.setOnClickListener(v -> {
            binding.initialNum.setText("0");
            initialNumText = "0";
            multiCount = 0;
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
                    initialNumText = currentText;  // Save the updated text to initialNumText
                } else {
                    // If there are no characters remaining, set the text to "0"
                    binding.initialNum.setText("0");
                    initialNumText = "0";
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
            multiCount++;

            if (multiCount == 1) {
                binding.chainNum.setText(binding.initialNum.getText().toString() + " *");
                chainNumText = binding.initialNum.getText().toString();

                initialNumText = "0";
                binding.initialNum.setText(initialNumText);

            }

            double product = Double.parseDouble(chainNumText) * Double.parseDouble(initialNumText);

            if (multiCount == 2) {

                if (binding.chainNum.getText().toString().contains("=")) {
                    product = Double.parseDouble(binding.initialNum.getText().toString());
                }

                binding.chainNum.setText(decimalFormat.format(product) + " *");

                initialNumText = "0";
                chainNumText = binding.initialNum.getText().toString();

            }

            if (multiCount > 2) {
                if (binding.chainNum.getText().toString().contains("=")) {
                    product = Double.parseDouble(binding.initialNum.getText().toString());
                } else {
                    product = Double.parseDouble(chainNumText) * Double.parseDouble(binding.initialNum.getText().toString());
                }

                binding.chainNum.setText(decimalFormat.format(product) + " *");
                chainNumText = decimalFormat.format(product);

                binding.initialNum.setText("0");
                initialNumText = "0";
            }
        });

        binding.buttonDivide.setOnClickListener(v -> {
            currentOperation = "/";

            double divisor = Double.parseDouble(binding.initialNum.getText().toString());

            if (binding.chainNum.getText().toString().contains("=")) {
                chainNumText = binding.initialNum.getText().toString();
            } else {
                chainNumText = decimalFormat.format(divisor);
            }
            binding.chainNum.setText(chainNumText + " /");
            binding.initialNum.setText("0");
            initialNumText = "0";
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
            } else if (currentOperation.equals("*")) {
                double product = Double.parseDouble(initialNumText) * Double.parseDouble(chainNumText);
                binding.chainNum.setText(chainNumText + " * " + initialNumText + " =");
                binding.initialNum.setText(decimalFormat.format(product));

                // TODO test
                if (binding.chainNum.getText().toString().contains("=")) {
                    chainNumText = decimalFormat.format(product);
                }
            } else if (currentOperation.equals("/")) {
                double divisor = Double.parseDouble(initialNumText);
                double result = Double.parseDouble(chainNumText) / divisor;

                // Update the UI with the division result
                binding.chainNum.setText(chainNumText + " / " + initialNumText + " =");
                binding.initialNum.setText(decimalFormat.format(result));

                // Reset operation
                currentOperation = "";
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