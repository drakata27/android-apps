package com.example.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btn1 = findViewById(R.id.btn1);
       btn2 = findViewById(R.id.btn2);
       btn3 = findViewById(R.id.btn3);
       btn4 = findViewById(R.id.btn4);
       btn5 = findViewById(R.id.btn5);
       btn6 = findViewById(R.id.btn6);

        btn1.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 1 Pressed!", Toast.LENGTH_SHORT).show();
        });

        btn2.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 2 Pressed!", Toast.LENGTH_SHORT).show();
        });

        btn3.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 3 Pressed!", Toast.LENGTH_SHORT).show();
        });

        btn4.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 4 Pressed!", Toast.LENGTH_SHORT).show();
        });

        btn5.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 5 Pressed!", Toast.LENGTH_SHORT).show();
        });

        btn6.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Button 6 Pressed!", Toast.LENGTH_SHORT).show();
        });
    }
}