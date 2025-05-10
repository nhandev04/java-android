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
        @GET("categories")
        Call<ApiResponse<List<Category>>> getCategories(
                        @Query("page") Integer page,
                        @Query("limit") Integer limit);

        @GET("categories/{id}")
        Call<ApiResponse<Category>> getCategoryById(@Path("id") String id);

        @GET("categories/{id}/products")
        Call<ApiResponse<List<Product>>> getProductsByCategory(
                        @Path("id") String id,
                        @Query("page") Integer page,
                        @Query("limit") Integer limit);

        @GET("products")
        Call<ApiResponse<List<Product>>> getProductsByCategoryName(
                        @Query("category") String name,
                        @Query("page") Integer page,
                        @Query("limit") Integer limit);
}
