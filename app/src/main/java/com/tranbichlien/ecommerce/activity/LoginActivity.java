package com.tranbichlien.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tranbichlien.ecommerce.R;
import com.tranbichlien.ecommerce.api.ApiClient;
import com.tranbichlien.ecommerce.api.model.ApiResponse;
import com.tranbichlien.ecommerce.api.model.LoginRequest;
import com.tranbichlien.ecommerce.api.model.LoginResponse;
import com.tranbichlien.ecommerce.entity.User;
import com.tranbichlien.ecommerce.util.StorageUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity for user login
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        progressBarLogin = findViewById(R.id.progressBarLogin);

        // Check if user is already logged in
        if (StorageUtils.isLoggedIn(this)) {
            navigateToMainActivity();
            return;
        }

        // Set click listener for login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    /**
     * Attempt to log in the user with the provided credentials
     */
    private void loginUser() {
        // Get email and password from input fields
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate input
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // Show progress bar
        progressBarLogin.setVisibility(View.VISIBLE);
        buttonLogin.setEnabled(false);

        // Create login request
        LoginRequest loginRequest = new LoginRequest(email, password); // Make API call
        ApiClient.getAuthApiService().login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // Hide progress bar
                progressBarLogin.setVisibility(View.GONE);
                buttonLogin.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.getToken() != null) {
                        // Get JWT token
                        String token = loginResponse.getToken();

                        // Save token to SharedPreferences
                        StorageUtils.saveAuthToken(LoginActivity.this, token);

                        // Set logged in state
                        StorageUtils.setLoggedIn(LoginActivity.this, true);

                        // Get current user information
                        getCurrentUser(token);
                    } else {
                        // Show error message
                        showToast("Login failed: Token is missing");
                    }
                } else {
                    // Show error message
                    showToast("Login failed: " + (response.message() != null ? response.message() : "Unknown error"));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Hide progress bar
                progressBarLogin.setVisibility(View.GONE);
                buttonLogin.setEnabled(true);

                // Show error message
                showToast("Login failed: " + t.getMessage());
            }
        });
    }

    /**
     * Get current user information using the JWT token
     * 
     * @param token The JWT token
     */
    private void getCurrentUser(String token) {
        // Show progress bar
        progressBarLogin.setVisibility(View.VISIBLE);

        // Format token for Authorization header
        String authHeader = "Bearer " + token;

        // Make API call
        ApiClient.getAuthApiService().getCurrentUser(authHeader).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // Hide progress bar
                progressBarLogin.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();

                    // Save user data to SharedPreferences
                    StorageUtils.saveUserData(LoginActivity.this, user);

                    // Navigate to main activity
                    navigateToMainActivity();
                } else {
                    // Show error message
                    showToast("Failed to get user information: "
                            + (response.message() != null ? response.message() : "Unknown error"));

                    // Clear auth data
                    StorageUtils.clearAuthData(LoginActivity.this);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Hide progress bar
                progressBarLogin.setVisibility(View.GONE);

                // Show error message
                showToast("Failed to get user information: " + t.getMessage());

                // Clear auth data
                StorageUtils.clearAuthData(LoginActivity.this);
            }
        });
    }

    /**
     * Navigate to the main activity
     */
    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    /**
     * Show a toast message
     * 
     * @param message The message to show
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}