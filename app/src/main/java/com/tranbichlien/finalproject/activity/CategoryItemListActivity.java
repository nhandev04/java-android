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

import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemListActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView categoryTitleTextView;
    private RecyclerView productsRecyclerView;
    private ProgressBar progressBar;
    private ProductRepository productRepository;
    private TextView emptyView; // Constants for intent extras
    public static final String EXTRA_CATEGORY_NAME = "category_name";
    public static final String EXTRA_CATEGORY_ID = "category_id";
    public static final String EXTRA_PRODUCTS_LIST = "products_list";

    private String categoryId;
    private String categoryName;
    private ArrayList<Product> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item_list);

        // Initialize views
        initViews(); // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            categoryName = intent.getStringExtra(EXTRA_CATEGORY_NAME);
            categoryId = intent.getStringExtra(EXTRA_CATEGORY_ID);

            // Check if we have pre-filtered products passed from the fragment
            if (intent.hasExtra(EXTRA_PRODUCTS_LIST)) {
                productsList = (ArrayList<Product>) intent.getSerializableExtra(EXTRA_PRODUCTS_LIST);
            }

            // Set category name as title
            if (categoryName != null) {
                categoryTitleTextView.setText(categoryName);
            }

            // If we have pre-filtered products, use them directly
            if (productsList != null && !productsList.isEmpty()) {
                // Use the pre-filtered products that were passed from the fragment
                handleProductsResponse(productsList);
            } else {
                // Otherwise, load products based on category info
                if (categoryId != null && !categoryId.isEmpty()) {
                    // If we have a category ID, use it for API call
                    loadProductsByCategoryId(categoryId);
                } else if (categoryName != null && !categoryName.isEmpty()) {
                    // Otherwise use category name
                    loadProductsForCategory(categoryName);
                } else {
                    // If no category info provided, load all products
                    loadAllProducts();
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
        emptyView = findViewById(R.id.empty_view);

        // Initialize the repositories
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

        // Use the ProductRepository to fetch products by category name
        productRepository.getProductByCategory(categoryName)
                .observe(this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        handleProductsResponse(products);
                    }
                });
    }

    private void loadAllProducts() {
        // Show progress bar while loading
        progressBar.setVisibility(View.VISIBLE);

        // Load all products from API
        productRepository.getProducts(null, null, null, null)
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
            ProductAdapter productAdapter = new ProductAdapter(this, productArrayList);
            productsRecyclerView.setAdapter(productAdapter);

            // Hide empty view
            emptyView.setVisibility(View.GONE);
        } else {
            // Show message if no products found
            emptyView.setVisibility(View.VISIBLE);
            // Fallback to sample data for demo purposes
            loadSampleProductsForCategory(categoryName);
        }
    }

    private void loadSampleProductsForCategory(String categoryName) {
        // Create a list of products for the selected category
        ArrayList<Product> products = new ArrayList<>();

        // Define image URLs for sample products
        String iphone14ImageUrl = "https://minhtuanmobile.com/uploads/products/iphone-15pro-natural-gallery-1-202309.webp";
        String samsung23ImageUrl = "https://images.samsung.com/is/image/samsung/p6pim/vn/2202/gallery/vn-galaxy-s22-ultra-s908-sm-s908ezwgxxv-thumb-530964026.png";
        String iphone13ImageUrl = "https://minhtuanmobile.com/uploads/products/iphone-15-pink-gallery-1-202309.webp";
        String galaxyImageUrl = "https://minhtuanmobile.com/uploads/products/galaxy-s24-ultra-titanium-gray-pure-back-s-pen-221011.webp";
        String macbookProUrl = "https://minhtuanmobile.com/uploads/products/mbp-spacegray-gallery1-202310.webp";
        String macbookAirUrl = "https://minhtuanmobile.com/uploads/products/macbook-air-m3-15-midnight-gallery-1-240307.webp";
        String ipadProUrl = "https://minhtuanmobile.com/uploads/products/ipad-pro-m4-silver-gallery-1-240507.webp";
        String galaxyTabUrl = "https://images.samsung.com/is/image/samsung/p6pim/vn/sm-x910nzaaxev/gallery/vn-galaxy-tab-s9-ultra-5g-x910-sm-x910nzaaxev-thumb-536654849";

        // Add sample products based on category
        if (categoryName == null || "Tất cả sản phẩm".equals(categoryName) || "All".equals(categoryName)) {
            // Default products if category name is null or "All"
            products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f, iphone14ImageUrl));
            products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f, samsung23ImageUrl));
            products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f, macbookProUrl));
            products.add(new Product("Apple", "iPad Pro", "20,000,000", 4.9f, ipadProUrl));
        } else if ("iPhones".equals(categoryName)) {
            products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f, iphone14ImageUrl));
            products.add(new Product("Apple", "iPhone 13", "20,000,000", 4.8f, iphone13ImageUrl));
        } else if ("MacBooks".equals(categoryName)) {
            products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f, macbookProUrl));
            products.add(new Product("Apple", "MacBook Air", "25,000,000", 4.7f, macbookAirUrl));
        } else if ("iPads".equals(categoryName)) {
            products.add(new Product("Apple", "iPad Pro", "20,000,000", 4.9f, ipadProUrl));
            products.add(new Product("Samsung", "Galaxy Tab S7", "15,000,000", 4.6f, galaxyTabUrl));
        } else {
            // Generic products for other categories
            products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f, samsung23ImageUrl));
            products.add(new Product("Samsung", "Galaxy S20", "15,000,000", 4.5f, galaxyImageUrl));
        }

        if (!products.isEmpty()) {
            // Set adapter if there are products
            ProductAdapter productAdapter = new ProductAdapter(this, products);
            productsRecyclerView.setAdapter(productAdapter);
            // Hide empty view
            emptyView.setVisibility(View.GONE);
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

    /**
     * Static method to create intent for this activity with category and products
     */
    public static Intent newIntent(Context context, Category category, ArrayList<Product> products) {
        Intent intent = new Intent(context, CategoryItemListActivity.class);

        // Safely add extras checking for null values
        if (category.getId() != null) {
            intent.putExtra(EXTRA_CATEGORY_ID, category.getId());
        }

        if (category.getName() != null) {
            intent.putExtra(EXTRA_CATEGORY_NAME, category.getName());
        }

        // Add filtered products if available
        if (products != null && !products.isEmpty()) {
            intent.putExtra(EXTRA_PRODUCTS_LIST, products);
        }

        return intent;
    }

    /**
     * Static method to create intent for this activity with category only
     */
    public static Intent newIntent(Context context, Category category) {
        return newIntent(context, category, null);
    }

    /**
     * Static method to create intent for this activity using just a category name
     * and products
     */
    public static Intent newIntent(Context context, String categoryName, ArrayList<Product> products) {
        Intent intent = new Intent(context, CategoryItemListActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);

        // Add filtered products if available
        if (products != null && !products.isEmpty()) {
            intent.putExtra(EXTRA_PRODUCTS_LIST, products);
        }

        return intent;
    }

    /**
     * Static method to create intent for this activity using just a category name
     */
    public static Intent newIntent(Context context, String categoryName) {
        return newIntent(context, categoryName, null);
    }
}
