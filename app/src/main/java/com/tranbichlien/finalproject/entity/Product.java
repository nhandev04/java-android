package com.tranbichlien.finalproject.entity;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("brand")
    private String brand;

    @SerializedName("price")
    private String price;

    @SerializedName("rating")
    private float rating;

    @SerializedName("image")
    private String imageUrl = "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"; // For URL

    @SerializedName("description")
    private String description;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("stock")
    private int stock;

    private int imageResource; // For drawable resource (local only)

    // Constructor for URL image
    public Product(String name, String brand, String price, float rating, String imageUrl) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl ;
        this.imageResource = 0; // Default to 0 if using URL
    }

    // Constructor for Drawable resource
    public Product(String name, String brand, String price, float rating, int imageResource) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageUrl = null; // Default to null if using Drawable
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    /**
     * Get the ID of the product
     * 
     * @return The product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the product
     * 
     * @param id The new product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the description of the product
     * 
     * @return The product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the product
     * 
     * @param description The new product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the category ID of the product
     * 
     * @return The category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Set the category ID of the product
     * 
     * @param categoryId The new category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get the stock quantity of the product
     * 
     * @return The stock quantity
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the stock quantity of the product
     * 
     * @param stock The new stock quantity
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Constructor for API response
     * 
     * @param id          The ID of the product
     * @param name        The name of the product
     * @param brand       The brand of the product
     * @param price       The price of the product
     * @param rating      The rating of the product
     * @param imageUrl    The URL of the product image
     * @param description The description of the product
     * @param categoryId  The category ID of the product
     * @param stock       The stock quantity of the product
     */
    public Product(int id, String name, String brand, String price, float rating, 
                  String imageUrl, String description, int categoryId, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.categoryId = categoryId;
        this.stock = stock;
        this.imageResource = 0; // Default to 0 when using URL
    }
}
