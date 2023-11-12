package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    String inputNum;
    String chainNum;
    String currentOperation;
    int multiCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DecimalFormat decimalFormat = new DecimalFormat("#.########");

        inputNum = binding.inputNum.getText().toString();
        chainNum = "0";
        multiCount = 0;

        // clear
        binding.buttonC.setOnClickListener(v -> {
            binding.chainNum.setText("");
            resetInput();
            chainNum = "0";
            multiCount = 0;
        });

        // clear entry
        binding.buttonCe.setOnClickListener(v -> {
            resetInput();
            multiCount = 0;
        });

        // delete
        binding.buttonDelete.setOnClickListener(v -> {
            String currentText = binding.inputNum.getText().toString();

            if (currentText.length() > 0) {
                // Remove the last character from the text
                currentText = currentText.substring(0, currentText.length() - 1);

                if (currentText.length() > 0) {
                    // If there are characters remaining, update the text
                    binding.inputNum.setText(currentText);
                    inputNum = currentText;  // Save the updated text to initialNumText
                } else {
                    // If there are no characters remaining, set the text to "0"
                    resetInput();
                }
            }
        });

        // addition
        binding.buttonAdd.setOnClickListener(v -> {
            currentOperation = "+";
            double sum = Double.parseDouble(binding.inputNum.getText().toString()) + Double.parseDouble(chainNum);

            if (binding.chainNum.getText().toString().contains("=")) {
                chainNum = binding.inputNum.getText().toString();
            } else {
                chainNum = decimalFormat.format(sum);
            }
            binding.chainNum.setText(chainNum + " +");
            resetInput();
        });

        // subtraction
        binding.buttonSubtract.setOnClickListener(v -> {
            currentOperation = "-";

            double difference = Double.parseDouble(binding.inputNum.getText().toString()) - Double.parseDouble(chainNum);

            if (binding.chainNum.getText().toString().contains("=")) {

                chainNum = binding.inputNum.getText().toString();
            } else {
                chainNum = decimalFormat.format(difference);
            }
            binding.chainNum.setText(chainNum + " -");
            resetInput();
        });

        // multiplication
        binding.buttonMultiply.setOnClickListener(v -> {
            currentOperation = "*";
            multiCount++;

            if (multiCount == 1) {
                binding.chainNum.setText(binding.inputNum.getText().toString() + " *");
                chainNum = binding.inputNum.getText().toString();

                inputNum = "0";
                binding.inputNum.setText(inputNum);

            }

            double product = Double.parseDouble(chainNum) * Double.parseDouble(inputNum);

            if (multiCount == 2) {

                if (binding.chainNum.getText().toString().contains("=")) {
                    product = Double.parseDouble(binding.inputNum.getText().toString());
                }

                binding.chainNum.setText(decimalFormat.format(product) + " *");

                inputNum = "0";
                chainNum = binding.inputNum.getText().toString();

            }

            if (multiCount > 2) {
                if (binding.chainNum.getText().toString().contains("=")) {
                    product = Double.parseDouble(binding.inputNum.getText().toString());
                } else {
                    product = Double.parseDouble(chainNum) * Double.parseDouble(binding.inputNum.getText().toString());
                }

                binding.chainNum.setText(decimalFormat.format(product) + " *");
                chainNum = decimalFormat.format(product);
                resetInput();
            }
        });

        binding.buttonDivide.setOnClickListener(v -> {
            currentOperation = "/";

            double divisor = Double.parseDouble(binding.inputNum.getText().toString());

            if (binding.chainNum.getText().toString().contains("=")) {
                chainNum = binding.inputNum.getText().toString();
            } else {
                chainNum = decimalFormat.format(divisor);
            }
            binding.chainNum.setText(chainNum + " /");
            resetInput();
        });

        // dot
        binding.buttonDot.setOnClickListener(v -> {
            if (!binding.inputNum.getText().toString().contains(".")) {
                inputNum += ".";
                binding.inputNum.setText(inputNum);
            }
        });

        // equals
        binding.buttonEquals.setOnClickListener(v -> {
            switch (currentOperation) {
                case "+":
                    double sum = Double.parseDouble(chainNum) + Double.parseDouble(inputNum);
                    binding.chainNum.setText(chainNum + " + " + inputNum + " =");
                    binding.inputNum.setText(decimalFormat.format(sum));

                    // TODO test
                    if (binding.chainNum.getText().toString().contains("=")) {
                        chainNum = decimalFormat.format(sum);
                    }
                    break;
                case "-":
                    double diff = Double.parseDouble(chainNum) - Double.parseDouble(inputNum);
                    binding.chainNum.setText(chainNum + " - " + inputNum + " =");
                    binding.inputNum.setText(decimalFormat.format(diff));

                    // TODO test
                    if (binding.chainNum.getText().toString().contains("=")) {
                        chainNum = decimalFormat.format(diff);
                    }
                    break;
                case "*":
                    double product = Double.parseDouble(inputNum) * Double.parseDouble(chainNum);
                    binding.chainNum.setText(chainNum + " * " + inputNum + " =");
                    binding.inputNum.setText(decimalFormat.format(product));

                    // TODO test
                    if (binding.chainNum.getText().toString().contains("=")) {
                        chainNum = decimalFormat.format(product);
                    }
                    break;
                case "/":
                    double divisor = Double.parseDouble(inputNum);
                    double result = Double.parseDouble(chainNum) / divisor;

                    // Update the UI with the division result
                    binding.chainNum.setText(chainNum + " / " + inputNum + " =");
                    binding.inputNum.setText(decimalFormat.format(result));

                    // Reset operation
                    currentOperation = "";
                    break;
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
            inputNum = "";
            chainNum = "0";
        }

        if (inputNum.length() < 10) {
            if (inputNum.equals("0")) {
                inputNum = buttonText;
            } else {
                inputNum += buttonText;
            }
        }
        binding.inputNum.setText(inputNum);
    }

    public void resetInput() {
        binding.inputNum.setText("0");
        inputNum = "0";
    }

}