package com.tranbichlien.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.tranbichlien.ecommerce.util.StorageUtils;
import com.tranbichlien.ecommerce.util.ThemeHelper;

import com.tranbichlien.ecommerce.R;

public class SettingsActivity extends AppCompatActivity {
    private ImageView backButton;
    private SwitchCompat notificationsSwitch;
    private RadioGroup themeModeGroup;
    private RadioButton themeModeSystem, themeModeLight, themeModeDark;
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
        logoutButton = findViewById(R.id.logout_button);

        // Initialize theme mode radio group
        themeModeGroup = findViewById(R.id.theme_mode_group);
        themeModeSystem = findViewById(R.id.theme_mode_system);
        themeModeLight = findViewById(R.id.theme_mode_light);
        themeModeDark = findViewById(R.id.theme_mode_dark);

        // Set switch states based on saved preferences
        notificationsSwitch.setChecked(StorageUtils.areNotificationsEnabled(this));

        // Set the correct radio button based on the theme mode
        int currentThemeMode = ThemeHelper.getThemeMode(this);
        switch (currentThemeMode) {
            case ThemeHelper.MODE_LIGHT:
                themeModeLight.setChecked(true);
                break;
            case ThemeHelper.MODE_DARK:
                themeModeDark.setChecked(true);
                break;
            case ThemeHelper.MODE_AUTO:
            default:
                themeModeSystem.setChecked(true);
                break;
        }
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish()); // Notifications switch listener
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save notification preference to SharedPreferences
            StorageUtils.setNotificationsEnabled(this, isChecked);

            // Show toast message for notifications setting
            if (isChecked) {
                showToast("Thông báo voucher đã bật");
            } else {
                showToast("Đã tắt tất cả thông báo");
            }
        });

        // Theme mode radio group listener
        themeModeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int themeMode;
            String themeName;
            if (checkedId == R.id.theme_mode_light) {
                themeMode = ThemeHelper.MODE_LIGHT;
                themeName = "Chế độ sáng";
                StorageUtils.setDarkModeEnabled(this, false);
            } else if (checkedId == R.id.theme_mode_dark) {
                themeMode = ThemeHelper.MODE_DARK;
                themeName = "Chế độ tối";
                StorageUtils.setDarkModeEnabled(this, true);
            } else {
                themeMode = ThemeHelper.MODE_AUTO;
                themeName = "Theo hệ thống";
            }

            // Apply the theme mode
            ThemeHelper.setThemeMode(this, themeMode);

            // Show toast message
            showToast("Đã chọn " + themeName);

            // Recreate activity to apply changes
            if (themeMode != ThemeHelper.MODE_AUTO) {
                recreate();
            }
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
