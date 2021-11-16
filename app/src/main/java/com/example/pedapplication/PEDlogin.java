package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PEDlogin extends AppCompatActivity {
    Button loginbtn;
    FirebaseFirestore firestore;
    AutoCompleteTextView game;
    TextInputEditText code;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    String code2;
    TextView gotoRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedlogin);


        gotoRegister = findViewById(R.id.regiterbtn2);
        firestore = FirebaseFirestore.getInstance();
        code = findViewById(R.id.pedcode);
        loginbtn = findViewById(R.id.pedLoginBtn);
        game = findViewById(R.id.staffID);
        getcode();

//        EventChangeListener();
//        arrayList = new ArrayList<>();
//        arrayList.add("P.E.D");
//
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        game.setAdapter(adapter);


        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String gameName = game.getText().toString();
                String code1 = code.getText().toString();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("message", gameName); //InputString: from the EditText
                editor.commit();

                if (gameName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select a game", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (gameName.equals("P.E.D")) {
                    if (code1.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "This field should not be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!code1.equals("88833")) {
                        Toast.makeText(getApplicationContext(), "Wrong code. Try again", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(new Intent(getApplicationContext(), PedCalender.class));
                    return;
                }
                if (code1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "This field should not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!code1.equals(code2)) {
                    Toast.makeText(getApplicationContext(), "Wrong code. Try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(getApplicationContext(), PedCalender.class));

            }
        });


        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PEDRegistration.class);
                startActivity(intent);
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

                        //arrayList.add(dc.getDocument().get("game").toString());

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
                if (snapshot.exists()) {
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
