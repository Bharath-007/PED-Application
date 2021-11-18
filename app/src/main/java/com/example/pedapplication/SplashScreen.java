package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation fadeIn;
    ImageView logo;
    TextView drngpit,ped,sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        logo = findViewById(R.id.logo);
        drngpit = findViewById(R.id.drngpit);
        ped = findViewById(R.id.ped);
        sp = findViewById(R.id.sp);

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        logo.setAnimation(fadeIn);
        drngpit.setAnimation(fadeIn);
        ped.setAnimation(fadeIn);
        sp.setAnimation(fadeIn);
        new Handler().postDelayed(() -> {
            Intent intent= new Intent(SplashScreen.this, RegisterIntro.class);
//                startActivity(intent);
            startActivity(intent);


            finish();
        },3000);
    }
}