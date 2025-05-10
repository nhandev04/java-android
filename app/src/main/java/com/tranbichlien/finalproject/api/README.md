# API Integration Documentation

This package contains the API integration for the application, allowing it to communicate with a RESTful API.

## Overview

The API integration is built using:
- **Retrofit**: For making API calls
- **OkHttp**: For HTTP client functionality
- **Gson**: For JSON parsing
- **LiveData**: For reactive data handling

## Package Structure

- `api/`: Root package for API-related code
  - `ApiClient.java`: Singleton class for Retrofit initialization
  - `ApiConstants.java`: Constants for API configuration
  - `model/`: Package for API response models
    - `ApiResponse.java`: Generic wrapper for API responses
  - `service/`: Package for API service interfaces
    - `ProductApiService.java`: Interface for product-related endpoints
    - `CategoryApiService.java`: Interface for category-related endpoints
  - `repository/`: Package for repository classes
    - `ProductRepository.java`: Repository for product-related API calls
    - `CategoryRepository.java`: Repository for category-related API calls
  - `example/`: Package for example code
    - `ApiUsageExample.java`: Example of how to use the API repositories

## How to Use

### 1. Initialize Repositories

```java
// Initialize repositories
ProductRepository productRepository = new ProductRepository();
CategoryRepository categoryRepository = new CategoryRepository();
```

### 2. Make API Calls

```java
// Get products with pagination and filtering
productRepository.getProducts(1, 10, null, null).observe(this, products -> {
    if (products != null) {
        // Success - update UI with products
        updateProductsUI(products);
    } else {
        // Error - handle failure
        showError("Failed to load products");
    }
});
```

### 3. Handle Responses

All repository methods return LiveData objects, which can be observed for changes:

```java
// Get a product by ID
productRepository.getProductById(productId).observe(this, product -> {
    if (product != null) {
        // Success - update UI with product details
        updateProductDetailsUI(product);
    } else {
        // Error - handle failure
        showError("Failed to load product");
    }
});
```

## Available API Endpoints

### Products

- `getProducts(page, limit, category, search)`: Get a list of products with optional filtering
- `getProductById(id)`: Get a product by ID
- `getFeaturedProducts(limit)`: Get featured products
- `getNewArrivals(limit)`: Get new arrivals

### Categories

- `getCategories(page, limit)`: Get a list of categories
- `getCategoryById(id)`: Get a category by ID
- `getProductsByCategory(id, page, limit)`: Get products by category ID

## Error Handling

All repository methods handle errors and return null in the LiveData when an error occurs. You should always check if the returned data is null before using it.

## Configuration

The API base URL and other configuration options can be modified in the `ApiConstants.java` file.

## Example

See `ApiUsageExample.java` for a complete example of how to use the API repositories in an Activity.