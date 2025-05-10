package com.tranbichlien.finalproject.api.service;

import com.tranbichlien.finalproject.api.model.ApiResponse;
import com.tranbichlien.finalproject.entity.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit service interface for tag-related API endpoints
 */
public interface TagApiService {

    /**
     * Get all tags with optional pagination
     * 
     * @param page  Optional page number for pagination
     * @param limit Optional limit for number of items per page
     * @return API response containing a list of tags
     */
    @GET("tags")
    Call<ApiResponse<List<Tag>>> getTags(
            @Query("page") Integer page,
            @Query("limit") Integer limit);

    /**
     * Get a tag by its ID
     * 
     * @param id The ID of the tag to retrieve
     * @return API response containing the requested tag
     */
    @GET("tags/{id}")
    Call<ApiResponse<Tag>> getTagById(@Path("id") String id);
}