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
    DecimalFormat decimalFormat;
    boolean entryCleared;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        decimalFormat = new DecimalFormat("#.########");

        inputNum = binding.inputNumTv.getText().toString();
        chainNum = "0";

        entryCleared = false;

        // clear
        binding.buttonC.setOnClickListener(v -> {
            binding.chainNumTv.setText("");
            resetInput();
            chainNum = "0";
        });

        // clear entry
        binding.buttonCe.setOnClickListener(v -> {
            entryCleared = true;
            resetInput();
        });

        // delete
        binding.buttonDelete.setOnClickListener(v -> {
            String currentText = binding.inputNumTv.getText().toString();

            if (currentText.length() > 0) {
                // Remove the last character from the text
                currentText = currentText.substring(0, currentText.length() - 1);

                if (currentText.length() > 0) {
                    // If there are characters remaining, update the text
                    binding.inputNumTv.setText(currentText);
                    inputNum = currentText;  // Save the updated text to initialNumText
                } else {
                    // If there are no characters remaining, set the text to "0"
                    resetInput();
                }
            }
        });

        // addition
        binding.buttonAdd.setOnClickListener(v -> {
            if (isValidNumber(inputNum)) {
                currentOperation = "+";
                double sum = Double.parseDouble(binding.inputNumTv.getText().toString()) + Double.parseDouble(chainNum);

                if (isSolved()) {
                    chainNum = binding.inputNumTv.getText().toString();
                } else {
                    chainNum = decimalFormat.format(sum);
                }
                binding.chainNumTv.setText(chainNum + " +");
                resetInput();
            }
        });

        // subtraction
        binding.buttonSubtract.setOnClickListener(v -> {
            if (isValidNumber(inputNum)) {
                currentOperation = "-";
                double difference = Double.parseDouble(binding.inputNumTv.getText().toString()) - Double.parseDouble(chainNum);

                if (isSolved()) {
                    chainNum = binding.inputNumTv.getText().toString();
                } else {
                    chainNum = decimalFormat.format(difference);
                }
                binding.chainNumTv.setText(chainNum + " -");
                resetInput();
            }
        });

        // multiplication
        binding.buttonMultiply.setOnClickListener(v -> {
            if (isValidNumber(inputNum)) {
                currentOperation = "*";
                binding.chainNumTv.setText(binding.inputNumTv.getText().toString() + " *");
                chainNum = binding.inputNumTv.getText().toString();

                inputNum = "0";
                binding.inputNumTv.setText(inputNum);
            }
        });

        binding.buttonDivide.setOnClickListener(v -> {
            if (isValidNumber(inputNum)) {
                currentOperation = "/";

                double divisor = Double.parseDouble(binding.inputNumTv.getText().toString());

                if (isSolved()) {
                    chainNum = binding.inputNumTv.getText().toString();
                } else {
                    chainNum = decimalFormat.format(divisor);
                }
                binding.chainNumTv.setText(chainNum + " /");
                resetInput();
            }

        });

        // dot
        binding.buttonDot.setOnClickListener(v -> {
            if (!binding.inputNumTv.getText().toString().contains(".") && isValidNumber(inputNum)) {
                inputNum += ".";
                binding.inputNumTv.setText(inputNum);
            }

            if (isSolved() && !entryCleared) {
                binding.chainNumTv.setText("");
                chainNum = "0";
                binding.inputNumTv.setText("0.");
                inputNum = "0.";
            }
        });

        // equals
        binding.buttonEquals.setOnClickListener(v -> {
            if (isValidNumber(inputNum)) {
                switch (currentOperation) {
                    case "+":
                        calculate(chainNum, inputNum, "+");

                        break;
                    case "-":
                        calculate(chainNum, inputNum, "-");

                        break;
                    case "*":
                        calculate(chainNum, inputNum,"*");

                        break;
                    case "/":
                        if (inputNum.equals("0")) {
                            binding.chainNumTv.setText("Cannot divide by zero");
                            chainNum = "0";
                            resetInput();
                        } else {
                            calculate(chainNum, inputNum,"/");
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        if (isSolved() && !entryCleared) {
            binding.chainNumTv.setText("");
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
        binding.inputNumTv.setText(inputNum);
    }

    private void resetInput() {
        binding.inputNumTv.setText("0");
        inputNum = "0";
    }

    private void calculate(String num1, String num2, String sign) {
        double result = 0.0;

        if (isValidNumber(inputNum)) {
            switch (sign) {
                case "+":
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    break;
                case "-":
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    break;
                case "*":
                    result = Double.parseDouble(num1) * Double.parseDouble(num2);
                    break;
                case "/":
                    result = Double.parseDouble(num1) / Double.parseDouble(num2);
                    break;
            }
        }

        binding.chainNumTv.setText(chainNum + " " + sign + " " + inputNum + " =");
        binding.inputNumTv.setText(decimalFormat.format(result));

        if (binding.chainNumTv.getText().toString().contains("=")) {
            chainNum = decimalFormat.format(result);
        }
    }

    private boolean isValidNumber(String num) {
        return !num.equals("-") && !num.isEmpty();
    }

    private boolean isSolved() {
        entryCleared = false;
        return binding.chainNumTv.getText().toString().contains("=");
    }
}