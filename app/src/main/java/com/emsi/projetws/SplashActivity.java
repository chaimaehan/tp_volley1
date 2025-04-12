package com.emsi.projetws;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 4000; // 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the status bar for a more immersive experience
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Get references to views
        ImageView splashIcon = findViewById(R.id.splashIcon);
        TextView splashText = findViewById(R.id.splashText);
        TextView splashSubText = findViewById(R.id.splashSubText);

        // Create animations
        // 1. Icon animation - scale up and fade in
        AnimationSet iconAnimSet = new AnimationSet(true);
        
        Animation scaleAnim = new ScaleAnimation(
                0.5f, 1.0f, 0.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        scaleAnim.setDuration(1000);
        scaleAnim.setInterpolator(new DecelerateInterpolator());
        
        Animation fadeInAnim = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnim.setDuration(1000);
        fadeInAnim.setInterpolator(new DecelerateInterpolator());
        
        iconAnimSet.addAnimation(scaleAnim);
        iconAnimSet.addAnimation(fadeInAnim);
        
        // 2. Text animations - fade in with delay
        Animation textFadeIn = new AlphaAnimation(0.0f, 1.0f);
        textFadeIn.setStartOffset(500);
        textFadeIn.setDuration(1000);
        
        Animation subTextFadeIn = new AlphaAnimation(0.0f, 1.0f);
        subTextFadeIn.setStartOffset(1000);
        subTextFadeIn.setDuration(1000);

        // Start animations
        splashIcon.startAnimation(iconAnimSet);
        splashText.startAnimation(textFadeIn);
        splashSubText.startAnimation(subTextFadeIn);

        // Navigate to main activity after delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ListEtudiantsActivity.class);
                startActivity(intent);
                
                // Add a smooth transition
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                
                // Close the splash activity
                finish();
            }
        }, SPLASH_DURATION);
    }
}
