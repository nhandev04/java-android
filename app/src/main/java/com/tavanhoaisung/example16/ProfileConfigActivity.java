package com.tavanhoaisung.example16;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileConfigActivity extends AppCompatActivity {

    private ImageView backButton;
    private EditText nameEditText, emailEditText, phoneEditText;
    private Button saveButton, changePasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_config);

        // Initialize views
        initViews();
        
        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        saveButton = findViewById(R.id.save_button);
        changePasswordButton = findViewById(R.id.change_password_button);
        
        // Set default values
        nameEditText.setText("Tạ Văn Hoài Sung");
        emailEditText.setText("Hoaisung113@gmail.com");
        phoneEditText.setText("+84 123 456 789");
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
        
        // Save button click listener
        saveButton.setOnClickListener(v -> {
            // Show toast message for save functionality
            showToast("Profile information saved");
            // In a real app, you would save the profile information here
            // For now, just finish the activity
            finish();
        });
        
        // Change password button click listener
        changePasswordButton.setOnClickListener(v -> {
            // Show toast message for change password functionality
            showToast("Change password functionality");
            // In a real app, you would navigate to a change password screen
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