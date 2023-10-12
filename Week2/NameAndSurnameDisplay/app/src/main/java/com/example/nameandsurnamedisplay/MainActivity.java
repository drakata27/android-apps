package com.example.nameandsurnamedisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText surname;
    private EditText birthYear;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        birthYear = findViewById(R.id.birthYear);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                String surnameStr = surname.getText().toString();
                String birthYearStr = birthYear.getText().toString();
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int age = currentYear - Integer.parseInt(birthYearStr);

                if (age < 0) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Hello, "+ nameStr + " " + surnameStr + ". You are " + age + " years old",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}