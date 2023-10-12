package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText num1 = findViewById(R.id.num1);
        EditText num2 = findViewById(R.id.num2);

        Button add = findViewById(R.id.add);
        Button sub = findViewById(R.id.sub);
        Button mult = findViewById(R.id.mult);
        Button div = findViewById(R.id.div);
        Button clear = findViewById(R.id.clear);

        TextView res = findViewById(R.id.res);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1.setText("");
                num2.setText("");
            }
        });

        // Addition
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String num1Str = num1.getText().toString();
                    String num2Str = num2.getText().toString();

                    int n1 = Integer.parseInt(num1Str);
                    int n2 = Integer.parseInt(num2Str);
                    int result = n1 + n2;
                    res.setText("" + result);
                } catch (NumberFormatException e) {
                    res.setText("Make sure you entered a valid number");
                }

            }
        });

        // Subtraction
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String num1Str = num1.getText().toString();
                    String num2Str = num2.getText().toString();

                    int n1 = Integer.parseInt(num1Str);
                    int n2 = Integer.parseInt(num2Str);
                    int result = n1 - n2;
                    res.setText("" + result);
                } catch (NumberFormatException e) {
                    res.setText("Make sure you entered a valid number");
                }

            }
        });

        // Multiplication
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String num1Str = num1.getText().toString();
                    String num2Str = num2.getText().toString();

                    int n1 = Integer.parseInt(num1Str);
                    int n2 = Integer.parseInt(num2Str);
                    int result = n1 * n2;
                    res.setText("" + result);
                } catch (NumberFormatException e) {
                    res.setText("Make sure you entered a valid number");
                }

            }
        });

        // Division
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String num1Str = num1.getText().toString();
                    String num2Str = num2.getText().toString();

                    int n1 = Integer.parseInt(num1Str);
                    int n2 = Integer.parseInt(num2Str);

                    if (n2 == 0) {
                        res.setText("You cannot divide by 0!");
                    } else {
                        int result = n1 / n2;
                        res.setText("" + result);
                    }
                } catch (NumberFormatException e) {
                    res.setText("Make sure you entered a valid number");
                }
            }
        });
    }
}