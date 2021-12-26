package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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
        Window window = SelectActivityPED.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SelectActivityPED.this, R.color.blue));

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