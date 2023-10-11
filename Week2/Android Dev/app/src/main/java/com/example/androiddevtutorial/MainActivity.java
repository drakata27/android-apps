package com.example.androiddevtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditText and TextView by their IDs
        nameInput = findViewById(R.id.nameInput);
        displayText = findViewById(R.id.displayText);

        // Find the "Submit" button by its ID
        Button submitBtn = findViewById(R.id.submitBtn);
        Button clearBtn = findViewById(R.id.clearBtn);

        // Set an OnClickListener for the "Submit" button
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Read the text input from the EditText
                String name = nameInput.getText().toString();

                // Update the TextView with the input text
                if(!nameInput.getText().toString().isEmpty()) {
                    displayText.setText("Hello, " + name + "!");
                } else {
                    displayText.setText("Please enter your name");
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayText.setText("Your name");
                nameInput.setText("");
            }
        });
    }
}