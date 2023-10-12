package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView num = findViewById(R.id.num);
        Button increase = findViewById(R.id.increase);
        Button decrease = findViewById(R.id.decrease);
        Button clear = findViewById(R.id.clear);

        // Increase
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(num.getText().toString());
                count++;
                num.setText(""+count);
            }
        });

        // Decrease
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(num.getText().toString());
                count--;
                num.setText(""+count);
            }
        });

        // Clear
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText("0");
            }
        });

    }
}