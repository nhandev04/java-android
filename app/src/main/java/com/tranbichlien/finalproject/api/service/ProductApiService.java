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
        @GET("products")
        Call<ApiResponse<List<Product>>> getProducts(
                        @Query("page") Integer page,
                        @Query("limit") Integer limit,
                        @Query("category") String category,
                        @Query("search") String search);

        @GET("products/{id}")
        Call<ApiResponse<Product>> getProductById(@Path("id") String id);

        @GET("product-tags")
        Call<ApiResponse<List<String>>> getProductTags(@Query("limit") Integer limit);

        @GET("products")
        Call<ApiResponse<List<Product>>> getProductsByTag(
                        @Query("tag") String tag,
                        @Query("page") Integer page,
                        @Query("limit") Integer limit);
}
