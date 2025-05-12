package com.tranbichlien.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.tranbichlien.ecommerce.R;
import com.tranbichlien.ecommerce.util.StorageUtils;
import com.tranbichlien.ecommerce.util.ThemeHelper;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply theme before setContentView
        ThemeHelper.applyThemeFromPrefs(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if user is already logged in
                if (StorageUtils.isLoggedIn(SplashScreenActivity.this)) {
                    // Navigate to MainActivity if logged in
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    // Navigate to LoginActivity if not logged in
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);
    }

}
