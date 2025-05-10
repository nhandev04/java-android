package com.tranbichlien.finalproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("id")
    private String id;

    @SerializedName("slug")
    private String slug;

    @SerializedName("productName")
    private String name;

    @SerializedName("sku")
    private String sku;

    @SerializedName("salePrice")
    private double salePrice;

    @SerializedName("comparePrice")
    private double comparePrice;

    @SerializedName("buyingPrice")
    private double buyingPrice;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("productDescription")
    private String description;

    @SerializedName("productType")
    private String productType;

    @SerializedName("published")
    private boolean published;

    @SerializedName("disableOutOfStock")
    private boolean disableOutOfStock;

    @SerializedName("note")
    private String note;

    @SerializedName("categories")
    private List<String> categories;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("createdBy")
    private CreatedBy createdBy;

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("image")
    private String imageUrl = "https://minhtuanmobile.com/uploads/products/241207030434-4.webp"; // For URL

    // Legacy fields
    @SerializedName("brand")
    private String brand;

    @SerializedName("price")
    private String price;

    @SerializedName("rating")
    private float rating;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("stock")
    private int stock;

    private int imageResource; // For drawable resource (local only)

    // Inner class for createdBy object
    public static class CreatedBy {
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
        private CreatedBy createdBy;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public CreatedBy getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(CreatedBy createdBy) {
            this.createdBy = createdBy;
        }
    }

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
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the product
     * 
     * @param id The new product ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the slug of the product
     * 
     * @return The product slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * Set the slug of the product
     * 
     * @param slug The new product slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
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
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Set the category ID of the product
     * 
     * @param categoryId The new category ID
     */
    public void setCategoryId(String categoryId) {
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
     * Get the SKU of the product
     * 
     * @return The product SKU
     */
    public String getSku() {
        return sku;
    }

    /**
     * Set the SKU of the product
     * 
     * @param sku The new product SKU
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Get the sale price of the product
     * 
     * @return The sale price
     */
    public double getSalePrice() {
        return salePrice;
    }

    /**
     * Set the sale price of the product
     * 
     * @param salePrice The new sale price
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * Get the compare price of the product
     * 
     * @return The compare price
     */
    public double getComparePrice() {
        return comparePrice;
    }

    /**
     * Set the compare price of the product
     * 
     * @param comparePrice The new compare price
     */
    public void setComparePrice(double comparePrice) {
        this.comparePrice = comparePrice;
    }

    /**
     * Get the buying price of the product
     * 
     * @return The buying price
     */
    public double getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * Set the buying price of the product
     * 
     * @param buyingPrice The new buying price
     */
    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    /**
     * Get the quantity of the product
     * 
     * @return The quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the product
     * 
     * @param quantity The new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the short description of the product
     * 
     * @return The short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Set the short description of the product
     * 
     * @param shortDescription The new short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Get the product type
     * 
     * @return The product type
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Set the product type
     * 
     * @param productType The new product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Check if the product is published
     * 
     * @return True if published, false otherwise
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * Set the published status of the product
     * 
     * @param published The new published status
     */
    public void setPublished(boolean published) {
        this.published = published;
    }

    /**
     * Check if out of stock products should be disabled
     * 
     * @return True if disabled when out of stock, false otherwise
     */
    public boolean isDisableOutOfStock() {
        return disableOutOfStock;
    }

    /**
     * Set whether out of stock products should be disabled
     * 
     * @param disableOutOfStock The new disable out of stock status
     */
    public void setDisableOutOfStock(boolean disableOutOfStock) {
        this.disableOutOfStock = disableOutOfStock;
    }

    /**
     * Get the note for the product
     * 
     * @return The note
     */
    public String getNote() {
        return note;
    }

    /**
     * Set the note for the product
     * 
     * @param note The new note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Get the categories of the product
     * 
     * @return The list of categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Set the categories of the product
     * 
     * @param categories The new list of categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Get the creation date of the product
     * 
     * @return The creation date
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the creation date of the product
     * 
     * @param createdAt The new creation date
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the update date of the product
     * 
     * @return The update date
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Set the update date of the product
     * 
     * @param updatedAt The new update date
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Get the creator of the product
     * 
     * @return The creator
     */
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the creator of the product
     * 
     * @param createdBy The new creator
     */
    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the tags of the product
     * 
     * @return The list of tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set the tags of the product
     * 
     * @param tags The new list of tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Constructor for API response
     * 
     * @param id                The ID of the product
     * @param slug              The slug of the product
     * @param name              The name of the product
     * @param sku               The SKU of the product
     * @param salePrice         The sale price of the product
     * @param comparePrice      The compare price of the product
     * @param buyingPrice       The buying price of the product
     * @param quantity          The quantity of the product
     * @param shortDescription  The short description of the product
     * @param description       The description of the product
     * @param productType       The type of the product
     * @param published         Whether the product is published
     * @param disableOutOfStock Whether to disable when out of stock
     * @param note              The note for the product
     * @param categories        The list of categories
     * @param createdAt         The creation date
     * @param updatedAt         The update date
     * @param createdBy         The creator
     * @param tags              The list of tags
     * @param imageUrl          The URL of the product image
     */
    public Product(String id, String slug, String name, String sku, double salePrice, double comparePrice,
                  double buyingPrice, int quantity, String shortDescription, String description,
                  String productType, boolean published, boolean disableOutOfStock, String note,
                  List<String> categories, String createdAt, String updatedAt, CreatedBy createdBy,
                  List<String> tags, String imageUrl) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.sku = sku;
        this.salePrice = salePrice;
        this.comparePrice = comparePrice;
        this.buyingPrice = buyingPrice;
        this.quantity = quantity;
        this.shortDescription = shortDescription;
        this.description = description;
        this.productType = productType;
        this.published = published;
        this.disableOutOfStock = disableOutOfStock;
        this.note = note;
        this.categories = categories;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.imageResource = 0; // Default to 0 when using URL

        // Set legacy fields for backward compatibility
        this.price = String.valueOf(salePrice);
        this.stock = quantity;
    }

    /**
     * Legacy constructor for API response
     * 
     * @param id          The ID of the product
     * @param name        The name of the product
     * @param slug        The slug of the product
     * @param brand       The brand of the product
     * @param price       The price of the product
     * @param rating      The rating of the product
     * @param imageUrl    The URL of the product image
     * @param description The description of the product
     * @param categoryId  The category ID of the product
     * @param stock       The stock quantity of the product
     */
    public Product(String id, String name, String slug, String brand, String price, float rating, 
                  String imageUrl, String description, String categoryId, int stock) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.brand = brand;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.description = description;
        this.categoryId = categoryId;
        this.stock = stock;
        this.quantity = stock;
        this.imageResource = 0; // Default to 0 when using URL
    }
}
