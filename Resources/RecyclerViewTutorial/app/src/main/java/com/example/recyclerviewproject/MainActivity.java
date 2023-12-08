package com.example.recyclerviewproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels = new ArrayList<>();
    int[] dataModelImages = {R.drawable.baseline_airport_shuttle_24, R.drawable.baseline_alternate_email_24,
            R.drawable.baseline_animation_24, R.drawable.baseline_approval_24, R.drawable.baseline_assistant_direction_24,
            R.drawable.baseline_attach_money_24, R.drawable.baseline_baby_changing_station_24, R.drawable.baseline_cloud_done_24,
            R.drawable.baseline_create_new_folder_24, R.drawable.sharp_android_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setDataModels();

        DataModelRecyclerViewAdapter adapter = new DataModelRecyclerViewAdapter(this, dataModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDataModels() {
        String[] dataModelsHeadings = getResources().getStringArray(R.array.array1);
        String[] dataModelsText1 = getResources().getStringArray(R.array.array2);
        String[] dataModelsText2 = getResources().getStringArray(R.array.array3);

        for (int i = 0; i < dataModelsHeadings.length; i++) {
            dataModels.add(new DataModel(dataModelsHeadings[i],
                    dataModelsText1[i],
                    dataModelsText2[i],
                    dataModelImages[i]));
        }
    }
}