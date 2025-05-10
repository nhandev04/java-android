package com.tranbichlien.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tranbichlien.finalproject.activity.ProductDetailActivity;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single product item
        View view = LayoutInflater.from(context).inflate(R.layout.single_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to the ViewHolder
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productBrand.setText(product.getBrand());
        holder.productPrice.setText(product.getPrice());

        // Nếu là URL thì dùng Glide
        if (product.getImageUrl() != null) {
            Glide.with(holder.productImage.getContext())
                    .load(product.getImageUrl())
                    .into(holder.productImage);
        } else {
            // Nếu là Drawable resource thì dùng setImageResource
            holder.productImage.setImageResource(product.getImageResource());
        }

        // Set click listener to navigate to product detail
        holder.itemView.setOnClickListener(v -> {
            // Create intent using the helper method in ProductDetailActivity
            Intent intent = ProductDetailActivity.newIntent(context, product);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName, productBrand, productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage_singleProduct);
            productName = itemView.findViewById(R.id.productName_singleProduct);
            productBrand = itemView.findViewById(R.id.productBrandName_singleProduct);
            productPrice = itemView.findViewById(R.id.productPrice_singleProduct);
        }
    }
}
