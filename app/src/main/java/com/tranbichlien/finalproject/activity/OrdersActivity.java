package com.tranbichlien.finalproject.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tranbichlien.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView ordersRecyclerView;
    private TextView emptyOrdersText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // Initialize views
        initViews();

        // Set click listeners
        setupClickListeners();

        // For demo purposes, show a toast message
        showToast("Orders screen loaded");

        // In a real app, you would load the orders from a database or API
        // For now, we'll just show a message that there are no orders
        emptyOrdersText.setVisibility(android.view.View.VISIBLE);
        ordersRecyclerView.setVisibility(android.view.View.GONE);
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        ordersRecyclerView = findViewById(R.id.orders_recycler_view);
        emptyOrdersText = findViewById(R.id.empty_orders_text);

        // Set up RecyclerView
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // In a real app, you would set an adapter with actual order data
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