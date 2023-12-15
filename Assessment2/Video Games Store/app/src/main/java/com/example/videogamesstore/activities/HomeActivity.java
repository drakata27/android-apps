//package com.example.videogamesstore.activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.SearchView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.example.videogamesstore.models.Games;
//import com.example.videogamesstore.adapters.MainAdapter;
//import com.example.videogamesstore.R;
//import com.example.videogamesstore.databinding.ActivityMainBinding;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class HomeActivity extends AppCompatActivity {
//
//    private ActivityMainBinding binding;
//    private MainAdapter mainAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<Games> options =
//                new FirebaseRecyclerOptions.Builder<Games>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("videogames"), Games.class)
//                        .build();
//
//        mainAdapter = new MainAdapter(options);
//        binding.recyclerView.setAdapter(mainAdapter);
//
//        binding.floatingActionButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CartActivity.class)));
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mainAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mainAdapter.stopListening();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        assert searchView != null;
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                txtSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//                txtSearch(query);
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void txtSearch(String str) {
//        FirebaseRecyclerOptions<Games> options =
//                new FirebaseRecyclerOptions.Builder<Games>()
//                        .setQuery(FirebaseDatabase
//                                .getInstance()
//                                .getReference().
//                                child("videogames").
//                                orderByChild("name").
//                                startAt(str)
//                                .endAt(str+"~"), Games.class).build();
//
//        mainAdapter = new MainAdapter(options);
//        mainAdapter.startListening();
//        binding.recyclerView.setAdapter(mainAdapter);
//    }
//}