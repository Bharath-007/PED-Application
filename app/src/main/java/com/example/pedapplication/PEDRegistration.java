package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PEDRegistration extends AppCompatActivity {

    Button registerbtn;
    TextInputEditText pedcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedregistration);

        registerbtn = findViewById(R.id.pedregisterbtn);
        pedcode = findViewById(R.id.captaincode);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = pedcode.getText().toString().trim();
                if(code.isEmpty()){
                    pedcode.setError("This field should not be empty");
                    pedcode.requestFocus();
                    return;
                }
                if(!code.equals("88833")){
                    pedcode.setError("Wrong code. Try again");
                    pedcode.requestFocus();
                    return;
                }

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                Toast.makeText(getApplicationContext(), "Login now", Toast.LENGTH_SHORT).show();
            }
        });
    }
}