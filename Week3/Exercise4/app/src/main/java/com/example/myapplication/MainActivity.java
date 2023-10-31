package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

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

        Map<Button, String> buttonColorMap = new HashMap<>();

        buttonColorMap.put(btn1, "#FF8333");
        buttonColorMap.put(btn2, "#2521FF"); // text white
        buttonColorMap.put(btn3, "#685045"); // text white
        buttonColorMap.put(btn4, "#6EFF21");
        buttonColorMap.put(btn5, "#FF21F8"); // text white
        buttonColorMap.put(btn6, "#9033FF");

        btn1.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn1));
            showToast("Button 1 was pressed!", c);
        });

        btn2.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn2));
            showToast("Button 2 was pressed!", c);
        });

        btn3.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn3));
            showToast("Button 3 was pressed!", c);
        });

        btn4.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn4));
            showToast("Button 4 was pressed!", c);
        });

        btn5.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn5));
            showToast("Button 5 was pressed!", c);
        });

        btn6.setOnClickListener(v -> {
            int c = Color.parseColor(buttonColorMap.get(btn6));
            showToast("Button 6 was pressed!",c);
        });
    }
    
    private void showToast(String text, int color){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView txt = (TextView) layout.findViewById(R.id.toastMessage);

        txt.setTextColor(color);
        txt.setText(text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 500);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}