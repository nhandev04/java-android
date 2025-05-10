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
                // Fetch product with tag "Hot" for main product section
                ProductRepository productRepository = new ProductRepository();
                productRepository.getProductByTag("Hot").observe(getViewLifecycleOwner(), products -> {
                        if (products != null && !products.isEmpty()) {
                                featuredProduct = products.get(0); // Take the first product with the "Hot" tag

                                // Update main product section UI
                                mainProductTitle.setText(featuredProduct.getName());
                                Glide.with(requireContext())
                                                .load(featuredProduct.getImageUrl())
                                                .into(mainProductImage);

                                // Set click listener for Buy Now button
                                buyNowButton.setOnClickListener(v -> {
                                        Intent intent = ProductDetailActivity.newIntent(requireContext(),
                                                        featuredProduct);
                                        startActivity(intent);
                                });
                        } else {
                                Toast.makeText(requireContext(), "No hot products available", Toast.LENGTH_SHORT)
                                                .show();

                                // Set up main product section with featured product
                                mainProductTitle.setText(featuredProduct.getName());
                                Glide.with(requireContext())
                                                .load(featuredProduct.getImageUrl())
                                                .into(mainProductImage);

                        }
                });

                // Set click listener for Buy Now button
                buyNowButton.setOnClickListener(v -> {
                        Intent intent = ProductDetailActivity.newIntent(requireContext(), featuredProduct);
                        startActivity(intent);
                });

                // Dữ liệu mẫu cho sản phẩm mới
                newProducts = new ArrayList<>();
                newProducts.add(new Product("iPhone 14", "Apple", "$999", 5.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));

                newProducts.add(new Product("iPhone 13 ProMax", "Apple", "$1999", 5.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));

                newProducts.add(new Product("Galaxy ", "Samsung", "$796", 4.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                newProducts.add(new Product("Galaxys20", "Samsung", "$1572", 4.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                // Dữ liệu mẫu cho sản phẩm giảm giá
                discountedProducts = new ArrayList<>();
                discountedProducts.add(new Product("iPhone 14", "Apple", "$799", 4.7f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                discountedProducts.add(new Product("iPhone 13", "Apple", "$1799", 4.7f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                discountedProducts.add(new Product("Galaxy", "Samsung", "$600", 4.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                discountedProducts.add(new Product("Galaxys20", "Samsung", "$1499", 4.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                // Cài đặt Adapter và LayoutManager cho RecyclerView
                newProductAdapter = new ProductAdapter(getContext(), newProducts); // Tạo adapter riêng cho sản phẩm mới
                newProductsRecycler
                                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                                                false));
                newProductsRecycler.setAdapter(newProductAdapter);

                discountedProductAdapter = new ProductAdapter(getContext(), discountedProducts); // Tạo adapter riêng
                                                                                                 // cho sản
                                                                                                 // phẩm giảm giá
                discountedProductsRecycler
                                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,
                                                false));
                discountedProductsRecycler.setAdapter(discountedProductAdapter);

                return view;
        }
}
