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

                // Update the products view with filtered products
                loadProductsByCategory(categoryName);

                // Update "View All" button to show the selected category name
                if (categoryName != null && !categoryName.isEmpty()) {
                    productsViewAll.setText("Xem thêm " + categoryName);
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
                // Navigate to CategoryDetailActivity with the selected category
                Intent intent = CategoryDetailActivity.newIntent(getContext(), selectedCategory);
                startActivity(intent);
            } else {
                // No category selected, show all products
                Toast.makeText(getContext(), "Hiển thị tất cả sản phẩm", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CategoryDetailActivity.class);
                intent.putExtra(CategoryDetailActivity.EXTRA_CATEGORY_NAME, "Tất cả sản phẩm");
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

        // // Fixed URL for iPhone images
        // String iphoneImageUrl =
        // "https://minhtuanmobile.com/uploads/products/241207030434-4.webp";

        // // iPhone category based on the actual API response format
        // Category iPhoneCategory = new Category(
        // "14668943-d510-4b12-9dea-e55382eab507",
        // "iPhones",
        // "Latest Apple iPhone models and series.",
        // iphoneImageUrl);
        // iPhoneCategory.setIcon(iphoneImageUrl);
        // iPhoneCategory.setPlaceholder(iphoneImageUrl);
        // iPhoneCategory.setActive(true);
        // iPhoneCategory.setCreatedAt("2025-05-05T02:42:24Z");
        // iPhoneCategory.setUpdatedAt("2025-05-05T02:42:24Z");
        // categories.add(iPhoneCategory); // Other categories with separate image URLs
        // for each category
        // String macbookImageUrl =
        // "https://minhtuanmobile.com/uploads/products/mbp-spacegray-gallery1-202310.webp";
        // String ipadImageUrl =
        // "https://minhtuanmobile.com/uploads/products/ipad-pro-m4-silver-gallery-1-240507.webp";
        // String watchImageUrl =
        // "https://minhtuanmobile.com/uploads/products/apple-watch-series-10-46mm-gps-jet-black-aluminum-sport-loop-ink-pdp-image-position-1-vn-vi-240910022744.jpg";
        // String airpodsImageUrl =
        // "https://minhtuanmobile.com/uploads/products/airpods-pro-2-charge-via-usb-c-pdp-image-gallery-1-202309.webp";
        // String accessoriesImageUrl =
        // "https://minhtuanmobile.com/uploads/products/MN6J3_VW_34FRwatch-44-alum-midnight-nc-se_VW_34FR_WF_CO_GEO_VN.jpg";

        // categories.add(new Category(
        // "24668943-d510-4b12-9dea-e55382eab508",
        // "MacBooks",
        // "Premium Apple laptop computers.",
        // macbookImageUrl));
        // categories.add(new Category(
        // "34668943-d510-4b12-9dea-e55382eab509",
        // "iPads",
        // "Apple tablet devices for every need.",
        // ipadImageUrl));
        // categories.add(new Category(
        // "44668943-d510-4b12-9dea-e55382eab510",
        // "Apple Watches",
        // "Smart watches with health and fitness features.",
        // watchImageUrl));
        // categories.add(new Category(
        // "54668943-d510-4b12-9dea-e55382eab511",
        // "AirPods",
        // "Wireless earbuds and headphones.",
        // airpodsImageUrl));
        // categories.add(new Category(
        // "64668943-d510-4b12-9dea-e55382eab512",
        // "Accessories",
        // "Cases, chargers, and other Apple accessories.",
        // accessoriesImageUrl));

        categoryAdapter.notifyDataSetChanged();
        // Toast.makeText(getContext(), "Sử dụng dữ liệu mẫu do không thể tải từ API",
        // Toast.LENGTH_SHORT).show();
    }

    private void loadAllProducts() {
        // Show progress bar
        if (productProgressBar != null) {
            productProgressBar.setVisibility(View.VISIBLE);
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
        // Sample products as fallback with distinct, valid image URLs
        products.clear();

        // Use distinct image URLs for each product
        // String iphone14ImageUrl =
        // "https://minhtuanmobile.com/uploads/products/iphone-15pro-natural-gallery-1-202309.webp";
        // String samsung23ImageUrl =
        // "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-finish-select-202209-6-7inch-deeppurple?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1663703841896";
        // String iphone11ImageUrl =
        // "https://minhtuanmobile.com/uploads/products/iphone-15-pink-gallery-1-202309.webp";
        // String galaxys20ImageUrl =
        // "https://minhtuanmobile.com/uploads/products/galaxy-s24-ultra-titanium-gray-pure-back-s-pen-221011.webp";

        // products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f,
        // iphone14ImageUrl));
        // products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f,
        // samsung23ImageUrl));
        // products.add(new Product("Apple", "iPhone 11", "10,000,000", 4.5f,
        // iphone11ImageUrl));
        // products.add(new Product("Samsung", "Galaxy S20", "15,000,000", 4.5f,
        // galaxys20ImageUrl));

        productAdapter.notifyDataSetChanged();
    }

    private void loadSampleProductsForCategory(String categoryName) {
        products.clear();

        // // Define image URLs for products
        // String iphone14ImageUrl =
        // "https://minhtuanmobile.com/uploads/products/iphone-15pro-natural-gallery-1-202309.webp";
        // String iphone13ImageUrl =
        // "https://minhtuanmobile.com/uploads/products/iphone-15-pink-gallery-1-202309.webp";
        // String samsungImageUrl =
        // "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-14-pro-finish-select-202209-6-7inch-deeppurple?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1663703841896";
        // String galaxyImageUrl =
        // "https://minhtuanmobile.com/uploads/products/galaxy-s24-ultra-titanium-gray-pure-back-s-pen-221011.webp";
        // String macbookProUrl =
        // "https://minhtuanmobile.com/uploads/products/mbp-spacegray-gallery1-202310.webp";
        // String macbookAirUrl =
        // "https://minhtuanmobile.com/uploads/products/macbook-air-m3-15-midnight-gallery-1-240307.webp";

        // // Add sample products based on category
        // if (categoryName == null) {
        // // Default products if category name is null
        // products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f,
        // iphone14ImageUrl));
        // products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f,
        // samsungImageUrl));
        // } else if ("iPhones".equals(categoryName)) {
        // products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f,
        // iphone14ImageUrl));
        // products.add(new Product("Apple", "iPhone 13", "20,000,000", 4.8f,
        // iphone13ImageUrl));
        // } else if ("MacBooks".equals(categoryName)) {
        // products.add(new Product("Apple", "MacBook Pro", "30,000,000", 4.8f,
        // macbookProUrl));
        // products.add(new Product("Apple", "MacBook Air", "25,000,000", 4.7f,
        // macbookAirUrl));
        // } else {
        // // Generic products for other categories
        // products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f,
        // samsungImageUrl));
        // products.add(new Product("Samsung", "Galaxy S20", "15,000,000", 4.5f,
        // galaxyImageUrl));
        // }

        productAdapter.notifyDataSetChanged();
    }
}
