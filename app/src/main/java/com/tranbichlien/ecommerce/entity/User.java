package com.tranbichlien.ecommerce.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

/**
 * User entity representing a customer in the system
 */
public class User {
    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("roles")
    private List<String> roles;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor with all fields
     */
    public User(String id, String email, String firstName, String lastName, List<String> roles) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    /**
     * Get the user ID
     * 
     * @return The user ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set the user ID
     * 
     * @param id The new user ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the user email
     * 
     * @return The user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user email
     * 
     * @param email The new user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the user first name
     * 
     * @return The user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the user first name
     * 
     * @param firstName The new user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user last name
     * 
     * @return The user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user last name
     * 
     * @param lastName The new user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user roles
     * 
     * @return The user roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Set the user roles
     * 
     * @param roles The new user roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Get the full name of the user
     * 
     * @return The full name (firstName + lastName)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}