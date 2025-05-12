package com.tranbichlien.ecommerce.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tranbichlien.ecommerce.R;
import com.tranbichlien.ecommerce.entity.User;
import com.tranbichlien.ecommerce.util.StorageUtils;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileConfigActivity extends AppCompatActivity {

    private ImageView backButton;
    private EditText nameEditText, emailEditText, phoneEditText;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_config);

        // Initialize views
        initViews();

        // Load user data
        loadUserData();

        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);

        // Make sure all edit texts are disabled
        nameEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        phoneEditText.setEnabled(false);
    }

    private void loadUserData() {
        // Get current user data from storage
        currentUser = StorageUtils.getUserData(this);

        if (currentUser != null) {
            // Set user data to UI
            String fullName = currentUser.getFullName();
            String email = currentUser.getEmail();

            nameEditText.setText(fullName);
            emailEditText.setText(email); // Show toast indicating feature is not available
            showToast("Chỉnh sửa hồ sơ không khả dụng trong phiên bản này");
        } else {
            // Set default values if user data is not available
            nameEditText.setText("");
            emailEditText.setText("");
            phoneEditText.setText("");

            showToast("Dữ liệu người dùng không có sẵn");
        }
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
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