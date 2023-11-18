package com.example.buttoninteractions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buttoninteractions.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // top left
        binding.topLeftBtn.setOnClickListener(v -> showToast("You clicked the Top Left button!", 0, -500));

        // top right
        binding.topRightBtn.setOnClickListener(v -> showToast("You clicked the Top Right button!", 0, -500));

        // center
        binding.centerBtn.setOnClickListener(v -> showToast("You clicked the Center button!", 0, 0));

        // bottom left
        binding.bottomLeftBtn.setOnClickListener(v -> showToast("You clicked the Bottom Left button!", 0, 500));

        // bottom right
        binding.bottomRightBtn.setOnClickListener(v -> showToast("You clicked the Bottom Right button!", 0, 500));
    }

    private void showToast(String toastText, int xOffset, int yOffset) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_custom, null);

            TextView text = (TextView) layout.findViewById(R.id.toastMessage);
            text.setText(toastText);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER_VERTICAL, xOffset, yOffset);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
    }
}