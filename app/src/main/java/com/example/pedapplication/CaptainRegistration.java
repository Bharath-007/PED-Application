package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class CaptainRegistration extends AppCompatActivity {

    ArrayAdapter adapter;
    AutoCompleteTextView gamesbox;
    ArrayList<String> gamesarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_registration);
        gamesbox = findViewById(R.id.games);
        gamesarray = new ArrayList<>();
        gamesarray.add("Football");
        gamesarray.add("Volley Ball");
        gamesarray.add("BasketBall");
        gamesarray.add("Hockey");
        gamesarray.add("Badminton");
        gamesarray.add("Kabaddi");
        gamesarray.add("Athletics");


        adapter =ArrayAdapter.createFromResource(this,gamesarray, android.R.layout.simple_list_item_1);


    }
}