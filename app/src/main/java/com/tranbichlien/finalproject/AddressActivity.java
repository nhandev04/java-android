package com.tranbichlien.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddressActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView addressesRecyclerView;
    private TextView emptyAddressesText;
    private Button addAddressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        // Initialize views
        initViews();

        // Set click listeners
        setupClickListeners();

        // For demo purposes, show a toast message
        showToast("Address screen loaded");

        // In a real app, you would load the addresses from a database or API
        // For now, we'll just show a message that there are no addresses
        emptyAddressesText.setVisibility(android.view.View.VISIBLE);
        addressesRecyclerView.setVisibility(android.view.View.GONE);
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        addressesRecyclerView = findViewById(R.id.addresses_recycler_view);
        emptyAddressesText = findViewById(R.id.empty_addresses_text);
        addAddressButton = findViewById(R.id.add_address_button);

        // Set up RecyclerView
        addressesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // In a real app, you would set an adapter with actual address data
        // For now, we'll just leave it empty
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());

        // Add address button click listener
        addAddressButton.setOnClickListener(v -> {
            // Show toast message for add address functionality
            showToast("Add new address functionality");
            // In a real app, you would navigate to an add address screen or show a dialog
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