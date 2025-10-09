package com.example.citidemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.citidemo.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        ImageView ivLogo = findViewById(R.id.ivSplashLogo);
        TextView tvAppName = findViewById(R.id.tvSplashAppName);
        TextView tvTagline = findViewById(R.id.tvSplashTagline);

        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Apply animations
        ivLogo.startAnimation(fadeIn);
        tvAppName.startAnimation(slideUp);
        tvTagline.startAnimation(slideUp);

        // Navigate after delay
        new Handler().postDelayed(this::navigateToNextScreen, SPLASH_DURATION);
    }

    private void navigateToNextScreen() {
        // Create an Intent to go from SplashActivity to LoginActivity
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

        // Add smooth fade transition
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}