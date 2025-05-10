package com.tranbichlien.finalproject;

/**
 * Model class representing a product category.
 */
public class Category {
    private String name;
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
}