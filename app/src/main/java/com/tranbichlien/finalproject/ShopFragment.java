package com.tranbichlien.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    private RecyclerView categoriesRecView, productsRecView;
    private TextView categoriesViewAll, productsViewAll;
    private ImageView searchBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupCategoriesRecyclerView();
        setupProductsRecyclerView();

        // Thiết lập sự kiện click
        categoriesViewAll.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Xem thêm" danh mục
        });

        productsViewAll.setOnClickListener(v -> {
            // Xử lý khi nhấn vào "Xem thêm" sản phẩm
        });

        searchBtn.setOnClickListener(v -> {
        });
    }

    private void initViews(View view) {
        categoriesRecView = view.findViewById(R.id.categoriesRecView);
        productsRecView = view.findViewById(R.id.hotRecView_shopFrag);
        categoriesViewAll = view.findViewById(R.id.categories_GroupViewAll);
        productsViewAll = view.findViewById(R.id.hot_GroupViewAll);
        searchBtn = view.findViewById(R.id.visualSearchBtn_shopPage);
    }

    private void setupCategoriesRecyclerView() {
        // Tạo danh sách danh mục
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Điện thoại", R.drawable.img));
        categories.add(new Category("Laptop", R.drawable.img_1));
        categories.add(new Category("Tablet", R.drawable.img_2));
        categories.add(new Category("Đồng hồ", R.drawable.img_3));
        categories.add(new Category("PC-Máy in", R.drawable.img_4));
        categories.add(new Category("Phụ kiện", R.drawable.img_5));

        // Tạo và thiết lập adapter
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories);
        categoriesRecView.setAdapter(categoryAdapter);
        categoriesRecView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    private void setupProductsRecyclerView() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Apple", "iPhone 14 Pro Max", "25,000,000", 5.0f, R.drawable.iphone));
        products.add(new Product("Samsung", "Galaxy S23+", "20,000,000", 5.0f, R.drawable.galaxy));
        products.add(new Product("Apple", "iPhone 11", "10,000,000", 4.5f, R.drawable.iphone11));
        products.add(new Product("Samsung", "Galaxy S20", "15,000,000", 4.5f, R.drawable.galaxys20));
        ProductAdapter productAdapter = new ProductAdapter(getContext(), products);
        productsRecView.setAdapter(productAdapter);
        productsRecView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}