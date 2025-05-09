package com.tranbichlien.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryDetailActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView categoryTitleTextView;
    private RecyclerView productsRecyclerView;

    // Constants for intent extras
    public static final String EXTRA_CATEGORY_NAME="category_name";
    public static final String EXTRA_CATEGORY_IMAGE="category_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        // Initialize views
        initViews();

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            String categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME);

            // Set category name as title
            if (categoryName != null) {
                categoryTitleTextView.setText(categoryName);

                // Load products for this category
                loadProductsForCategory(categoryName);
            }
        }

        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        categoryTitleTextView = findViewById(R.id.category_title);
        productsRecyclerView = findViewById(R.id.products_recycler_view);

        // Set up RecyclerView with GridLayoutManager (2 columns)
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void loadProductsForCategory(String categoryName) {
        // Create a list of products for the selected category
        ArrayList<Product> products = new ArrayList<>();

        // Add sample products based on category
        if (categoryName.equals("Điện thoại")) {
            products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f, R.drawable.iphone));
            products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f, R.drawable.galaxy));
            products.add(new Product("Apple", "iPhone 11", "10,000,000", 4.5f, R.drawable.iphone11));
            products.add(new Product("Samsung", "Galaxy S20", "15,000,000", 4.5f, R.drawable.galaxys20));
        } else if (categoryName.equals("Laptop")) {
            products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f, R.drawable.img_1));
            products.add(new Product("Dell", "XPS 13", "25,000,000", 4.7f, R.drawable.img_1));
        } else if (categoryName.equals("Tablet")) {
            products.add(new Product("Apple", "iPad Pro", "20,000,000", 4.9f, R.drawable.img_2));
            products.add(new Product("Samsung", "Galaxy Tab S7", "15,000,000", 4.6f, R.drawable.img_2));
        } else if (categoryName.equals("Đồng hồ")) {
            products.add(new Product("Apple", "Apple Watch", "10,000,000", 4.7f, R.drawable.img_3));
            products.add(new Product("Samsung", "Galaxy Watch", "8,000,000", 4.5f, R.drawable.img_3));
        } else if (categoryName.equals("PC-Máy in")) {
            products.add(new Product("HP", "Desktop PC", "15,000,000", 4.3f, R.drawable.img_4));
            products.add(new Product("Canon", "Printer", "5,000,000", 4.2f, R.drawable.img_4));
        } else if (categoryName.equals("Phụ kiện")) {
            products.add(new Product("Anker", "Power Bank", "1,000,000", 4.4f, R.drawable.img_5));
            products.add(new Product("Sony", "Headphones", "3,000,000", 4.6f, R.drawable.img_5));
        } else {
            // Default products if category is not recognized
            showToast("No products found for this category");
        }

        // Set adapter if there are products
        if (!products.isEmpty()) {
            ProductAdapter productAdapter = new ProductAdapter(this, products);
            productsRecyclerView.setAdapter(productAdapter);
        }
    }

    /**
     * Helper method to show toast messages
     * 
     * @param message The message to display
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Static method to create intent for this activity
    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, category.getName());
        intent.putExtra(EXTRA_CATEGORY_IMAGE, category.getImageResource());
        return intent;
    }
}