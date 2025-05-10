package com.tranbichlien.finalproject.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.finalproject.api.ApiClient;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling product-related API calls
 */
public class ProductRepository {

    public LiveData<List<Product>> getProducts(Integer page, Integer limit, String category, String search) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();

        ApiClient.getProductApiService().getProducts(page, limit, category, search)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call,
                            Response<ApiResponse<List<Product>>> response) {
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
     * Get products filtered by a specific category
     * 
     * @param categoryName The category name to filter by
     * @return LiveData containing the filtered products list
     */
    public LiveData<List<Product>> getProductByCategory(String categoryName) {
        MutableLiveData<List<Product>> filteredProductsLiveData = new MutableLiveData<>();

        // Get all products first
        ApiClient.getProductApiService().getProducts(null, null, null, null)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call,
                            Response<ApiResponse<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            // Filter products by category
                            List<Product> allProducts = response.body().getData();
                            List<Product> filteredProducts = allProducts.stream()
                                    .filter(product -> product.getCategories() != null &&
                                            product.getCategories().contains(categoryName))
                                    .collect(Collectors.toList());

                            filteredProductsLiveData.setValue(filteredProducts);
                        } else {
                            // Handle error
                            filteredProductsLiveData.setValue(new ArrayList<>());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        // Handle failure
                        filteredProductsLiveData.setValue(new ArrayList<>());
                    }
                });

        return filteredProductsLiveData;
    }

    /**
     * Get products filtered by a specific tag
     * 
     * @param tagName The tag name to filter by
     * @return LiveData containing the filtered products list
     */
    public LiveData<List<Product>> getProductByTag(String tagName) {
        MutableLiveData<List<Product>> filteredProductsLiveData = new MutableLiveData<>();

        // Get all products first
        ApiClient.getProductApiService().getProducts(null, null, null, null)
                .enqueue(new Callback<ApiResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Product>>> call,
                            Response<ApiResponse<List<Product>>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            // Filter products by tag
                            List<Product> allProducts = response.body().getData();
                            List<Product> filteredProducts = allProducts.stream()
                                    .filter(product -> product.getTags() != null &&
                                            product.getTags().contains(tagName))
                                    .collect(Collectors.toList());

                            filteredProductsLiveData.setValue(filteredProducts);
                        } else {
                            // Handle error
                            filteredProductsLiveData.setValue(new ArrayList<>());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {
                        // Handle failure
                        filteredProductsLiveData.setValue(new ArrayList<>());
                    }
                });

        return filteredProductsLiveData;
    }
}
