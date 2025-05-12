package com.tranbichlien.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.tranbichlien.ecommerce.util.StorageUtils;

import com.tranbichlien.ecommerce.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageView backButton;
    private SwitchCompat notificationsSwitch, darkModeSwitch;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        initViews();

        // Set click listeners
        setupClickListeners();
        showToast("Màn hình cài đặt đã tải");
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        notificationsSwitch = findViewById(R.id.notifications_switch);
        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        logoutButton = findViewById(R.id.logout_button);

        // Set switch states based on saved preferences
        notificationsSwitch.setChecked(StorageUtils.areNotificationsEnabled(this));
        darkModeSwitch.setChecked(StorageUtils.isDarkModeEnabled(this));
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
        // Notifications switch listener
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save notification preference to SharedPreferences
            StorageUtils.setNotificationsEnabled(this, isChecked);

            // Show toast message for notifications setting
            if (isChecked) {
                showToast("Thông báo voucher đã bật");
            } else {
                showToast("Đã tắt tất cả thông báo");
            }
        }); // Dark mode switch listener
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save dark mode preference to SharedPreferences
            StorageUtils.setDarkModeEnabled(this, isChecked);

            // Apply the theme change
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Show toast message for dark mode setting
            showToast("Chế độ tối " + (isChecked ? "đã bật" : "đã tắt"));

            // Recreate the activity to apply theme changes immediately
            recreate();
        });

        // Logout button click listener
        logoutButton.setOnClickListener(v -> {
            // Clear auth data
            StorageUtils.clearAuthData(this);

            // Show toast message for logout
            showToast("Đăng xuất thành công");

            // Navigate to login screen
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Helper method to show toast messages
     * 
     * @param message The message to display
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
