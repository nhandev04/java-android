package com.tranbichlien.finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tranbichlien.finalproject.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
import com.tranbichlien.finalproject.entity.Product;
import com.tranbichlien.finalproject.util.StorageUtils;

public class FavoriteFragment extends Fragment {

        private ArrayList<Product> favoriteProducts;
        private ProductAdapter adapter;
        private RecyclerView lvFavoriteProducts;
        private TextView emptyView;
        private ProgressBar progressBar;
        private ProductRepository productRepository;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                        @Nullable ViewGroup container,
                        @Nullable Bundle savedInstanceState) {
                // Inflate layout cho fragment
                View view = inflater.inflate(R.layout.fragment_favorite, container, false);

                // Ánh xạ RecyclerView và emptyView
                lvFavoriteProducts = view.findViewById(R.id.favRecyclerView);
                emptyView = view.findViewById(R.id.emptyFavorites);
                progressBar = view.findViewById(R.id.favoriteProgressBar);

                if (progressBar == null) {
                        // Create progress bar programmatically if not in layout
                        progressBar = new ProgressBar(getContext());
                        ((ViewGroup) lvFavoriteProducts.getParent()).addView(progressBar);
                }

                if (emptyView == null) {
                        // If emptyView is not defined in layout, create it programmatically
                        emptyView = new TextView(getContext());
                        emptyView.setText("No favorite items found");
                        emptyView.setTextSize(16);
                        ((ViewGroup) lvFavoriteProducts.getParent()).addView(emptyView);
                        emptyView.setVisibility(View.GONE);
                }

                // Cài đặt LayoutManager cho RecyclerView
                lvFavoriteProducts.setLayoutManager(new LinearLayoutManager(getContext()));

                // Initialize product repository
                productRepository = new ProductRepository();

                // Initialize empty favorites list and adapter
                favoriteProducts = new ArrayList<>();
                adapter = new ProductAdapter(getContext(), favoriteProducts);
                lvFavoriteProducts.setAdapter(adapter);

                // Load favorite products
                loadFavoriteProducts();

                return view;
        }

        @Override
        public void onResume() {
                super.onResume();
                // Reload favorites when fragment resumes
                refreshFavoritesData();
        }

        /**
         * Public method to refresh favorites data from outside the fragment
         */
        public void refreshFavoritesData() {
                if (isAdded()) {
                        loadFavoriteProducts();
                }
        }

        private void loadFavoriteProducts() {
                // Show progress bar
                if (progressBar != null) {
                        progressBar.setVisibility(View.VISIBLE);
                }

                // Hide recycler view and empty view while loading
                lvFavoriteProducts.setVisibility(View.GONE);
                if (emptyView != null) {
                        emptyView.setVisibility(View.GONE);
                }

                // Get favorite product IDs from SharedPreferences
                final Set<String> favoriteIds = StorageUtils.getFavoriteItems(getContext());

                if (favoriteIds.isEmpty()) {
                        // Hide progress bar
                        if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                        }

                        // Show empty view if no favorites
                        lvFavoriteProducts.setVisibility(View.GONE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.VISIBLE);
                        }
                } else {
                        // Load products from API
                        productRepository.getProducts(null, null, null, null).observe(getViewLifecycleOwner(),
                                        new Observer<List<Product>>() {
                                                @Override
                                                public void onChanged(List<Product> products) {
                                                        // Hide progress bar
                                                        if (progressBar != null) {
                                                                progressBar.setVisibility(View.GONE);
                                                        }

                                                        if (products != null && !products.isEmpty()) {
                                                                // Filter products based on favorite IDs
                                                                favoriteProducts.clear();

                                                                for (Product product : products) {
                                                                        String productId = product.getId() != null
                                                                                        ? product.getId()
                                                                                        : product.getName();
                                                                        if (favoriteIds.contains(productId)) {
                                                                                favoriteProducts.add(product);
                                                                        }
                                                                }

                                                                // Update UI based on filtered products
                                                                updateUI();
                                                        } else {
                                                                // Show empty view if API returns no products
                                                                lvFavoriteProducts.setVisibility(View.GONE);
                                                                if (emptyView != null) {
                                                                        emptyView.setVisibility(View.VISIBLE);
                                                                }
                                                        }
                                                }
                                        });
                }
        }

        private void updateUI() {
                if (favoriteProducts.isEmpty()) {
                        // Show empty view if no favorites after filtering
                        lvFavoriteProducts.setVisibility(View.GONE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.VISIBLE);
                        }
                } else {
                        // Show recycler view with favorites
                        lvFavoriteProducts.setVisibility(View.VISIBLE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.GONE);
                        }

                        // Notify adapter of data change
                        adapter.notifyDataSetChanged();
                }
        }
}
