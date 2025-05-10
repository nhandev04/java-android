package com.tranbichlien.finalproject.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.adapter.CategoryAdapter;
import com.tranbichlien.finalproject.entity.Category;

import java.util.ArrayList;

public class AllCategoriesActivity extends AppCompatActivity {

    private ImageView backButton;
    private RecyclerView categoriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        // Initialize views
        initViews();

        // Setup categories
        setupCategoriesRecyclerView();

        // Set click listeners
        setupClickListeners();
    }

    private void initViews() {
        backButton = findViewById(R.id.back_button);
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);

        // Set up RecyclerView with GridLayoutManager (3 columns)
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void setupClickListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> finish());
    }

    private void setupCategoriesRecyclerView() {
        // Create a list of categories
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Điện thoại", R.drawable.img));
        categories.add(new Category("Laptop", R.drawable.img_1));
        categories.add(new Category("Tablet", R.drawable.img_2));
        categories.add(new Category("Đồng hồ", R.drawable.img_3));
        categories.add(new Category("PC-Máy in", R.drawable.img_4));
        categories.add(new Category("Phụ kiện", R.drawable.img_5));

        // Add more categories for demonstration
        categories.add(new Category("Tivi", R.drawable.img));
        categories.add(new Category("Âm thanh", R.drawable.img_1));
        categories.add(new Category("Camera", R.drawable.img_2));
        categories.add(new Category("Điện lạnh", R.drawable.img_3));
        categories.add(new Category("Gia dụng", R.drawable.img_4));
        categories.add(new Category("Đồ chơi", R.drawable.img_5));

        // Set adapter
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categories);
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }
}