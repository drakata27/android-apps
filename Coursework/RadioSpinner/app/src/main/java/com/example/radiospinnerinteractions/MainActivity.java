package com.example.radiospinnerinteractions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import com.example.radiospinnerinteractions.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<ClubItem> clubsList = new ArrayList<>();

    private ClubAdapter clubAdapter;

    String [] newClubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        clubsList.add("No CLub Selected");
        clubsList.add(new ClubItem("No CLub Selected"));

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, clubsList);

//        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//        binding.spinner.setAdapter(adapter);

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
//            adapter.notifyDataSetChanged();
            clubAdapter.notifyDataSetChanged();
        });

        binding.submitBtn.setOnClickListener(v -> {
//            try {
//                String selectedItem = (String) binding.spinner.getSelectedItem();
//                RadioButton selectedLeagueBtn = findViewById(binding.radioGroup.getCheckedRadioButtonId());
//                String selectedLeagueTxt = selectedLeagueBtn.getText().toString();
//                binding.favLeague.setText("Favourite league: " + selectedLeagueTxt);
//                binding.favClub.setText("Favourite club: " + selectedItem);
//            } catch (Exception e) {
//                binding.favLeague.setText("Please select an option from above");
//            }

//            ClubItem selectedItem = (ClubItem) binding.spinner.getSelectedItem();
//            if (selectedItem != null) {
//                RadioButton selectedLeagueBtn = findViewById(binding.radioGroup.getCheckedRadioButtonId());
//                String selectedLeagueTxt = selectedLeagueBtn.getText().toString();
//                binding.favLeague.setText("Favorite league: " + selectedLeagueTxt);
//                binding.favClub.setText("Favorite club: " + selectedItem.getClubName());
//            } else {
//                binding.favLeague.setText("Please select an option from above");
//            }
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
//            clubsList.add("No CLub Selected");
            clubsList.add(new ClubItem("No CLub Selected"));
            binding.favLeague.setText("");
            binding.favClub.setText("");
//            adapter.notifyDataSetChanged();
            clubAdapter.notifyDataSetChanged();
        });
    }

    public void addClubs(String [] clubs) {
        for (String club : clubs) {
//            clubsList.add(club);
            clubsList.add(new ClubItem(club));
        }
        binding.spinner.setSelection(0);
    }
}