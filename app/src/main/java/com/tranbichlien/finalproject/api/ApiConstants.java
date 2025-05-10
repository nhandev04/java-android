package com.tranbichlien.finalproject.api;

/**
 * Constants for API configuration
 */
public class ApiConstants {
    // Base URL for the API
    public static final String BASE_URL = "http://172.28.64.1:8080/api/"; // Replace with actual API server URL

    // Endpoints
    public static final String ENDPOINT_PRODUCTS = "products";
    public static final String ENDPOINT_CATEGORIES = "categories";
    public static final String ENDPOINT_USERS = "users";
    public static final String ENDPOINT_ORDERS = "orders";

    // Request parameters
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_CATEGORY = "category";
    public static final String PARAM_SEARCH = "search";

    // Default values
    public static final int DEFAULT_PAGE_SIZE = 20;

    // Timeouts (in seconds)
    public static final int CONNECT_TIMEOUT = 30;
    public static final int READ_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;
}
