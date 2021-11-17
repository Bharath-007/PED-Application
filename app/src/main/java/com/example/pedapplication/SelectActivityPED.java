package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SelectActivityPED extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> arrayList;
    PedAdapterForSelectingList myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ped);

        recyclerView = findViewById(R.id.Rviewselectlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        arrayList.add("Present List");
        arrayList.add("Absentees List");
        arrayList.add("OD List");
        arrayList.add("Rest List");


        myAdapter = new PedAdapterForSelectingList(SelectActivityPED.this,arrayList);
        recyclerView.setAdapter(myAdapter);

    }
}