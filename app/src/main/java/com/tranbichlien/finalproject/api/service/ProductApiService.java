package com.tranbichlien.finalproject.api.service;

import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit service interface for product-related API endpoints
 */
public interface ProductApiService {
    
    /**
     * Get a list of products
     * 
     * @param page     The page number (optional)
     * @param limit    The number of items per page (optional)
     * @param category The category ID to filter by (optional)
     * @param search   The search query (optional)
     * @return A Call object with the API response
     */
    @GET("products")
    Call<ApiResponse<List<Product>>> getProducts(
            @Query("page") Integer page,
            @Query("limit") Integer limit,
            @Query("category") Integer category,
            @Query("search") String search
    );
    
    /**
     * Get a product by ID
     * 
     * @param id The ID of the product
     * @return A Call object with the API response
     */
    @GET("products/{id}")
    Call<ApiResponse<Product>> getProductById(@Path("id") int id);
    
    /**
     * Get featured products
     * 
     * @param limit The number of featured products to get (optional)
     * @return A Call object with the API response
     */
    @GET("products/featured")
    Call<ApiResponse<List<Product>>> getFeaturedProducts(@Query("limit") Integer limit);
    
    /**
     * Get new arrivals
     * 
     * @param limit The number of new arrivals to get (optional)
     * @return A Call object with the API response
     */
    @GET("products/new-arrivals")
    Call<ApiResponse<List<Product>>> getNewArrivals(@Query("limit") Integer limit);
}