package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;
    FirebaseFirestore firestore;
    AutoCompleteTextView game;
    TextInputEditText code;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    String code2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();
        code = findViewById(R.id.logincode);
        loginbtn = findViewById(R.id.loginbtn);
        game = findViewById(R.id.gameslogin);
        getcode();

        EventChangeListener();
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        game.setAdapter(adapter);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameName = game.getText().toString();
                String code1 = code.getText().toString();

                if(gameName.isEmpty()){
                    game.setError("Please select a game");
                    game.requestFocus();
                    return;
                }
                if(code1.isEmpty()){
                    code.setError("This field should not be empty");
                    code.requestFocus();
                    return;
                }

                if(!code1.equals(code2)){
                    code.setError("Wrong code. Try again");
                    code.requestFocus();
                    return;
                }
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });


    }
    private void EventChangeListener() {
        firestore
        .collection("captains").orderBy("game", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                arrayList.add(dc.getDocument().get("game").toString());

                            }



                        }



                    }
                });
    }
    private void getcode() {
        DocumentReference documentReference = firestore.collection("ped").document("code");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                if(snapshot.exists()){
                    code2 = snapshot.getString("code");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Some unexpected error occured", Toast.LENGTH_SHORT).show();
            }
        });
        System.out.println(code2);


    }
}