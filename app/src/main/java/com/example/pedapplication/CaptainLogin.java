package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class CaptainLogin extends AppCompatActivity {

    String code;
    ArrayAdapter adapter;
    Button registerbtn;
    AutoCompleteTextView gamesbox;
    TextInputEditText ccode;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.captainlogin);





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
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("message", game); //InputString: from the EditText
                editor.commit();
                HashMap<String,String> hm = new HashMap<>();
                hm.put("game",game);
                
                if(game.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Select a game to proceed", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(code1.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter your code to proceed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!code1.equals(code)){
                    Toast.makeText(getApplicationContext(), "Wrong code, try again.", Toast.LENGTH_SHORT).show();
                    return;
                }

                putDetails(hm);
                startActivity(new Intent(getApplicationContext(), Captain_Calendar.class));
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
