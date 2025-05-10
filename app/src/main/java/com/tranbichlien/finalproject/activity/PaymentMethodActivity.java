package com.tranbichlien.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentMethodActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView paymentMethodsRecyclerView;
    private TextView emptyPaymentMethodsText;
    private Button addPaymentMethodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Initialize views
        initViews();

        // Set click listeners
        setupClickListeners();

        // For demo purposes, show a toast message
        showToast("Payment Methods screen loaded");

        // In a real app, you would load the payment methods from a database or API
        // For now, we'll just show a message that there are no payment methods
        emptyPaymentMethodsText.setVisibility(android.view.View.VISIBLE);
        paymentMethodsRecyclerView.setVisibility(android.view.View.GONE);
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        paymentMethodsRecyclerView = findViewById(R.id.payment_methods_recycler_view);
        emptyPaymentMethodsText = findViewById(R.id.empty_payment_methods_text);
        addPaymentMethodButton = findViewById(R.id.add_payment_method_button);

        // Set up RecyclerView
        paymentMethodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // In a real app, you would set an adapter with actual payment method data
        // For now, we'll just leave it empty
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());

        // Add payment method button click listener
        addPaymentMethodButton.setOnClickListener(v -> {
            // Show toast message for add payment method functionality
            showToast("Add new payment method functionality");
            // In a real app, you would navigate to an add payment method screen or show a
            // dialog
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