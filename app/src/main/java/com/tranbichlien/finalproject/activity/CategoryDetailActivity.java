package com.tranbichlien.finalproject.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.api.repository.CategoryRepository;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView categoryTitleTextView;
    private RecyclerView productsRecyclerView;
    private ProgressBar progressBar;
    private CategoryRepository categoryRepository;

    // Constants for intent extras
    public static final String EXTRA_CATEGORY_NAME = "category_name";
    public static final String EXTRA_CATEGORY_IMAGE = "category_image";

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

        // Initialize the CategoryRepository
        categoryRepository = new CategoryRepository();

        // Set up RecyclerView with GridLayoutManager (2 columns)
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void loadProductsForCategory(String categoryName) {
        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Use the CategoryRepository to fetch products by category name
        categoryRepository.getProductsByCategoryName(categoryName, null, null)
                .observe(this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        // Hide progress bar
                        progressBar.setVisibility(View.GONE);

                        if (products != null && !products.isEmpty()) {
                            // Convert List to ArrayList if needed
                            ArrayList<Product> productArrayList = new ArrayList<>(products);

                            // Set adapter
                            ProductAdapter productAdapter = new ProductAdapter(CategoryDetailActivity.this,
                                    productArrayList);
                            productsRecyclerView.setAdapter(productAdapter);
                        } else {
                            // Show message if no products found
                            showToast("No products found for this category");

                            // Fallback to sample data for demo purposes
                            loadSampleProductsForCategory(categoryName);
                        }
                    }
                });
    }

    /**
     * Load sample products for a category (fallback method)
     * 
     * @param categoryName The name of the category
     */
    private void loadSampleProductsForCategory(String categoryName) {
        // Create a list of products for the selected category
        ArrayList<Product> products = new ArrayList<>();

        // Add sample products based on category
        if (categoryName.equals("Điện thoại") || categoryName.equals("iPhones")) {
            products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        } else if (categoryName.equals("Laptop") || categoryName.equals("MacBooks")) {
            products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Dell", "XPS 13", "25,000,000", 4.7f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        } else if (categoryName.equals("Tablet") || categoryName.equals("iPads")) {
            products.add(new Product("Apple", "iPad Pro", "20,000,000", 4.9f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Samsung", "Galaxy Tab S7", "15,000,000", 4.6f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        } else if (categoryName.equals("Đồng hồ") || categoryName.equals("Apple Watch")) {
            products.add(new Product("Apple", "Apple Watch", "10,000,000", 4.7f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Samsung", "Galaxy Watch", "8,000,000", 4.5f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        } else if (categoryName.equals("Services")) {
            products.add(new Product("Apple", "AppleCare+", "2,000,000", 4.8f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Apple", "iCloud Storage", "500,000", 4.5f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        } else {
            // Default products if category is not recognized
            products.add(new Product("Apple", "AirPods Pro", "5,000,000", 4.7f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
            products.add(new Product("Apple", "Apple Pencil", "3,000,000", 4.6f,
                    "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
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
