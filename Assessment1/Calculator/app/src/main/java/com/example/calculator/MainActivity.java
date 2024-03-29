package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    String inputNum;
    String chainNum;
    double result;
    String currentOperation;
    DecimalFormat decimalFormat;
    boolean entryCleared;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        decimalFormat = new DecimalFormat("#.########");
        inputNum = binding.inputNumTv.getText().toString();
        chainNum = "0";
        entryCleared = false;
        currentOperation = "";
        result = 0.0;

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

            if (isSolved()) {
                binding.chainNumTv.setText("");

            } else if (currentText.length() > 0) {
                currentText = currentText.substring(0, currentText.length() - 1);

                if (currentText.length() > 0) {
                    binding.inputNumTv.setText(currentText);
                    inputNum = currentText;
                } else {
                    resetInput();
                }
            }
        });

        // addition
        binding.buttonAdd.setOnClickListener(v -> updateNumber("+", inputNum));

        // subtraction
        binding.buttonSubtract.setOnClickListener(v -> updateNumber("-", inputNum));

        // multiplication
        binding.buttonMultiply.setOnClickListener(v -> updateNumber("*", inputNum));

        // division
        binding.buttonDivide.setOnClickListener(v -> updateNumber("/", inputNum));

        // decimal point
        binding.buttonDecimalPoint.setOnClickListener(v -> {
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
                            binding.chainNumTv.setText("Error");
                            chainNum = "0";
                            resetInput();
                        } else {
                            calculate(chainNum, inputNum,"/");
                        }
                        break;
                    case "":
                        binding.chainNumTv.setText(inputNum + " =");
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

    @SuppressLint("SetTextI18n")
    private void calculate(String num1, String num2, String sign) {
        int resultLength = binding.inputNumTv.getText().toString().length();

        if (isValidNumber(inputNum) && (resultLength < 10)) {
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
            binding.chainNumTv.setText(chainNum + " " + sign + " " + inputNum + " =");
            binding.inputNumTv.setText(decimalFormat.format(result));
        }

        if (isSolved()) {
            chainNum = decimalFormat.format(result);
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateNumber(String operation, String inputNum) {
        if (isValidNumber(inputNum)) {
            currentOperation = operation;
            chainNum = binding.inputNumTv.getText().toString();
            binding.chainNumTv.setText(chainNum + " " + operation);
            resetInput();
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