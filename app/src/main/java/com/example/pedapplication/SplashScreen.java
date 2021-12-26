package com.example.pedapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation fadeIn,topToBottom,BottomToTop;
    ImageView centreLogo,splPed,sportIcon,sportCommunity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Window window = SplashScreen.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SplashScreen.this, R.color.blue));
        centreLogo = findViewById(R.id.splLogo);
        splPed = findViewById(R.id.splPed);
        sportIcon = findViewById(R.id.sportIcon);
        sportCommunity = findViewById(R.id.sportCommunity);

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        BottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        centreLogo.setAnimation(fadeIn);
        splPed.setAnimation(topToBottom);
        sportIcon.setAnimation(BottomToTop);
        sportCommunity.setAnimation(BottomToTop);

        new Handler().postDelayed(() -> {
            Intent intent= new Intent(SplashScreen.this, RegisterIntro.class);
            startActivity(intent);


            finish();
        },6000);
    }
}