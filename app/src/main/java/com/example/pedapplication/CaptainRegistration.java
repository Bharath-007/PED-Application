package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CaptainRegistration extends AppCompatActivity {

    ArrayAdapter adapter;
    Button registerbtn;
    AutoCompleteTextView gamesbox;
    TextInputEditText ccode;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_registration);
        gamesbox = findViewById(R.id.games);
        ccode = findViewById(R.id.captaincode);
        registerbtn = findViewById(R.id.registerbtn);

        adapter =ArrayAdapter.createFromResource(this,R.array.games, android.R.layout.simple_list_item_1);
        gamesbox.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = gamesbox.getText().toString();
                String code = ccode.getText().toString();
                HashMap<String,String> hm = new HashMap<>();
                hm.put("game",game);

                putDetails(hm);
            }
        });







    }

    private void putDetails(HashMap<String,String> hm) {
        DocumentReference documentReference = firestore.collection("captains").document("games");
        documentReference.set(hm);
    }
}