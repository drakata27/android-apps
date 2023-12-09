package com.example.firebase_crud_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {
    EditText name, course, surl, email;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.txtName);
        course = (EditText) findViewById(R.id.txtCourse);
        email = (EditText) findViewById(R.id.txtEmail);
        surl = (EditText) findViewById(R.id.txtImageUrl);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(v -> insertData());

        btnBack.setOnClickListener(v -> finish());
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("course", course.getText().toString());
        map.put("email", email.getText().toString());
        map.put("surl", surl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("students").push()
                .setValue(map)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(AddActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddActivity.this, "Error while adding", Toast.LENGTH_SHORT).show());
    }

//private void insertData() {
//    String nameValue = name.getText().toString();
//    String courseValue = course.getText().toString();
//    String emailValue = email.getText().toString();
//    String surlValue = surl.getText().toString();
//
//    if (nameValue.isEmpty() || courseValue.isEmpty() || emailValue.isEmpty() || surlValue.isEmpty()) {
//        Toast.makeText(AddActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        return;
//    }
//
//    Map<String, Object> map = new HashMap<>();
//    map.put("name", nameValue);
//    map.put("course", courseValue);
//    map.put("email", emailValue);
//    map.put("surl", surlValue);
//
//    FirebaseDatabase.getInstance().getReference().child("students").push()
//            .setValue(map)
//            .addOnSuccessListener(unused -> {
//                Toast.makeText(AddActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
//                finish();
//            })
//            .addOnFailureListener(e -> {
//                Toast.makeText(AddActivity.this, "Error while adding: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                e.printStackTrace(); // Log the exception for debugging
//            });
//    }
}