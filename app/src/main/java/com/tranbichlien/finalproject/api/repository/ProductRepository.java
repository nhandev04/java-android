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
}
