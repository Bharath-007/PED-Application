package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Future;

public class CaptainRegistration extends AppCompatActivity {

    String code;
    ArrayAdapter adapter;
    Button registerbtn;
    AutoCompleteTextView gamesbox;
    TextInputEditText ccode;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_registration);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String data = prefs.getString("message","no_id");



        gamesbox = findViewById(R.id.games);
        ccode = findViewById(R.id.captaincode);
        registerbtn = findViewById(R.id.registerbtn);

        adapter =ArrayAdapter.createFromResource(this,R.array.games, android.R.layout.simple_list_item_1);
        gamesbox.setAdapter(adapter);

        firestore = FirebaseFirestore.getInstance();
        getcode();

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String game = gamesbox.getText().toString();
                String code1 = ccode.getText().toString();
                HashMap<String,String> hm = new HashMap<>();
                hm.put("game",game);
                if(game.isEmpty()){
                    gamesbox.setError("Please select a game");
                    gamesbox.requestFocus();
                    return;
                }
                if(code1.isEmpty()){
                    ccode.setError("This field should not be empty");
                    ccode.requestFocus();
                    return;
                }

                if(!code1.equals(code)){
                    ccode.setError("Wrong code. Try again");
                    ccode.requestFocus();
                    return;
                }

                putDetails(hm);
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Login now", Toast.LENGTH_SHORT).show();
            }
        });







    }

    private void getcode() {
        DocumentReference documentReference = firestore.collection("ped").document("code");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                if(snapshot.exists()){
                    code = snapshot.getString("code");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Some unexpected error occured", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(code);


    }

    private void putDetails(HashMap<String,String> hm) {
        DocumentReference documentReference = firestore.collection("captains").document(hm.get("game"));
        documentReference.set(hm);
    }

}
