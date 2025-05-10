package com.tranbichlien.finalproject.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Generic API response wrapper
 * @param <T> The type of data contained in the response
 */
public class ApiResponse<T> {
    @SerializedName("status")
    private boolean status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("data")
    private T data;
    
    @SerializedName("pagination")
    private Pagination pagination;
    
    /**
     * Get the status of the response
     * 
     * @return true if the request was successful, false otherwise
     */
    public boolean isStatus() {
        return status;
    }
    
    /**
     * Set the status of the response
     * 
     * @param status The new status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * Get the message of the response
     * 
     * @return The message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Set the message of the response
     * 
     * @param message The new message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Get the data of the response
     * 
     * @return The data
     */
    public T getData() {
        return data;
    }
    
    /**
     * Set the data of the response
     * 
     * @param data The new data
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Get the pagination information of the response
     * 
     * @return The pagination information
     */
    public Pagination getPagination() {
        return pagination;
    }
    
    /**
     * Set the pagination information of the response
     * 
     * @param pagination The new pagination information
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    
    /**
     * Pagination information
     */
    public static class Pagination {
        @SerializedName("current_page")
        private int currentPage;
        
        @SerializedName("total_pages")
        private int totalPages;
        
        @SerializedName("per_page")
        private int perPage;
        
        @SerializedName("total_items")
        private int totalItems;
        
        /**
         * Get the current page
         * 
         * @return The current page
         */
        public int getCurrentPage() {
            return currentPage;
        }
        
        /**
         * Set the current page
         * 
         * @param currentPage The new current page
         */
        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
        
        /**
         * Get the total number of pages
         * 
         * @return The total number of pages
         */
        public int getTotalPages() {
            return totalPages;
        }
        
        /**
         * Set the total number of pages
         * 
         * @param totalPages The new total number of pages
         */
        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
        
        /**
         * Get the number of items per page
         * 
         * @return The number of items per page
         */
        public int getPerPage() {
            return perPage;
        }
        
        /**
         * Set the number of items per page
         * 
         * @param perPage The new number of items per page
         */
        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }
        
        /**
         * Get the total number of items
         * 
         * @return The total number of items
         */
        public int getTotalItems() {
            return totalItems;
        }
        
        /**
         * Set the total number of items
         * 
         * @param totalItems The new total number of items
         */
        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }
    }
}