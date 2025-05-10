package com.tranbichlien.finalproject.api.service;

import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Category;
import com.tranbichlien.finalproject.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit service interface for category-related API endpoints
 */
public interface CategoryApiService {
    
    /**
     * Get a list of categories
     * 
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return A Call object with the API response
     */
    @GET("categories")
    Call<ApiResponse<List<Category>>> getCategories(
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );
    
    /**
     * Get a category by ID
     * 
     * @param id The ID of the category
     * @return A Call object with the API response
     */
    @GET("categories/{id}")
    Call<ApiResponse<Category>> getCategoryById(@Path("id") int id);
    
    /**
     * Get products by category ID
     * 
     * @param id    The ID of the category
     * @param page  The page number (optional)
     * @param limit The number of items per page (optional)
     * @return A Call object with the API response
     */
    @GET("categories/{id}/products")
    Call<ApiResponse<List<Product>>> getProductsByCategory(
            @Path("id") int id,
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );
}