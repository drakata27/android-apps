package com.example.radiospinnerinteractions;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import com.example.radiospinnerinteractions.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final ArrayList<ClubItem> clubsList = new ArrayList<>(); //check if it is final

    private ClubAdapter clubAdapter;

    String [] newClubs;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clubsList.add(new ClubItem("No CLub Selected"));

        clubAdapter = new ClubAdapter(this, clubsList);
        binding.spinner.setAdapter(clubAdapter);

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            clubsList.clear();
            if (checkedId == R.id.epl) {
                newClubs = new String[]{"Arsenal", "Chelsea", "Liverpool", "Man City", "Man United", "Spurs"};
                addClubs(newClubs);
            } else if (checkedId == R.id.laLiga) {
                newClubs = new String[]{"Athletic Bilbao", "Atletico Madrid", "Barcelona", "Real Madrid", "Sevilla", "Villareal"};
                addClubs(newClubs);
            } else if (checkedId == R.id.serieA) {
                newClubs = new String[]{"AC Milan", "Inter Milan", "Juventus", "Lazio", "Napoli", "Roma"};
                addClubs(newClubs);
            } else if (checkedId == R.id.bundesliga) {
                newClubs = new String[]{"Bayer Leverkusen", "Bayern Munich", "Borussia Dortmund", "Eintracht Frankfurt", "RB Leipzig", "Wolfsburg"};
                addClubs(newClubs);
            }
            clubAdapter.notifyDataSetChanged();
        });

        binding.submitBtn.setOnClickListener(v -> {
            try {
                ClubItem selectedItem = (ClubItem) binding.spinner.getSelectedItem();
                RadioButton selectedLeagueBtn = findViewById(binding.radioGroup.getCheckedRadioButtonId());
                String selectedLeagueTxt = selectedLeagueBtn.getText().toString();
                binding.favLeague.setText("Favorite league: " + selectedLeagueTxt);
                binding.favClub.setText("Favorite club: " + selectedItem.getClubName());
            } catch (Exception e) {
                binding.favLeague.setText("Please select an option from above");
            }
        });

        binding.clearBtn.setOnClickListener(v -> {
            clubsList.clear();
            binding.radioGroup.clearCheck();
            clubsList.add(new ClubItem("No CLub Selected"));
            binding.favLeague.setText("");
            binding.favClub.setText("");
            clubAdapter.notifyDataSetChanged();
        });
    }

    public void addClubs(String [] clubs) {
        for (String club : clubs) {
            clubsList.add(new ClubItem(club));
        }
        binding.spinner.setSelection(0);
    }
}