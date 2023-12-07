package com.example.itemscatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itemscatalogue.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        com.example.itemscatalogue.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = new Intent(MainActivity.this, DetailActivity.class);

        binding.arsenal.setOnClickListener(v -> passData(intent, R.drawable.arsenal, R.string.arsenal));

        binding.chelsea.setOnClickListener(v -> passData(intent, R.drawable.chelsea, R.string.chelsea));

        binding.lfc.setOnClickListener(v -> passData(intent, R.drawable.lfc, R.string.liverpool));

        binding.manCity.setOnClickListener(v -> passData(intent, R.drawable.man_city, R.string.man_city));

        binding.manUtd.setOnClickListener(v -> passData(intent, R.drawable.man_utd, R.string.man_utd));

        binding.spurs.setOnClickListener(v -> passData(intent, R.drawable.spurs, R.string.spurs));

    }
    @Override
    public void onClick(View v) {
        LinearLayout parentLayout = (LinearLayout) v.getParent();

        // Assuming the club name is the second TextView in the layout
        TextView clubNameTextView = (TextView) parentLayout.getChildAt(1);
        String clubName = clubNameTextView.getText().toString();

        switch (clubName) {
            case "Arsenal":
                passData(intent, R.drawable.arsenal, R.string.arsenal);
                break;
            case "Chelsea":
                passData(intent, R.drawable.chelsea, R.string.chelsea);
                break;
            case "Liverpool FC":
                passData(intent, R.drawable.lfc, R.string.liverpool);
                break;
            case "Man City":
                passData(intent, R.drawable.man_city, R.string.man_city);
                break;
            case "Man United":
                passData(intent, R.drawable.man_utd, R.string.man_utd);
                break;
            case "Tottenham Hotspur":
                passData(intent, R.drawable.spurs, R.string.spurs);
                break;
        }
    }
    public void passData(Intent intent, int img, int details) {
        intent.putExtra("key", details);
        intent.putExtra("imageRes", img);
        startActivity(intent);
    }
}