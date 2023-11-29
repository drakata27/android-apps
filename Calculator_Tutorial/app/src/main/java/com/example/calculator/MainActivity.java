package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.calculator.databinding.ActivityMainBinding;
import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    public void onClick(View view) {
        // init on click for every button
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = binding.solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            binding.solutionTv.setText("");
            binding.resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            binding.solutionTv.setText(binding.resultTv.getText());
            return;
        }

//        if (buttonText.equals("C") & dataToCalculate.length() >= 0) {
//            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
//        } else {
//        }
        dataToCalculate += buttonText;

        binding.solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Error")){
            binding.resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();

            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }

            double resultValue = Double.parseDouble(finalResult);
            DecimalFormat decimalFormat = new DecimalFormat("#.#######");
            finalResult = decimalFormat.format(resultValue);

            return finalResult;
        } catch (Exception e) {
            return "Error";
        }
    }
}