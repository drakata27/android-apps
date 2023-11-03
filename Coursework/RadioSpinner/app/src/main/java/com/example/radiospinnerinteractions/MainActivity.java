package com.example.radiospinnerinteractions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.radiospinnerinteractions.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ArrayList<String> clubsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clubsList.add("No CLub Selected");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, clubsList);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        binding.spinner.setAdapter(adapter);

        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                clubsList.clear();
                if (checkedId == R.id.epl) {
                    String [] newClubs = {"Arsenal", "Chelsea", "Liverpool", "Man City", "Man United", "Spurs"};
                    addClubs(newClubs);
                } else if (checkedId == R.id.laLiga) {
                    String [] newClubs = {"Athletic Bilbao", "Atletico Madrid", "Barcelona", "Real Madrid", "Sevilla", "Villareal"};
                    addClubs(newClubs);
                } else if (checkedId == R.id.serieA) {
                    String [] newClubs = {"AC Milan", "Inter Milan", "Juventus", "Lazio", "Napoli", "Roma"};
                    addClubs(newClubs);
                } else if (checkedId == R.id.bundesliga) {
                    String [] newClubs = {"Bayer Leverkusen", "Bayern Munich", "Borussia Dortmund", "Eintracht Frankfurt", "RB Leipzig", "Wolfsburg"};
                    addClubs(newClubs);
                }
                adapter.notifyDataSetChanged();
            }
        });

        binding.submitBtn.setOnClickListener(v -> {
            String selectedItem = (String) binding.spinner.getSelectedItem();

            RadioButton selectedLeagueBtn = findViewById(binding.radioGroup.getCheckedRadioButtonId());
            String selectedLeagueTxt = selectedLeagueBtn.getText().toString();

            binding.favLeague.setText("Favourite league: " + selectedLeagueTxt);
            binding.favClub.setText("Favourite club: " + selectedItem);
        });
    }

    public void addClubs(String [] clubs) {
        for (int i = 0; i < clubs.length; i++) {
            clubsList.add(clubs[i]);
        }
        binding.spinner.setSelection(0);
    }
}