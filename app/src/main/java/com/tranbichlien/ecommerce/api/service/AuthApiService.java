package com.tranbichlien.ecommerce.api.service;

import com.tranbichlien.ecommerce.api.ApiConstants;
import com.tranbichlien.ecommerce.api.model.ApiResponse;
import com.tranbichlien.ecommerce.api.model.LoginRequest;
import com.tranbichlien.ecommerce.api.model.LoginResponse;
import com.tranbichlien.ecommerce.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * API service for authentication operations
 */
public interface AuthApiService {
    /**
     * Login a user
     * 
     * @param loginRequest The login request containing email and password
     * @return A Call object with the login response containing the JWT token
     */
    @POST(ApiConstants.ENDPOINT_LOGIN)
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    /**
     * Get the current user's information
     * 
     * @param token The JWT token for authentication
     * @return A Call object with the user information
     */
    @GET(ApiConstants.ENDPOINT_CURRENT_USER)
    Call<User> getCurrentUser(@Header("Authorization") String token);
}