package com.tranbichlien.finalproject.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a product category.
 */
public class Category {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String imageUrl;

    // Local field for resource-based images (not from API)
    private int imageResource;

    /**
     * Constructor for Category
     * 
     * @param name          The name of the category
     * @param imageResource The resource ID of the category image
     */
    public Category(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    /**
     * Get the name of the category
     * 
     * @return The category name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the category
     * 
     * @param name The new category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the image resource ID of the category
     * 
     * @return The image resource ID
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     * Set the image resource ID of the category
     * 
     * @param imageResource The new image resource ID
     */
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    /**
     * Get the ID of the category
     * 
     * @return The category ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the category
     * 
     * @param id The new category ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the image URL of the category
     * 
     * @return The image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set the image URL of the category
     * 
     * @param imageUrl The new image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Constructor for API response (with ID and image URL)
     * 
     * @param id       The ID of the category
     * @param name     The name of the category
     * @param imageUrl The URL of the category image
     */
    public Category(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageResource = 0; // Default to 0 when using URL
    }
}
