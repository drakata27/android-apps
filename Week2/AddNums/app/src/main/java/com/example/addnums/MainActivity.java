package com.example.addnums;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText num1;
    private EditText num2;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);

        Button sumBtn = findViewById(R.id.sumBtn);


        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1Str = num1.getText().toString();
                String num2Str = num2.getText().toString();

                try {
                    int n1 = Integer.parseInt(num1Str);
                    int n2 = Integer.parseInt(num2Str);

                    result.setText(""+(n1+n2));
                } catch (Exception e) {
                    result.setText("Invalid input!");
                }

            }
        });

    }
}