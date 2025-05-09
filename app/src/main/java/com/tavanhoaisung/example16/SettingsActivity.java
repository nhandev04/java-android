package com.tavanhoaisung.example16;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private ImageView backButton;
    private SwitchCompat notificationsSwitch, darkModeSwitch;
    private Button logoutButton, deleteAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        initViews();
        
        // Set click listeners
        setupClickListeners();
        
        // For demo purposes, show a toast message
        showToast("Settings screen loaded");
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        notificationsSwitch = findViewById(R.id.notifications_switch);
        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        logoutButton = findViewById(R.id.logout_button);
        deleteAccountButton = findViewById(R.id.delete_account_button);
        
        // Set default values
        notificationsSwitch.setChecked(true);
        darkModeSwitch.setChecked(false);
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
        
        // Notifications switch listener
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Show toast message for notifications setting
            showToast("Notifications " + (isChecked ? "enabled" : "disabled"));
            // In a real app, you would save this setting to SharedPreferences
        });
        
        // Dark mode switch listener
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Show toast message for dark mode setting
            showToast("Dark mode " + (isChecked ? "enabled" : "disabled"));
            // In a real app, you would apply the theme change
        });
        
        // Logout button click listener
        logoutButton.setOnClickListener(v -> {
            // Show toast message for logout functionality
            showToast("Logout functionality");
            // In a real app, you would clear user session and navigate to login screen
        });
        
        // Delete account button click listener
        deleteAccountButton.setOnClickListener(v -> {
            // Show toast message for delete account functionality
            showToast("Delete account functionality");
            // In a real app, you would show a confirmation dialog and then delete the account
        });
    }
    
    /**
     * Helper method to show toast messages
     * @param message The message to display
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}