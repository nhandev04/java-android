package com.tranbichlien.finalproject.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Model class representing a tag.
 */
public class Tag {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    /**
     * Default constructor
     */
    public Tag() {
    }

    /**
     * Constructor with all fields
     * 
     * @param id          The tag ID
     * @param name        The tag name
     * @param description The tag description
     */
    public Tag(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Get the ID of the tag
     * 
     * @return The tag ID
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the tag
     * 
     * @param id The new tag ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the tag
     * 
     * @return The tag name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the tag
     * 
     * @param name The new tag name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of the tag
     * 
     * @return The tag description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the tag
     * 
     * @param description The new tag description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}