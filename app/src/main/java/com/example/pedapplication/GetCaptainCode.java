package com.example.pedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class GetCaptainCode extends AppCompatActivity {
    TextInputEditText captaincode;
    FirebaseFirestore firestore;
    Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_captain_code);
        firestore = FirebaseFirestore.getInstance();

        captaincode = findViewById(R.id.captaincode);
        confirmBtn = findViewById(R.id.confirmbtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = captaincode.getText().toString().trim();
                if(code.isEmpty()){
                    captaincode.setError("This field cannot be empty");
                    captaincode.requestFocus();
                    return;
                }
                if(!code.matches("\\d{5}$")){
                    captaincode.setError("Enter only five digits!");
                    captaincode.requestFocus();
                    return;
                }
                putDetails(code);
            }
        });
    }

    private void putDetails(String code) {
        HashMap<String,String> hm = new HashMap<>();
        hm.put("code",code);

        DocumentReference documentReference = firestore.collection("ped").document("code");
        documentReference.set(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("message", code); //InputString: from the EditText
//                editor.commit();
                startActivity(new Intent(getApplicationContext(), PEDlogin.class));
                Toast.makeText(getApplicationContext(), "Login now", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),PEDlogin.class));
        finish();
    }
}