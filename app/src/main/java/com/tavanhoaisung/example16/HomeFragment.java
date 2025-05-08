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

public class HomeFragment extends Fragment {

    private RecyclerView newProductsRecycler, discountedProductsRecycler;
    private ArrayList<Product> newProducts, discountedProducts;
    private ProductAdapter newProductAdapter, discountedProductAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo RecyclerView
        newProductsRecycler = view.findViewById(R.id.newProductsRecycler);
        discountedProductsRecycler = view.findViewById(R.id.discountedProductsRecycler);

        // Dữ liệu mẫu cho sản phẩm mới
        newProducts = new ArrayList<>();
        newProducts.add(new Product("iPhone 14", "Apple", "$999", 5.0f, R.drawable.iphone));
        newProducts.add(new Product("iPhone 13 ProMax", "Apple", "$1999", 5.0f, R.drawable.iphone13));

        newProducts.add(new Product("Galaxy ", "Samsung", "$796", 4.0f, R.drawable.galaxy));
        newProducts.add(new Product("Galaxys20", "Samsung", "$1572", 4.0f, R.drawable.galaxys20));
        // Dữ liệu mẫu cho sản phẩm giảm giá
        discountedProducts = new ArrayList<>();
        discountedProducts.add(new Product("iPhone 14", "Apple", "$799", 4.7f, R.drawable.iphone));
        discountedProducts.add(new Product("iPhone 13", "Apple", "$1799", 4.7f, R.drawable.iphone13));
        discountedProducts.add(new Product("Galaxy", "Samsung", "$600", 4.0f, R.drawable.galaxy));
        discountedProducts.add(new Product("Galaxys20", "Samsung", "$1499", 4.0f, R.drawable.galaxys20));
        // Cài đặt Adapter và LayoutManager cho RecyclerView
        newProductAdapter = new ProductAdapter(getContext(),newProducts);  // Tạo adapter riêng cho sản phẩm mới
        newProductsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newProductsRecycler.setAdapter(newProductAdapter);

        discountedProductAdapter = new ProductAdapter(getContext(),discountedProducts);  // Tạo adapter riêng cho sản phẩm giảm giá
        discountedProductsRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        discountedProductsRecycler.setAdapter(discountedProductAdapter);

        return view;
    }
}
