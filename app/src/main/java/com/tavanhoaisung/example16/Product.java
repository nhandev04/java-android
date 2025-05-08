package com.tavanhoaisung.example16;

public class Product {
    private String name;
    private String brand;
    private String price;
    private float rating;
    private int imageResId;

    // Constructor chính
    public Product(String name, String brand, String price, float rating, int imageResId) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResId() {
        return imageResId;
    }
}
