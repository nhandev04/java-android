package com.tranbichlien.finalproject.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tranbichlien.finalproject.api.ApiClient;
import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for handling product-related API calls
 */
public class ProductRepository {
    
    /**
     * Get a list of products
     * 
     * @param page     The page number (optional)
     * @param limit    The number of items per page (optional)
     * @param category The category ID to filter by (optional)
     * @param search   The search query (optional)
     * @return LiveData containing the list of products
     */
    public LiveData<List<Product>> getProducts(Integer page, Integer limit, Integer category, String search) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
        
        ApiClient.getProductApiService().getProducts(page, limit, category, search)
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
     * Get a product by ID
     * 
     * @param id The ID of the product
     * @return LiveData containing the product
     */
    public LiveData<Product> getProductById(int id) {
        MutableLiveData<Product> productLiveData = new MutableLiveData<>();
        
        ApiClient.getProductApiService().getProductById(id)
                .enqueue(new Callback<ApiResponse<Product>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<Product>> call, Response<ApiResponse<Product>> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
                            productLiveData.setValue(response.body().getData());
                        } else {
                            // Handle error
                            productLiveData.setValue(null);
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<ApiResponse<Product>> call, Throwable t) {
                        // Handle failure
                        productLiveData.setValue(null);
                    }
                });
        
        return productLiveData;
    }
    
    /**
     * Get featured products
     * 
     * @param limit The number of featured products to get (optional)
     * @return LiveData containing the list of featured products
     */
    public LiveData<List<Product>> getFeaturedProducts(Integer limit) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
        
        ApiClient.getProductApiService().getFeaturedProducts(limit)
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
     * Get new arrivals
     * 
     * @param limit The number of new arrivals to get (optional)
     * @return LiveData containing the list of new arrivals
     */
    public LiveData<List<Product>> getNewArrivals(Integer limit) {
        MutableLiveData<List<Product>> productsLiveData = new MutableLiveData<>();
        
        ApiClient.getProductApiService().getNewArrivals(limit)
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