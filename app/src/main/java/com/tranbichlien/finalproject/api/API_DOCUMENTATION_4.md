# API Documentation Part 4

This document contains API documentation for the variant-related controllers in the Spring Boot application.

## Variant Option APIs

### 1. Get All Variant Options
**Endpoint:** `/api/variant-options`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "name": "string"
  }
]
```
**Description:** Retrieves a list of all variant options.

---

### 2. Get Variant Option by ID
**Endpoint:** `/api/variant-options/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "name": "string"
}
```
**Description:** Retrieves a specific variant option by its ID.

---

### 3. Get Variant Options by Product ID
**Endpoint:** `/api/variant-options/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "name": "string"
  }
]
```
**Description:** Retrieves all variant options for a specific product by product ID.

---

### 4. Add Variant Option
**Endpoint:** `/api/variant-options`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "name": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "name": "string"
}
```
**Description:** Creates a new variant option.

---

### 5. Update Variant Option
**Endpoint:** `/api/variant-options/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "productId": "UUID",
  "name": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "name": "string"
}
```
**Description:** Updates an existing variant option by its ID.

---

### 6. Delete Variant Option
**Endpoint:** `/api/variant-options/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a variant option by its ID.

---

## Variants APIs

### 1. Get All Variants
**Endpoint:** `/api/variants`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "price": "number",
    "quantity": "integer",
    "sku": "string",
    "imageUrl": "string"
  }
]
```
**Description:** Retrieves a list of all variants.

---

### 2. Get Variant by ID
**Endpoint:** `/api/variants/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "price": "number",
  "quantity": "integer",
  "sku": "string",
  "imageUrl": "string"
}
```
**Description:** Retrieves a specific variant by its ID.

---

### 3. Get Variants by Product ID
**Endpoint:** `/api/variants/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "price": "number",
    "quantity": "integer",
    "sku": "string",
    "imageUrl": "string"
  }
]
```
**Description:** Retrieves all variants for a specific product by product ID.

---

### 4. Add Variant
**Endpoint:** `/api/variants`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "price": "number",
  "quantity": "integer",
  "sku": "string",
  "imageUrl": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "price": "number",
  "quantity": "integer",
  "sku": "string",
  "imageUrl": "string"
}
```
**Description:** Creates a new variant.

---

### 5. Update Variant
**Endpoint:** `/api/variants/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "productId": "UUID",
  "price": "number",
  "quantity": "integer",
  "sku": "string",
  "imageUrl": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "price": "number",
  "quantity": "integer",
  "sku": "string",
  "imageUrl": "string"
}
```
**Description:** Updates an existing variant by its ID.

---

### 6. Delete Variant
**Endpoint:** `/api/variants/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a variant by its ID.

---

## Variant Value APIs

### 1. Get All Variant Values
**Endpoint:** `/api/variant-values`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves a list of all variant values.

---

### 2. Get Variant Value by ID
**Endpoint:** `/api/variant-values/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "variantId": "UUID",
  "productId": "UUID",
  "productAttributeValueId": "UUID",
  "attributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Retrieves a specific variant value by its ID.

---

### 3. Get Variant Values by Variant ID
**Endpoint:** `/api/variant-values/variant/{variantId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all variant values for a specific variant by variant ID.

---

### 4. Get Variant Values by Product ID
**Endpoint:** `/api/variant-values/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all variant values for a specific product by product ID.

---

### 5. Get Variant Values by Product Attribute Value ID
**Endpoint:** `/api/variant-values/product-attribute-value/{productAttributeValueId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all variant values for a specific product attribute value by its ID.

---

### 6. Get Variant Values by Attribute ID
**Endpoint:** `/api/variant-values/attribute/{attributeId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all variant values for a specific attribute by attribute ID.

---

### 7. Get Variant Values by Attribute Value ID
**Endpoint:** `/api/variant-values/attribute-value/{attributeValueId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "variantId": "UUID",
    "productId": "UUID",
    "productAttributeValueId": "UUID",
    "attributeId": "UUID",
    "attributeValueId": "UUID"
  }
]
```
**Description:** Retrieves all variant values for a specific attribute value by its ID.

---

### 8. Add Variant Value
**Endpoint:** `/api/variant-values`  
**Method:** `POST`  
**Request Body:**
```json
{
  "variantId": "UUID",
  "productId": "UUID",
  "productAttributeValueId": "UUID",
  "attributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "variantId": "UUID",
  "productId": "UUID",
  "productAttributeValueId": "UUID",
  "attributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Creates a new variant value.

---

### 9. Update Variant Value
**Endpoint:** `/api/variant-values/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "variantId": "UUID",
  "productId": "UUID",
  "productAttributeValueId": "UUID",
  "attributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "variantId": "UUID",
  "productId": "UUID",
  "productAttributeValueId": "UUID",
  "attributeId": "UUID",
  "attributeValueId": "UUID"
}
```
**Description:** Updates an existing variant value by its ID.

---
