package com.tranbichlien.ecommerce.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.ecommerce.api.ApiClient;
import com.tranbichlien.ecommerce.api.model.ApiResponse;
import com.tranbichlien.ecommerce.entity.Category;
import com.tranbichlien.ecommerce.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling category-related API calls
 */
public class CategoryRepository {

    private static final String TAG = "CategoryRepository";

    /**
     * Get a list of categories
     * 
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return LiveData containing the list of categories
     */
    public LiveData<List<Category>> getCategories(Integer page, Integer limit) {
        MutableLiveData<List<Category>> categoriesLiveData = new MutableLiveData<>();

        ApiClient.getCategoryApiService().getCategories(page, limit)
                .enqueue(new Callback<ApiResponse<List<Category>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Category>>> call,
                            Response<ApiResponse<List<Category>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            List<Category> categories = response.body().getData();

                            // Process image URLs to ensure they work with various formats
                            if (categories != null) {
                                for (Category category : categories) {
                                    processImageUrls(category);
                                }
                            }

                            categoriesLiveData.setValue(categories);
                        } else {
                            // Handle error
                            categoriesLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {
                        // Handle failure
                        categoriesLiveData.setValue(null);
                    }
                });

        return categoriesLiveData;
    }

    /**
     * Process image URLs in a category to ensure they work with all image formats
     * 
     * @param category The category to process
     */
    private void processImageUrls(Category category) {
        if (category == null)
            return;

        // Log image URLs for debugging purposes
        String imageUrl = category.getImageUrl();
        String iconUrl = category.getIcon();
        String placeholderUrl = category.getPlaceholder();

        // Ensure the image URL is properly formatted
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String extension = getFileExtension(imageUrl);
            android.util.Log.d(TAG,
                    "Category " + category.getName() + " image URL: " + imageUrl + " (extension: " + extension + ")");
        }

        // Ensure the icon URL is properly formatted
        if (iconUrl != null && !iconUrl.isEmpty()) {
            String extension = getFileExtension(iconUrl);
            android.util.Log.d(TAG,
                    "Category " + category.getName() + " icon URL: " + iconUrl + " (extension: " + extension + ")");
        }

        // Ensure the placeholder URL is properly formatted
        if (placeholderUrl != null && !placeholderUrl.isEmpty()) {
            String extension = getFileExtension(placeholderUrl);
            android.util.Log.d(TAG, "Category " + category.getName() + " placeholder URL: " + placeholderUrl
                    + " (extension: " + extension + ")");
        }
    }

    /**
     * Extract the file extension from a URL
     * 
     * @param url The URL to process
     * @return The file extension or "unknown" if not found
     */
    private String getFileExtension(String url) {
        if (url == null || url.isEmpty())
            return "unknown";

        try {
            int lastDotIndex = url.lastIndexOf('.');
            if (lastDotIndex > 0 && lastDotIndex < url.length() - 1) {
                String extension = url.substring(lastDotIndex + 1).toLowerCase();
                // Check if it's a valid image extension
                if (isValidImageExtension(extension)) {
                    return extension;
                }
            }
        } catch (Exception e) {
            android.util.Log.e(TAG, "Error extracting file extension: " + e.getMessage());
        }

        return "unknown";
    }

    /**
     * Check if the extension is a valid image format
     * 
     * @param extension The file extension to check
     * @return True if it's a valid image extension
     */
    private boolean isValidImageExtension(String extension) {
        return extension.equals("jpg") || extension.equals("jpeg") ||
                extension.equals("png") || extension.equals("gif") ||
                extension.equals("webp") || extension.equals("bmp");
    }
}
