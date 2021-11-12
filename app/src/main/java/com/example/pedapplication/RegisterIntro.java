package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterIntro extends AppCompatActivity {
    Button pedbtn,captainbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_intro);

        pedbtn = findViewById(R.id.pedbtn);
        captainbtn = findViewById(R.id.captainbtn);

        captainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CaptainRegistration.class);
                startActivity(intent);
            }
        });
    }
}