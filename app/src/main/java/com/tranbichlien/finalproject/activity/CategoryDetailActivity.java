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

import com.bumptech.glide.Glide;
import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.api.repository.CategoryRepository;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
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
    private ProductRepository productRepository;
    private ImageView categoryImageView;

    // Constants for intent extras
    public static final String EXTRA_CATEGORY_NAME = "category_name";
    public static final String EXTRA_CATEGORY_IMAGE = "category_image";
    public static final String EXTRA_CATEGORY_ID = "category_id";
    public static final String EXTRA_CATEGORY_IMAGE_URL = "category_image_url";

    private String categoryId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        // Initialize views
        initViews();

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME);
            categoryId = intent.getStringExtra(EXTRA_CATEGORY_ID);
            String imageUrl = intent.getStringExtra(EXTRA_CATEGORY_IMAGE_URL);
            int imageResource = intent.getIntExtra(EXTRA_CATEGORY_IMAGE, 0);

            // Set category name as title
            if (categoryName != null) {
                categoryTitleTextView.setText(categoryName);

                // Load category image if available
                if (categoryImageView != null) {
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // Log the image format being loaded
                        String extension = "unknown";
                        int dotIndex = imageUrl.lastIndexOf('.');
                        if (dotIndex > 0) {
                            extension = imageUrl.substring(dotIndex + 1).toLowerCase();
                        }
                        android.util.Log.d("CategoryDetailActivity", "Loading category image: " + imageUrl +
                                " (format: " + extension + ") for category: " + categoryName);

                        // Load image from URL using Glide with enhanced settings for all image formats
                        Glide.with(this)
                                .load(imageUrl)
                                .placeholder(R.drawable.placeholder_image)
                                .error(R.drawable.error_image)
                                .timeout(10000) // 10 seconds timeout for slow connections
                                .into(categoryImageView);
                    } else if (imageResource != 0) {
                        // Use local resource image
                        categoryImageView.setImageResource(imageResource);
                    }
                }

                // Load products for this category
                if (categoryId != null && !categoryId.isEmpty()) {
                    // If we have a category ID, use it for API call
                    loadProductsByCategoryId(categoryId);
                } else {
                    // Otherwise use category name
                    loadProductsForCategory(categoryName);
                }
            }
        }

        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        categoryTitleTextView = findViewById(R.id.category_title);
        productsRecyclerView = findViewById(R.id.products_recycler_view);
        progressBar = findViewById(R.id.progressBar);
        categoryImageView = findViewById(R.id.category_image);

        // Initialize the repositories
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();

        // Set up RecyclerView with GridLayoutManager (2 columns)
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void loadProductsByCategoryId(String categoryId) {
        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Use the repository to fetch products by category ID
        // This would be implemented in ProductRepository
        productRepository.getProductsByCategoryId(categoryId)
                .observe(this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        handleProductsResponse(products);
                    }
                });
    }

    private void loadProductsForCategory(String categoryName) {
        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Use the CategoryRepository to fetch products by category name
        productRepository.getProductByCategory(categoryName)
                .observe(this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        handleProductsResponse(products);
                    }
                });
    }

    private void handleProductsResponse(List<Product> products) {
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

    /**
     * Load sample products for a category (fallback method)
     * 
     * @param categoryName The name of the category
     */
    private void loadSampleProductsForCategory(String categoryName) {
        // Create a list of products for the selected category
        ArrayList<Product> products = new ArrayList<>();

        // // Add sample products based on category
        // if (categoryName.equals("Điện thoại") || categoryName.equals("iPhones")) {
        // products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // } else if (categoryName.equals("Laptop") || categoryName.equals("MacBooks"))
        // {
        // products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // products.add(new Product("Dell", "XPS 13", "25,000,000", 4.7f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // } else if (categoryName.equals("Tablet") || categoryName.equals("iPads")) {
        // products.add(new Product("Apple", "iPad Pro", "20,000,000", 4.9f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // products.add(new Product("Samsung", "Galaxy Tab S7", "15,000,000", 4.6f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // } else {
        // // Generic products for other categories
        // products.add(new Product("Brand", "Product 1", "10,000,000", 4.5f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // products.add(new Product("Brand", "Product 2", "15,000,000", 4.3f,
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
        // }

        // Set adapter if there are products
        ProductAdapter productAdapter = new ProductAdapter(this, products);
        productsRecyclerView.setAdapter(productAdapter);
    }

    /**
     * Helper method to show toast messages
     * 
     * @param message The message to display
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    } // Static method to create intent for this activity

    public static Intent newIntent(Context context, Category category) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);

        // Safely add extras checking for null values
        if (category.getId() != null) {
            intent.putExtra(EXTRA_CATEGORY_ID, category.getId());
        }

        if (category.getName() != null) {
            intent.putExtra(EXTRA_CATEGORY_NAME, category.getName());
        }

        // Only add image resource if it's valid (non-zero)
        if (category.getImageResource() != 0) {
            intent.putExtra(EXTRA_CATEGORY_IMAGE, category.getImageResource());
        }

        // Only add image URL if not null or empty
        if (category.getImageUrl() != null && !category.getImageUrl().isEmpty()) {
            intent.putExtra(EXTRA_CATEGORY_IMAGE_URL, category.getImageUrl());
        }

        return intent;
    }
}
