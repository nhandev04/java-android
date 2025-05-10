package com.tranbichlien.finalproject.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.finalproject.api.ApiClient;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling category-related API calls
 */
public class CategoryRepository {

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
                    public void onResponse(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            categoriesLiveData.setValue(response.body().getData());
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
     * Get a category by ID
     * 
     * @param id The ID of the category
     * @return LiveData containing the category
     */
    public LiveData<Category> getCategoryById(String id) {
        MutableLiveData<Category> categoryLiveData = new MutableLiveData<>();

        ApiClient.getCategoryApiService().getCategoryById(id)
                .enqueue(new Callback<ApiResponse<Category>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<Category>> call, Response<ApiResponse<Category>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            categoryLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            categoryLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<Category>> call, Throwable t) {
                        // Handle failure
                        categoryLiveData.setValue(null);
                    }
                });

        return categoryLiveData;
    }

    /**
     * Get products by category ID
     * 
     * @param id    The ID of the category
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return LiveData containing the list of products
     */
    public LiveData<List<Product>> getProductsByCategory(String id, Integer page, Integer limit) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();

        ApiClient.getCategoryApiService().getProductsByCategory(id, page, limit)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            productsLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            productsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        // Handle failure
                        productsLiveData.setValue(null);
                    }
                });

        return productsLiveData;
    }

    /**
     * Get products by category name
     * 
     * @param name  The name of the category
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return LiveData containing the list of products
     */
    public LiveData<List<Product>> getProductsByCategoryName(String name, Integer page, Integer limit) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();

        ApiClient.getCategoryApiService().getProductsByCategoryName(name, page, limit)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            productsLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            productsLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        // Handle failure
                        productsLiveData.setValue(null);
                    }
                });

        return productsLiveData;
    }
}
