package com.tavanhoaisung.example16;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BagFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> bagProductList;

    public BagFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bag, container, false);

        recyclerView = view.findViewById(R.id.bagRecyclerView); // Đảm bảo id là đúng
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tạo danh sách sản phẩm mẫu
        bagProductList = new ArrayList<>();
        bagProductList.add(new Product("iPhone 14 Pro Max", "Apple", "25,000,000", 4.0f, R.drawable.iphone));
        bagProductList.add(new Product("Galaxy S22 Ultra", "Samsung", "23,000,000", 4.0f, R.drawable.galaxy));
        bagProductList.add(new Product("Mi 11 Lite", "Xiaomi", "7,500,000", 4.0f, R.drawable.iphone13));
        bagProductList.add(new Product("Oppo Reno8", "Oppo", "10,500,000", 4.0f, R.drawable.iphonex));

        // Khởi tạo adapter với danh sách sản phẩm
        adapter = new ProductAdapter(getContext(), bagProductList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
