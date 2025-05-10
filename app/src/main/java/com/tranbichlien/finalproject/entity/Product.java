package com.tranbichlien.finalproject;

public class Product {
    private String name;
    private String brand;
    private String price;
    private float rating;
    private String imageUrl = "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"; // For URL
    private int imageResource; // For drawable resource

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
}
