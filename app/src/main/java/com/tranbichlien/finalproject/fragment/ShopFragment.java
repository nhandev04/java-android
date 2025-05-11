package com.tranbichlien.finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.activity.AllCategoriesActivity;
import com.tranbichlien.finalproject.activity.CategoryDetailActivity;
import com.tranbichlien.finalproject.activity.CategoryItemListActivity;
import com.tranbichlien.finalproject.adapter.CategoryAdapter;
import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.api.repository.CategoryRepository;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private RecyclerView categoriesRecView, productsRecView;
    private TextView categoriesViewAll, productsViewAll;
    private ImageView searchBtn;
    private ProgressBar categoryProgressBar, productProgressBar;

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    private CategoryAdapter categoryAdapter;
    private ProductAdapter productAdapter;

    // Currently selected category
    private Category selectedCategory = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initRepositories();
        setupRecyclerViews();

        // Load data from API
        loadCategories();
        loadAllProducts();

        // Set up click listeners
        setupClickListeners();
    }

    private void initRepositories() {
        categoryRepository = new CategoryRepository();
        productRepository = new ProductRepository();
    }

    private void initViews(View view) {
        categoriesRecView = view.findViewById(R.id.categoriesRecView);
        productsRecView = view.findViewById(R.id.hotRecView_shopFrag);
        categoriesViewAll = view.findViewById(R.id.categories_GroupViewAll);
        productsViewAll = view.findViewById(R.id.hot_GroupViewAll);
        searchBtn = view.findViewById(R.id.visualSearchBtn_shopPage);

        // Initialize progress bars
        categoryProgressBar = view.findViewById(R.id.categoryProgressBar);
        productProgressBar = view.findViewById(R.id.productProgressBar);

        if (categoryProgressBar == null) {
            // Create programmatically if not in layout
            categoryProgressBar = new ProgressBar(getContext());
            categoryProgressBar.setVisibility(View.GONE);
        }

        if (productProgressBar == null) {
            // Create programmatically if not in layout
            productProgressBar = new ProgressBar(getContext());
            productProgressBar.setVisibility(View.GONE);
        }

        // Initially hide the productsViewAll button until products are loaded
        if (productsViewAll != null) {
            productsViewAll.setVisibility(View.GONE);
        }
    }

    private void setupRecyclerViews() {
        // Initialize adapters with empty lists
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        productAdapter = new ProductAdapter(getContext(), products);

        // Set up category RecyclerView
        categoriesRecView.setAdapter(categoryAdapter);
        categoriesRecView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        // Set up product RecyclerView
        productsRecView.setAdapter(productAdapter);
        productsRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Set OnItemClickListener for categories
        categoryAdapter.setOnCategoryClickListener(position -> {
            if (position >= 0 && position < categories.size()) {
                selectedCategory = categories.get(position);

                // Safely get category name
                String categoryName = selectedCategory != null ? selectedCategory.getName() : null;

                // If "All" category selected, load all products and reset filter
                if (categoryName != null && categoryName.equals("All")) {
                    selectedCategory = null;
                    loadAllProducts();
                    productsViewAll.setText("Xem thêm");
                    return;
                }

                // Show progress indicator
                if (productProgressBar != null) {
                    productProgressBar.setVisibility(View.VISIBLE);
                }

                // Update the products view with filtered products
                loadProductsByCategory(categoryName);

                // Update "View All" button to show the selected category name
                if (categoryName != null && !categoryName.isEmpty()) {
                    productsViewAll.setText("Xem thêm ");
                } else {
                    productsViewAll.setText("Xem thêm");
                }
            }
        });
    }

    private void setupClickListeners() {
        categoriesViewAll.setOnClickListener(v -> {
            // Navigate to AllCategoriesActivity
            Intent intent = new Intent(getActivity(), AllCategoriesActivity.class);
            startActivity(intent);
        });
        productsViewAll.setOnClickListener(v -> {
            if (selectedCategory != null) {
                // Navigate to CategoryItemListActivity with the selected category and filtered
                // products
                Intent intent = CategoryItemListActivity.newIntent(
                        getContext(),
                        selectedCategory,
                        new ArrayList<>(products) // Pass the already filtered products
                );
                startActivity(intent);
            } else {
                // No category selected, show all products
                Toast.makeText(getContext(), "Hiển thị tất cả sản phẩm", Toast.LENGTH_SHORT).show();
                Intent intent = CategoryItemListActivity.newIntent(
                        getContext(),
                        "Tất cả sản phẩm",
                        new ArrayList<>(products) // Pass all products
                );
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(v -> {
            // Search functionality
            Toast.makeText(getContext(), "Tính năng tìm kiếm đang được phát triển", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadCategories() {
        // Show progress bar
        if (categoryProgressBar != null) {
            categoryProgressBar.setVisibility(View.VISIBLE);
        }

        // Load categories from API
        categoryRepository.getCategories(null, null).observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> apiCategories) {
                // Hide progress bar
                if (categoryProgressBar != null) {
                    categoryProgressBar.setVisibility(View.GONE);
                }

                if (apiCategories != null && !apiCategories.isEmpty()) {
                    // Clear existing categories
                    categories.clear();

                    // Add "All" category first to reset filter
                    Category allCategory = new Category("All", R.drawable.apple_logo);
                    allCategory.setDescription("All products available");
                    categories.add(allCategory);

                    // Now add API categories
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

    }

    private void loadAllProducts() {
        // Show progress bar
        if (productProgressBar != null) {
            productProgressBar.setVisibility(View.VISIBLE);
        }

        // Hide productsViewAll until we confirm products are available
        if (productsViewAll != null) {
            productsViewAll.setVisibility(View.GONE);
        }

        // Load all products from API
        productRepository.getProducts(null, null, null, null).observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> apiProducts) {
                        // Hide progress bar
                        if (productProgressBar != null) {
                            productProgressBar.setVisibility(View.GONE);
                        }

                        if (apiProducts != null && !apiProducts.isEmpty()) {
                            // Clear existing products and add new ones
                            products.clear();
                            products.addAll(apiProducts);

                            // Notify adapter of data change
                            productAdapter.notifyDataSetChanged();

                            // Show productsViewAll when products are available
                            if (productsViewAll != null) {
                                productsViewAll.setVisibility(View.VISIBLE);
                            }
                        } else {
                            // If API fails or returns empty data, load sample products
                            loadSampleProducts();
                        }
                    }
                });
    }

    private void loadProductsByCategory(String categoryName) {
        // Show progress bar
        if (productProgressBar != null) {
            productProgressBar.setVisibility(View.VISIBLE);
        }

        // Hide productsViewAll until we confirm products are available
        if (productsViewAll != null) {
            productsViewAll.setVisibility(View.GONE);
        }

        // Check if category name is null or empty
        if (categoryName == null || categoryName.isEmpty()) {
            // Load all products instead
            loadAllProducts();
            return;
        }

        // Load products filtered by category
        productRepository.getProductByCategory(categoryName).observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> filteredProducts) {
                        // Hide progress bar
                        if (productProgressBar != null) {
                            productProgressBar.setVisibility(View.GONE);
                        }

                        if (filteredProducts != null && !filteredProducts.isEmpty()) {
                            // Clear existing products and add filtered ones
                            products.clear();
                            products.addAll(filteredProducts);

                            // Notify adapter of data change
                            productAdapter.notifyDataSetChanged();

                            // Show productsViewAll when products are available
                            if (productsViewAll != null) {
                                productsViewAll.setVisibility(View.VISIBLE);
                            }
                        } else {
                            // If API fails or returns empty data, show message
                            String message = "Không tìm thấy sản phẩm";
                            if (categoryName != null && !categoryName.isEmpty()) {
                                message += " cho danh mục " + categoryName;
                            }
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                            // Load sample products for the category
                            loadSampleProductsForCategory(categoryName);
                        }
                    }
                });
    }

    private void loadSampleProducts() {

    }

    private void loadSampleProductsForCategory(String categoryName) {

    }
}
