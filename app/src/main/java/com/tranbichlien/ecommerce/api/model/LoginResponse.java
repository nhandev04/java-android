package com.tranbichlien.ecommerce.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Response model for user login
 */
public class LoginResponse {
    @SerializedName("token")
    private String token;

    /**
     * Default constructor
     */
    public LoginResponse() {
    }

    /**
     * Constructor with token
     */
    public LoginResponse(String token) {
        this.token = token;
    }

    /**
     * Get the JWT token
     * 
     * @return The JWT token
     */
    public String getToken() {
        return token;
    }

    /**
     * Set the JWT token
     * 
     * @param token The new JWT token
     */
    public void setToken(String token) {
        this.token = token;
    }
}