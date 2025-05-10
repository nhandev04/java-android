package com.tranbichlien.finalproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tranbichlien.finalproject.R;
import java.util.ArrayList;

import com.tranbichlien.finalproject.adapter.ProductAdapter;
import com.tranbichlien.finalproject.entity.Product;

public class FavoriteFragment extends Fragment {

        private ArrayList<Product> favoriteProducts;
        private ProductAdapter adapter;
        private RecyclerView lvFavoriteProducts;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                        @Nullable ViewGroup container,
                        @Nullable Bundle savedInstanceState) {
                // Inflate layout cho fragment
                View view = inflater.inflate(R.layout.fragment_favorite, container, false);

                // Ánh xạ RecyclerView
                lvFavoriteProducts = view.findViewById(R.id.favRecyclerView);

                // Cài đặt LayoutManager cho RecyclerView
                lvFavoriteProducts.setLayoutManager(new LinearLayoutManager(getContext()));

                // Tạo danh sách sản phẩm yêu thích
                favoriteProducts = new ArrayList<>();
                favoriteProducts.add(new Product("iPhone 13", "Apple", "$999", 5.0f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                favoriteProducts.add(new Product("Samsung Galaxy S22", "Samsung", "$899", 4.7f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));
                favoriteProducts.add(new Product("Xiaomi Mi 11", "Xiaomi", "$699", 4.6f,
                                "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"));

                // Khởi tạo adapter và gán vào RecyclerView
                adapter = new ProductAdapter(getContext(), favoriteProducts);
                lvFavoriteProducts.setAdapter(adapter);

                return view;
        }
}
