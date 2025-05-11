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
import com.tranbichlien.finalproject.activity.AllCategoriesActivity;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.entity.Product;
import com.tranbichlien.finalproject.util.StorageUtils;

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

        // Handle different product price formats
        if (product.getSalePrice() > 0) {
            holder.productPrice.setText(String.format("%,.0f đ", product.getSalePrice()));
        } else {
            holder.productPrice.setText("Liên hệ");
        }

        holder.productRating.setRating(product.getRating());

        // Use short description if available, otherwise use regular description with
        // limit
        if (product.getShortDescription() != null && !product.getShortDescription().isEmpty()) {
            holder.productDescription.setText(product.getShortDescription());
        } else if (product.getDescription() != null) {
            String desc = product.getDescription();
            holder.productDescription.setText(desc.length() > 50 ? desc.substring(0, 50) + "..." : desc);
        } else {
            holder.productDescription.setText("");
        } // Nếu là URL thì dùng Glide
        if (product.getImageUrl() != null) {
            // Add enhanced image loading with support for all image formats
            Glide.with(holder.productImage.getContext())
                    .load(product.getImageUrl())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .timeout(10000) // 10 seconds timeout for slow connections
                    .into(holder.productImage);

            // Log the image format being loaded
            String imageUrl = product.getImageUrl();
            String extension = "unknown";
            int dotIndex = imageUrl.lastIndexOf('.');
            if (dotIndex > 0) {
                extension = imageUrl.substring(dotIndex + 1).toLowerCase();
            }
            android.util.Log.d("ProductAdapter", "Loading product image: " + imageUrl +
                    " (format: " + extension + ") for product: " + product.getName());
        } else {
            // Nếu là Drawable resource thì dùng setImageResource
            holder.productImage.setImageResource(product.getImageResource());
        }

        // Set up favorite functionality
        String productId = product.getId() != null ? product.getId() : product.getName();
        boolean isFavorite = StorageUtils.isInFavorites(context, productId);

        // Update favorite icon based on status
        updateFavoriteIcon(holder.productAddToFav, isFavorite);

        // Set click listener on the favorite icon
        holder.productAddToFav.setOnClickListener(v -> {
            boolean isNowFavorite = StorageUtils.toggleFavorite(context, productId);
            updateFavoriteIcon(holder.productAddToFav, isNowFavorite);
        });

        // Set click listener to navigate to product detail
        holder.itemView.setOnClickListener(v -> {
            // Create intent using the helper method in ProductDetailActivity
            Intent intent = ProductDetailActivity.newIntent(context, product);
            context.startActivity(intent);
        });
    }

    private void updateFavoriteIcon(ImageView favoriteIcon, boolean isFavorite) {
        if (isFavorite) {
            favoriteIcon.setColorFilter(context.getResources().getColor(R.color.red));
        } else {
            favoriteIcon.setColorFilter(context.getResources().getColor(R.color.mainText));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        ImageView productAddToFav;
        TextView productName, productBrand, productPrice, productDescription;
        RatingBar productRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage_singleProduct);
            productName = itemView.findViewById(R.id.productName_singleProduct);
            productBrand = itemView.findViewById(R.id.productBrandName_singleProduct);
            productPrice = itemView.findViewById(R.id.productPrice_singleProduct);
            productRating = itemView.findViewById(R.id.productRating_singleProduct);
            productDescription = itemView.findViewById(R.id.productDescription_singleProduct);
            productAddToFav = itemView.findViewById(R.id.productAddToFav_singleProduct);
        }
    }
}
