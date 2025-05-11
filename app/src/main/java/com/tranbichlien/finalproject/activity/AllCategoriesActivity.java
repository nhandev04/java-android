package com.tranbichlien.finalproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.adapter.CategoryAdapter;
import com.tranbichlien.finalproject.api.repository.CategoryRepository;
import com.tranbichlien.finalproject.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView categoriesRecyclerView;
    private ProgressBar progressBar;
    private CategoryRepository categoryRepository;
    private ArrayList<Category> categories = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        // Initialize views
        initViews();

        // Setup categories
        setupCategoriesRecyclerView();

        // Load categories from API
        loadCategories();

        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        progressBar = findViewById(R.id.progressBar);

        // Set up RecyclerView with GridLayoutManager (3 columns)
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        // Initialize repository
        categoryRepository = new CategoryRepository();
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void setupCategoriesRecyclerView() {
        // Initialize adapter with empty list
        categoryAdapter = new CategoryAdapter(this, categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);

        // Set click listener
        categoryAdapter.setOnCategoryClickListener(position -> {
            Category selectedCategory = categories.get(position);
            Intent intent = CategoryDetailActivity.newIntent(this, selectedCategory);
            startActivity(intent);
        });
    }

    private void loadCategories() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Load categories from API
        categoryRepository.getCategories(null, null).observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> apiCategories) {
                // Hide progress bar
                progressBar.setVisibility(View.GONE);

                if (apiCategories != null && !apiCategories.isEmpty()) {
                    // Clear existing categories and add new ones
                    categories.clear();
                    categories.addAll(apiCategories);

                    // Notify adapter of data change
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    // If API fails or returns empty data, load sample categories
                    loadSampleCategories();
                }
            }
        });
    }

    private void loadSampleCategories() {
        // Sample categories based on API format from user prompt
        categories.clear();

        // Fixed URL for images - making sure they use valid image URLs
        String iphoneImageUrl = "https://minhtuanmobile.com/uploads/products/241207030434-4.webp";

        // iPhone category based on the actual API response format
        Category iPhoneCategory = new Category(
                "14668943-d510-4b12-9dea-e55382eab507",
                "iPhones",
                "Latest Apple iPhone models and series.",
                iphoneImageUrl);
        iPhoneCategory.setIcon(iphoneImageUrl);
        iPhoneCategory.setPlaceholder(iphoneImageUrl);
        iPhoneCategory.setActive(true);
        iPhoneCategory.setCreatedAt("2025-05-05T02:42:24Z");
        iPhoneCategory.setUpdatedAt("2025-05-05T02:42:24Z");
        categories.add(iPhoneCategory); // Other categories with separate image URLs for each category
        String macbookImageUrl = "https://minhtuanmobile.com/uploads/products/mbp-spacegray-gallery1-202310.webp";
        String ipadImageUrl = "https://minhtuanmobile.com/uploads/products/ipad-pro-m4-silver-gallery-1-240507.webp";
        String watchImageUrl = "https://minhtuanmobile.com/uploads/products/apple-watch-series-10-46mm-gps-jet-black-aluminum-sport-loop-ink-pdp-image-position-1-vn-vi-240910022744.jpg";
        String airpodsImageUrl = "https://minhtuanmobile.com/uploads/products/airpods-pro-2-charge-via-usb-c-pdp-image-gallery-1-202309.webp";
        String accessoriesImageUrl = "https://minhtuanmobile.com/uploads/products/MN6J3_VW_34FRwatch-44-alum-midnight-nc-se_VW_34FR_WF_CO_GEO_VN.jpg";

        categories.add(new Category(
                "24668943-d510-4b12-9dea-e55382eab508",
                "MacBooks",
                "Premium Apple laptop computers.",
                macbookImageUrl));
        categories.add(new Category(
                "34668943-d510-4b12-9dea-e55382eab509",
                "iPads",
                "Apple tablet devices for every need.",
                ipadImageUrl));
        categories.add(new Category(
                "44668943-d510-4b12-9dea-e55382eab510",
                "Apple Watches",
                "Smart watches with health and fitness features.",
                watchImageUrl));
        categories.add(new Category(
                "54668943-d510-4b12-9dea-e55382eab511",
                "AirPods",
                "Wireless earbuds and headphones.",
                airpodsImageUrl));
        categories.add(new Category(
                "64668943-d510-4b12-9dea-e55382eab512",
                "Accessories",
                "Cases, chargers, and other Apple accessories.",
                accessoriesImageUrl));

        categoryAdapter.notifyDataSetChanged();

        // Show message
        Toast.makeText(this, "Sử dụng dữ liệu mẫu do không thể tải từ API", Toast.LENGTH_SHORT).show();
    }
}