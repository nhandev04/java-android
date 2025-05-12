package com.tranbichlien.ecommerce.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request model for user login
 */
public class LoginRequest {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    /**
     * Default constructor
     */
    public LoginRequest() {
    }

    /**
     * Constructor with all fields
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Get the email
     * 
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     * 
     * @param email The new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password
     * 
     * @param password The new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}