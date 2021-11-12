package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class CaptainRegistration extends AppCompatActivity {

    ArrayAdapter adapter;
    AutoCompleteTextView gamesbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_registration);
        gamesbox = findViewById(R.id.games);

        adapter =ArrayAdapter.createFromResource(this,R.array.games, android.R.layout.simple_list_item_1);
        gamesbox.setAdapter(adapter);


    }
}