package com.tranbichlien.finalproject;
// Product class with both URL (String) and Resource ID (int)

@Data
public class Product {
    private String name;
    private String brand;
    private String price;
    private float rating;
    private String imageUrl; // For URL
    private int imageResource; // For drawable resource

    // Constructor for URL image
    public Product(String name, String brand, String price, float rating, String imageUrl) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
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

}
