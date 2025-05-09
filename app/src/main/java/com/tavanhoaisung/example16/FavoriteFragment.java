package com.tavanhoaisung.example16;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        favoriteProducts.add(new Product("iPhone 13", "Apple", "$999", 5.0f, R.drawable.iphone13));
        favoriteProducts.add(new Product("Samsung Galaxy S22", "Samsung", "$899", 4.7f, R.drawable.galaxy));
        favoriteProducts.add(new Product("Xiaomi Mi 11", "Xiaomi", "$699", 4.6f, R.drawable.xiao11));

        // Khởi tạo adapter và gán vào RecyclerView
        adapter = new ProductAdapter(getContext(), favoriteProducts);
        lvFavoriteProducts.setAdapter(adapter);

        return view;
    }
}
