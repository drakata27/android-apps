package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    String resultText;
    String solutionText;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resultText = binding.resultTv.getText().toString();
        solutionText = binding.solutionTv.getText().toString();

        binding.buttonAc.setOnClickListener(v -> {
            binding.solutionTv.setText("");
            binding.resultTv.setText("0");
        });

        binding.buttonAdd.setOnClickListener(v -> {
            if (!resultText.equals("0")) {
                solutionText = resultText;
                resultText = "0";

                int sum = 0;
                sum = Integer.parseInt(solutionText) + Integer.parseInt(resultText);
                Log.d("Sum", "Sum " + sum);


                binding.solutionTv.setText(solutionText);
                binding.resultTv.setText(""+sum);
            }
        });
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();


        if (resultText.equals("0")) {
            resultText = buttonText;
        } else {
            resultText += buttonText;
        }
        binding.resultTv.setText(resultText);
    }
}