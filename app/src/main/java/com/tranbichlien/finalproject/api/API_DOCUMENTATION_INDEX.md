# API Documentation Index

This project contains comprehensive API documentation for the Spring Boot application. The documentation is organized into multiple files for better readability.

## Table of Contents

### [API Documentation Part 1](./API_DOCUMENTATION.md)
- Authentication APIs
  - Staff Authentication
  - Customer Authentication
- Attribute APIs
- Card APIs
- Category APIs
- Country APIs
- Coupon APIs
- Customer APIs
- Gallery APIs
- Notification APIs
- Order APIs

### [API Documentation Part 2](./API_DOCUMENTATION_2.md)
- Order Item APIs
- Order Status APIs
- Product APIs
- Product Category APIs
- Product Attribute APIs
- Product Coupon APIs
- Product Shipping Info APIs
- Product Supplier APIs
- Product Tag APIs

### [API Documentation Part 3](./API_DOCUMENTATION_3.md)
- Role APIs
- Sells APIs
- Shipping Rate APIs
- Shipping Zone APIs
- SlideShow APIs
- Staff Account APIs
- Supplier APIs
- Tag APIs

### [API Documentation Part 4](./API_DOCUMENTATION_4.md)
- Variant Option APIs
- Variants APIs
- Variant Value APIs

### [API Documentation Part 5](./API_DOCUMENTATION_5.md)
- Card Item APIs
- Customer Address APIs
- Attribute Value APIs
- Product Attribute Value APIs

## Overview

This API documentation covers all RESTful endpoints provided by the application. Each endpoint is documented with:

- URL path
- HTTP method
- Request body format (if applicable)
- Response format
- Brief description of the endpoint's purpose

## Authentication

Most endpoints require authentication. Please refer to the Authentication APIs section for details on how to obtain and use authentication tokens.

## Data Formats

All APIs use JSON for request and response bodies. Dates are formatted as ISO 8601 strings (e.g., `2025-05-09T12:00:00Z`).

## Error Handling

The API uses standard HTTP status codes to indicate success or failure:
- 200 OK: The request was successful
- 201 Created: A new resource was successfully created
- 204 No Content: The request was successful but no content is returned
- 400 Bad Request: The request was malformed or invalid
- 401 Unauthorized: Authentication is required or failed
- 403 Forbidden: The authenticated user lacks permissions
- 404 Not Found: The requested resource was not found
- 500 Internal Server Error: An unexpected error occurred on the server

Error responses generally include a JSON body with details about the error.
