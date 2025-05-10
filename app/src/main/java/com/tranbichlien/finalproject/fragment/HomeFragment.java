package com.tranbichlien.finalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.activity.ProductDetailActivity;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

        private RecyclerView newProductsRecycler, discountedProductsRecycler;
        private ArrayList<Product> newProducts, discountedProducts;
        private ProductAdapter newProductAdapter, discountedProductAdapter;

        // Main product section UI elements
        private ImageView mainProductImage;
        private TextView mainProductTitle;
        private Button buyNowButton;
        private Product featuredProduct;
        private ProductRepository productRepository;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                        @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_home, container, false);

                // Khởi tạo RecyclerView
                newProductsRecycler = view.findViewById(R.id.newProductsRecycler);
                discountedProductsRecycler = view.findViewById(R.id.discountedProductsRecycler);
                // Initialize main product section UI elements
                mainProductImage = view.findViewById(R.id.mainProductImage);
                mainProductTitle = view.findViewById(R.id.mainProductTitle);
                buyNowButton = view.findViewById(R.id.buyNowButton);


                // Initialize repository
                productRepository = new ProductRepository();

                // Initialize empty lists
                newProducts = new ArrayList<>();
                discountedProducts = new ArrayList<>();

                // Set up adapters
                newProductAdapter = new ProductAdapter(getContext(), newProducts);
                newProductsRecycler.setLayoutManager(
                                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                newProductsRecycler.setAdapter(newProductAdapter);

                discountedProductAdapter = new ProductAdapter(getContext(), discountedProducts);
                discountedProductsRecycler.setLayoutManager(
                                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                discountedProductsRecycler.setAdapter(discountedProductAdapter);

                // =====================================================

                // Create a default featured product as fallback

                // Fetch product with tag "Hot" for main product section
                productRepository.getProductByTag("Hot").observe(getViewLifecycleOwner(), products -> {
                        if (products != null && !products.isEmpty()) {
                                featuredProduct = products.get(0); // Take the first product with the "Hot" tag
                                updateMainProductUI();
                        } else {
                                Toast.makeText(requireContext(), "No hot products available", Toast.LENGTH_SHORT)
                                                .show();
                                // We'll use the default featuredProduct initialized above
                                updateMainProductUI();
                        }
                });

                // Fetch products with tag "Latest Release" for new products section
                productRepository.getProductByTag("Latest Release").observe(getViewLifecycleOwner(), products -> {
                        if (products != null && !products.isEmpty()) {
                                newProducts.clear();
                                newProducts.addAll(products);
                                newProductAdapter.notifyDataSetChanged();
                        } else {
                                Toast.makeText(requireContext(), "No latest releases available", Toast.LENGTH_SHORT)
                                                .show();
                        }
                });

                // Fetch products with tag "On Sale" for discounted products section
                productRepository.getProductByTag("On Sale").observe(getViewLifecycleOwner(), products -> {
                        if (products != null && !products.isEmpty()) {
                                discountedProducts.clear();
                                discountedProducts.addAll(products);
                                discountedProductAdapter.notifyDataSetChanged();
                        } else {
                                Toast.makeText(requireContext(), "No sale products available", Toast.LENGTH_SHORT)
                                                .show();
                        }
                });

                return view;
        }

        /**
         * Update the main product UI section with the current featuredProduct
         */
        private void updateMainProductUI() {
                if (featuredProduct != null) {
                        mainProductTitle.setText(featuredProduct.getName());
                        if (featuredProduct.getImageUrl() != null) {
                                Glide.with(requireContext())
                                                .load(featuredProduct.getImageUrl())
                                                .into(mainProductImage);
                        }

                        // Set click listener for Buy Now button
                        buyNowButton.setOnClickListener(v -> {
                                Intent intent = ProductDetailActivity.newIntent(requireContext(), featuredProduct);
                                startActivity(intent);
                        });
                }
        }
}
