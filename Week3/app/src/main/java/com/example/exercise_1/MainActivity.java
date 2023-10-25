package com.example.exercise_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EditText name = findViewById(R.id.name);
        EditText surname = findViewById(R.id.surname);
        Button submitBtn = findViewById(R.id.submit_btn);
        TextView showName = findViewById(R.id.show_name);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_custom,
                        null); // if you give an id to your layout, the null value can be replaced with the id of the layout

                TextView text = (TextView) layout.findViewById(R.id.toastMessage); //this is the textView inside toast_custom.xml

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 500);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                // End toast

                if (name.getText().toString().equals("") || surname.getText().toString().equals("")) {
                    toast.show();
                    text.setText("Please enter your full name!");
                } else {
                    showName.setText("Your name is " + name.getText().toString() + " " + surname.getText().toString() + "!");
                }
            }
        });

    }
}