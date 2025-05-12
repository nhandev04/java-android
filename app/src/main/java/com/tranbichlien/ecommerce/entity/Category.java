package com.tranbichlien.ecommerce.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a product category.
 */
public class Category {
    @SerializedName("id")
    private String id;

    @SerializedName("categoryName")
    private String name;

    @SerializedName("categoryDescription")
    private String description;

    @SerializedName("image")
    private String imageUrl;

    // Local field for resource-based images (not from API)
    private int imageResource;

    // Additional fields from the API response
    @SerializedName("icon")
    private String icon;

    @SerializedName("placeholder")
    private String placeholder;

    @SerializedName("active")
    private boolean active;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("createdBy")
    private UserInfo createdBy;

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
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the category
     * 
     * @param id The new category ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the description of the category
     * 
     * @return The category description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the category
     * 
     * @param description The new category description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Get the icon URL of the category
     *
     * @return The icon URL
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set the icon URL of the category
     *
     * @param icon The new icon URL
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Get the placeholder URL of the category
     *
     * @return The placeholder URL
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Set the placeholder URL of the category
     *
     * @param placeholder The new placeholder URL
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * Check if the category is active
     *
     * @return True if the category is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the active status of the category
     *
     * @param active The new active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get the creation date of the category
     *
     * @return The creation date
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the creation date of the category
     *
     * @param createdAt The new creation date
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the update date of the category
     *
     * @return The update date
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Set the update date of the category
     *
     * @param updatedAt The new update date
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Get information about the user who created this category
     *
     * @return The creator user info
     */
    public UserInfo getCreatedBy() {
        return createdBy;
    }

    /**
     * Set information about the user who created this category
     *
     * @param createdBy The new creator user info
     */
    public void setCreatedBy(UserInfo createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Constructor for API response (with ID, description, and image URL)
     * 
     * @param id          The ID of the category
     * @param name        The name of the category
     * @param description The description of the category
     * @param imageUrl    The URL of the category image
     */
    public Category(String id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageResource = 0; // Default to 0 when using URL
    }

    /**
     * Inner class to represent user information in the API response
     */
    public static class UserInfo {
        @SerializedName("id")
        private String id;

        @SerializedName("firstName")
        private String firstName;

        @SerializedName("lastName")
        private String lastName;

        @SerializedName("phoneNumber")
        private String phoneNumber;

        @SerializedName("email")
        private String email;

        @SerializedName("active")
        private boolean active;

        @SerializedName("image")
        private String image;

        @SerializedName("placeholder")
        private String placeholder;

        @SerializedName("createdAt")
        private String createdAt;

        @SerializedName("updatedAt")
        private String updatedAt;

        @SerializedName("createdBy")
        private Object createdBy; // Can be null

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public boolean isActive() {
            return active;
        }

        public String getImage() {
            return image;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }
    }
}
