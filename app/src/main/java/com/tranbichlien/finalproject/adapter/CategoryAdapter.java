package com.tranbichlien.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tranbichlien.finalproject.activity.CategoryDetailActivity;
import com.tranbichlien.finalproject.R;
import com.tranbichlien.finalproject.entity.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Category> categories;
    private OnCategoryClickListener listener;

    // Interface for handling item clicks
    public interface OnCategoryClickListener {
        void onCategoryClick(int position);
    }

    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_single, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categories.get(position);
        // Log category details for debugging
        // Set category name
        holder.categoryName.setText(category.getName()); // Check if the category has an image URL (from API) or
                                                         // resource ID (local)
        if (category.getImageUrl() != null && !category.getImageUrl().isEmpty()) {
            // Load image from URL using Glide with enhanced settings for all image formats
            Glide.with(context)
                    .load(category.getImageUrl())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .timeout(10000) // 10 seconds timeout for slow connections
                    .into(holder.categoryImage);

            // Log the image being loaded for debugging
            String imageUrl = category.getImageUrl();
            android.util.Log.d("CategoryAdapter", "Loading image: " + imageUrl +
                    " for category: " + category.getName());
        } else {
            // Use local resource image
            holder.categoryImage.setImageResource(category.getImageResource());
        }
        holder.parent.setOnClickListener(v -> {
            // Notify the fragment of the click through the interface if set
            if (listener != null) {
                listener.onCategoryClick(position);
            } else {
                // Direct navigation to CategoryDetailActivity if no listener is set
                // (This will be used in the AllCategoriesActivity)
                Intent intent = CategoryDetailActivity.newIntent(context, category);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView categoryImage;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            categoryImage = itemView.findViewById(R.id.categoryImage_CateSingle);
            categoryName = itemView.findViewById(R.id.categoryTitle_CateSingle);
        }
    }
}