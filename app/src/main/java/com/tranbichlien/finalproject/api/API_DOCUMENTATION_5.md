# API Documentation Part 5

This document contains API documentation for the remaining controllers in the Spring Boot application.

## Card Item APIs

### 1. Get All Card Items
**Endpoint:** `/api/card-items`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "cardId": "UUID",
    "productId": "UUID",
    "quantity": "integer",
    "price": "number"
  }
]
```
**Description:** Retrieves a list of all card items.

---

### 2. Get Card Item by ID
**Endpoint:** `/api/card-items/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "cardId": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "price": "number"
}
```
**Description:** Retrieves a specific card item by its ID.

---

### 3. Get Card Items by Card ID
**Endpoint:** `/api/card-items/card/{cardId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "cardId": "UUID",
    "productId": "UUID",
    "quantity": "integer",
    "price": "number"
  }
]
```
**Description:** Retrieves all card items for a specific card by card ID.

---

### 4. Add Card Item
**Endpoint:** `/api/card-items`  
**Method:** `POST`  
**Request Body:**
```json
{
  "cardId": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "price": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "cardId": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "price": "number"
}
```
**Description:** Adds a new card item.

---

### 5. Update Card Item
**Endpoint:** `/api/card-items/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "cardId": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "price": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "cardId": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "price": "number"
}
```
**Description:** Updates an existing card item by its ID.

---

### 6. Delete Card Item
**Endpoint:** `/api/card-items/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a card item by its ID.

---

## Customer Address APIs

### 1. Get All Customer Addresses
**Endpoint:** `/api/customer-addresses`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "customerId": "UUID",
    "addressLine1": "string",
    "addressLine2": "string",
    "city": "string",
    "state": "string",
    "postalCode": "string",
    "country": "string",
    "isDefault": "boolean"
  }
]
```
**Description:** Retrieves a list of all customer addresses.

---

### 2. Get Customer Address by ID
**Endpoint:** `/api/customer-addresses/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "postalCode": "string",
  "country": "string",
  "isDefault": "boolean"
}
```
**Description:** Retrieves a specific customer address by its ID.

---

### 3. Get Customer Addresses by Customer ID
**Endpoint:** `/api/customer-addresses/customer/{customerId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "customerId": "UUID",
    "addressLine1": "string",
    "addressLine2": "string",
    "city": "string",
    "state": "string",
    "postalCode": "string",
    "country": "string",
    "isDefault": "boolean"
  }
]
```
**Description:** Retrieves all addresses for a specific customer by customer ID.

---

### 4. Add Customer Address
**Endpoint:** `/api/customer-addresses`  
**Method:** `POST`  
**Request Body:**
```json
{
  "customerId": "UUID",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "postalCode": "string",
  "country": "string",
  "isDefault": "boolean"
}
```
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "postalCode": "string",
  "country": "string",
  "isDefault": "boolean"
}
```
**Description:** Adds a new address for a customer.

---

### 5. Update Customer Address
**Endpoint:** `/api/customer-addresses/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "customerId": "UUID",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "postalCode": "string",
  "country": "string",
  "isDefault": "boolean"
}
```
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "addressLine1": "string",
  "addressLine2": "string",
  "city": "string",
  "state": "string",
  "postalCode": "string",
  "country": "string",
  "isDefault": "boolean"
}
```
**Description:** Updates an existing customer address by its ID.

---

### 6. Delete Customer Address
**Endpoint:** `/api/customer-addresses/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a customer address by its ID.

---

## Attribute Value APIs

### 1. Get All Attribute Values
**Endpoint:** `/api/attribute-values`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "attributeId": "UUID",
    "value": "string"
  }
]
```
**Description:** Retrieves a list of all attribute values.

---

### 2. Get Attribute Value by ID
**Endpoint:** `/api/attribute-values/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "attributeId": "UUID",
  "value": "string"
}
```
**Description:** Retrieves a specific attribute value by its ID.

---

### 3. Get Attribute Values by Attribute ID
**Endpoint:** `/api/attribute-values/attribute/{attributeId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "attributeId": "UUID",
    "value": "string"
  }
]
```
**Description:** Retrieves all values for a specific attribute by attribute ID.

---

### 4. Add Attribute Value
**Endpoint:** `/api/attribute-values`  
**Method:** `POST`  
**Request Body:**
```json
{
  "attributeId": "UUID",
  "value": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "attributeId": "UUID",
  "value": "string"
}
```
**Description:** Adds a new attribute value.

---

### 5. Update Attribute Value
**Endpoint:** `/api/attribute-values/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "attributeId": "UUID",
  "value": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "attributeId": "UUID",
  "value": "string"
}
```
**Description:** Updates an existing attribute value by its ID.

---

### 6. Delete Attribute Value
**Endpoint:** `/api/attribute-values/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes an attribute value by its ID.

---

## Product Attribute Value APIs

### 1. Get All Product Attribute Values
**Endpoint:** `/api/product-attribute-values`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productAttributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves a list of all product attribute values.

---

### 2. Get Product Attribute Value by ID
**Endpoint:** `/api/product-attribute-values/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productAttributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Retrieves a specific product attribute value by its ID.

---

### 3. Get Product Attribute Values by Product Attribute ID
**Endpoint:** `/api/product-attribute-values/product-attribute/{productAttributeId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productAttributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all attribute values for a specific product attribute by product attribute ID.

---

### 4. Get Product Attribute Values by Attribute Value ID
**Endpoint:** `/api/product-attribute-values/attribute-value/{attributeValueId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productAttributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all product attribute values for a specific attribute value by attribute value ID.

---

### 5. Get Product Attribute Values by Product ID
**Endpoint:** `/api/product-attribute-values/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productAttributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all attribute values for a specific product by product ID.

---

### 6. Add Product Attribute Value
**Endpoint:** `/api/product-attribute-values`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productAttributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productAttributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Associates an attribute value with a product attribute.

---

### 7. Update Product Attribute Value
**Endpoint:** `/api/product-attribute-values/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "productAttributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productAttributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Updates an existing product attribute value by its ID.

---

### 8. Delete Product Attribute Value
**Endpoint:** `/api/product-attribute-values/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a product attribute value by its ID.

---
