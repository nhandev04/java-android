package com.tranbichlien.finalproject.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.api.repository.ProductRepository;
import com.tranbichlien.finalproject.entity.Product;
import com.tranbichlien.finalproject.util.StorageUtils;

public class BagFragment extends Fragment {
        private RecyclerView recyclerView;
        private ProductAdapter adapter;
        private ArrayList<Product> bagProductList;
        private TextView emptyView;
        private ProgressBar progressBar;
        private ProductRepository productRepository;
        private Button clearAllButton;
        private Button buyButton;
        private LinearLayout buttonContainer;

        public BagFragment() {
                // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_bag, container, false);
                recyclerView = view.findViewById(R.id.bagRecyclerView);
                emptyView = view.findViewById(R.id.emptyBag);
                progressBar = view.findViewById(R.id.bagProgressBar);
                clearAllButton = view.findViewById(R.id.clearAllButton);
                buyButton = view.findViewById(R.id.buyButton);
                buttonContainer = view.findViewById(R.id.buttonContainer);

                if (progressBar == null) {
                        // Create progress bar programmatically if not in layout
                        progressBar = new ProgressBar(getContext());
                        ((ViewGroup) recyclerView.getParent()).addView(progressBar);
                }

                if (emptyView == null) {
                        // If emptyView is not defined in layout, create it programmatically
                        emptyView = new TextView(getContext());
                        emptyView.setText("Your shopping bag is empty");
                        emptyView.setTextSize(16);
                        ((ViewGroup) recyclerView.getParent()).addView(emptyView);
                        emptyView.setVisibility(View.GONE);
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                // Initialize product repository
                productRepository = new ProductRepository();

                // Initialize empty bag product list
                bagProductList = new ArrayList<>();
                adapter = new ProductAdapter(getContext(), bagProductList);
                recyclerView.setAdapter(adapter); // Setup button click listeners
                setupButtonListeners();

                // Load cart items
                loadCartProducts();

                return view;
        }

        @Override
        public void onResume() {
                super.onResume();
                // Reload cart when fragment resumes
                refreshCartData();
        }

        /**
         * Public method to refresh cart data from outside the fragment
         */
        public void refreshCartData() {
                if (isAdded()) {
                        loadCartProducts();
                }
        }

        private void loadCartProducts() {
                // Show progress bar
                if (progressBar != null) {
                        progressBar.setVisibility(View.VISIBLE);
                }

                // Hide recycler view and empty view while loading
                recyclerView.setVisibility(View.GONE);
                if (emptyView != null) {
                        emptyView.setVisibility(View.GONE);
                }

                // Get cart product IDs from SharedPreferences
                final Set<String> cartIds = StorageUtils.getCartItems(getContext());
                if (cartIds.isEmpty()) {
                        // Hide progress bar
                        if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                        }

                        // Show empty view if no cart items
                        recyclerView.setVisibility(View.GONE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.VISIBLE);
                        } // Hide buttons when cart is empty
                        if (buttonContainer != null) {
                                buttonContainer.setVisibility(View.GONE);
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
                                                                // Filter products based on cart IDs
                                                                bagProductList.clear();

                                                                for (Product product : products) {
                                                                        String productId = product.getId() != null
                                                                                        ? product.getId()
                                                                                        : product.getName();
                                                                        if (cartIds.contains(productId)) {
                                                                                bagProductList.add(product);
                                                                        }
                                                                }

                                                                // Update UI based on filtered products
                                                                updateUI();
                                                        } else {
                                                                // Show empty view if API returns no products
                                                                recyclerView.setVisibility(View.GONE);
                                                                if (emptyView != null) {
                                                                        emptyView.setVisibility(View.VISIBLE);
                                                                }
                                                        }
                                                }
                                        });
                }
        }

        private void updateUI() {
                if (bagProductList.isEmpty()) {
                        // Show empty view if no cart items after filtering
                        recyclerView.setVisibility(View.GONE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.VISIBLE);
                        }
                        // Hide buttons when cart is empty
                        if (buttonContainer != null) {
                                buttonContainer.setVisibility(View.GONE);
                        }
                } else {
                        // Show recycler view with cart items
                        recyclerView.setVisibility(View.VISIBLE);
                        if (emptyView != null) {
                                emptyView.setVisibility(View.GONE);
                        }
                        // Show buttons when cart has items
                        if (buttonContainer != null) {
                                buttonContainer.setVisibility(View.VISIBLE);
                        } // Notify adapter of data change
                        adapter.notifyDataSetChanged();
                }
        }

        /**
         * Setup click listeners for the buttons
         */
        private void setupButtonListeners() {
                // Clear All button - removes all products from cart
                clearAllButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // Add a simple animation effect
                                v.animate().alpha(0.7f).setDuration(100).withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                                v.animate().alpha(1.0f).setDuration(100);
                                                clearAllCart();
                                        }
                                });
                        }
                });

                // Buy button - process the purchase
                buyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // Add a simple animation effect
                                v.animate().alpha(0.7f).setDuration(100).withEndAction(new Runnable() {
                                        @Override
                                        public void run() {
                                                v.animate().alpha(1.0f).setDuration(100);
                                                processPurchase();
                                        }
                                });
                        }
                });
        }

        /**
         * Clear all items from the cart
         */
        private void clearAllCart() {
                if (getContext() == null)
                        return;

                // Clear all cart items in SharedPreferences
                SharedPreferences.Editor editor = StorageUtils.getPreferences(getContext()).edit();
                editor.putStringSet(StorageUtils.CART_ITEMS_KEY, new HashSet<>());
                editor.apply();

                // Clear the list and update UI
                bagProductList.clear();
                updateUI();

                // Show confirmation toast
                Toast.makeText(getContext(), "Đã xóa tất cả sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
        }

        /**
         * Process the purchase (in a real app, this would navigate to checkout)
         */
        private void processPurchase() {
                if (getContext() == null)
                        return;

                if (bagProductList.isEmpty()) {
                        Toast.makeText(getContext(), "Giỏ hàng trống, không thể thanh toán", Toast.LENGTH_SHORT).show();
                        return;
                }

                // In a real app, this would navigate to a checkout screen
                // For now, we'll just show a confirmation message
                Toast.makeText(getContext(), "Đang xử lý thanh toán...", Toast.LENGTH_SHORT).show();

                // Simulate a successful purchase
                Toast.makeText(getContext(), "Thanh toán thành công!", Toast.LENGTH_SHORT).show();

                // Clear the cart after successful purchase
                SharedPreferences.Editor editor = StorageUtils.getPreferences(getContext()).edit();
                editor.putStringSet(StorageUtils.CART_ITEMS_KEY, new HashSet<>());
                editor.apply();

                // Clear the list and update UI
                bagProductList.clear();
                updateUI();
        }
}
