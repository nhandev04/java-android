package com.tranbichlien.finalproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CouponsActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView couponsRecyclerView;
    private TextView emptyCouponsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        // Initialize views
        initViews();

        // Set click listeners
        setupClickListeners();

        // For demo purposes, show a toast message
        showToast("Coupons screen loaded");

        // In a real app, you would load the coupons from a database or API
        // For now, we'll just show a message that there are no coupons
        emptyCouponsText.setVisibility(android.view.View.VISIBLE);
        couponsRecyclerView.setVisibility(android.view.View.GONE);
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        couponsRecyclerView = findViewById(R.id.coupons_recycler_view);
        emptyCouponsText = findViewById(R.id.empty_coupons_text);

        // Set up RecyclerView
        couponsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // In a real app, you would set an adapter with actual coupon data
        // For now, we'll just leave it empty
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