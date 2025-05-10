# API Documentation (Part 2)

## Order Item APIs

### 1. Get All Order Items
**Endpoint:** `/api/order-items`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "orderId": "UUID",
    "productId": "UUID",
    "quantity": "number"
  }
]
```
**Description:** Retrieves a list of all order items.

---

### 2. Get Order Item by ID
**Endpoint:** `/api/order-items/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "orderId": "UUID",
  "productId": "UUID",
  "quantity": "number"
}
```
**Description:** Retrieves a specific order item by its ID.

---

### 3. Get Order Items by Order ID
**Endpoint:** `/api/order-items/order/{orderId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "orderId": "UUID",
    "productId": "UUID",
    "quantity": "number"
  }
]
```
**Description:** Retrieves order items associated with a specific order ID.

---

### 4. Add Order Item
**Endpoint:** `/api/order-items`  
**Method:** `POST`  
**Request Body:**
```json
{
  "orderId": "UUID",
  "productId": "UUID",
  "quantity": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "orderId": "UUID",
  "productId": "UUID",
  "quantity": "number"
}
```
**Description:** Adds a new order item.

---

### 5. Update Order Item
**Endpoint:** `/api/order-items/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "orderId": "UUID",
  "productId": "UUID",
  "quantity": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "orderId": "UUID",
  "productId": "UUID",
  "quantity": "number"
}
```
**Description:** Updates an existing order item by its ID.

---

## Order Status APIs

### 1. Get All Order Statuses
**Endpoint:** `/api/order-statuses`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string"
  }
]
```
**Description:** Retrieves a list of all order statuses.

---

### 2. Get Order Status by ID
**Endpoint:** `/api/order-statuses/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string"
}
```
**Description:** Retrieves a specific order status by its ID.

---

### 3. Get Order Status by Name
**Endpoint:** `/api/order-statuses/name/{statusName}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string"
}
```
**Description:** Retrieves a specific order status by its name.

---

### 4. Add Order Status
**Endpoint:** `/api/order-statuses`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string"
}
```
**Description:** Adds a new order status.

---

### 5. Update Order Status
**Endpoint:** `/api/order-statuses/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string"
}
```
**Description:** Updates an existing order status by its ID.

---

## Product APIs

### 1. Get All Products
**Endpoint:** `/api/products`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "slug": "string"
  }
]
```
**Description:** Retrieves a list of all products.

---

### 2. Get Product by ID
**Endpoint:** `/api/products/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "slug": "string"
}
```
**Description:** Retrieves a specific product by its ID.

---

### 3. Get Product by Slug
**Endpoint:** `/api/products/slug/{slug}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "slug": "string"
}
```
**Description:** Retrieves a specific product by its slug.

---

### 4. Add Product
**Endpoint:** `/api/products`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "slug": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "slug": "string"
}
```
**Description:** Adds a new product.

---

### 5. Update Product
**Endpoint:** `/api/products/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "slug": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "slug": "string"
}
```
**Description:** Updates an existing product by its ID.

---

## Product Category APIs

### 1. Get All Product Categories
**Endpoint:** `/api/product-categories`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "categoryId": "UUID"
  }
]
```
**Description:** Retrieves a list of all product categories.

---

### 2. Get Product Category by ID
**Endpoint:** `/api/product-categories/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "categoryId": "UUID"
}
```
**Description:** Retrieves a specific product category by its ID.

---

### 3. Get Product Categories by Product ID
**Endpoint:** `/api/product-categories/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "categoryId": "UUID"
  }
]
```
**Description:** Retrieves product categories associated with a specific product ID.

---

### 4. Get Product Categories by Category ID
**Endpoint:** `/api/product-categories/category/{categoryId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "categoryId": "UUID"
  }
]
```
**Description:** Retrieves product categories associated with a specific category ID.

---

### 5. Add Product Category
**Endpoint:** `/api/product-categories`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "categoryId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "categoryId": "UUID"
}
```
**Description:** Adds a new product category.

---

## Product Attribute APIs

### 1. Get All Product Attributes
**Endpoint:** `/api/product-attributes`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "attributeId": "UUID"
  }
]
```
**Description:** Retrieves a list of all product attributes.

---

### 2. Get Product Attribute by ID
**Endpoint:** `/api/product-attributes/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "attributeId": "UUID"
}
```
**Description:** Retrieves a specific product attribute by its ID.

---

### 3. Get Product Attributes by Product ID
**Endpoint:** `/api/product-attributes/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "attributeId": "UUID"
  }
]
```
**Description:** Retrieves product attributes associated with a specific product ID.

---

### 4. Get Product Attributes by Attribute ID
**Endpoint:** `/api/product-attributes/attribute/{attributeId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "attributeId": "UUID"
  }
]
```
**Description:** Retrieves product attributes associated with a specific attribute ID.

---

### 5. Add Product Attribute
**Endpoint:** `/api/product-attributes`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "attributeId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "attributeId": "UUID"
}
```
**Description:** Adds a new product attribute.

---

## Product Coupon APIs

### 1. Get All Product Coupons
**Endpoint:** `/api/product-coupons`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "couponId": "UUID"
  }
]
```
**Description:** Retrieves a list of all product coupons.

---

### 2. Get Product Coupon by ID
**Endpoint:** `/api/product-coupons/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "couponId": "UUID"
}
```
**Description:** Retrieves a specific product coupon by its ID.

---

### 3. Get Product Coupons by Product ID
**Endpoint:** `/api/product-coupons/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "couponId": "UUID"
  }
]
```
**Description:** Retrieves product coupons associated with a specific product ID.

---

### 4. Get Product Coupons by Coupon ID
**Endpoint:** `/api/product-coupons/coupon/{couponId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "couponId": "UUID"
  }
]
```
**Description:** Retrieves product coupons associated with a specific coupon ID.

---

### 5. Add Product Coupon
**Endpoint:** `/api/product-coupons`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "couponId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "couponId": "UUID"
}
```
**Description:** Adds a new product coupon.

---

## Product Shipping Info APIs

### 1. Get All Product Shipping Infos
**Endpoint:** `/api/product-shipping-infos`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "weight": "number",
    "dimensions": "string"
  }
]
```
**Description:** Retrieves a list of all product shipping information.

---

### 2. Get Product Shipping Info by ID
**Endpoint:** `/api/product-shipping-infos/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Description:** Retrieves specific product shipping information by its ID.

---

### 3. Get Product Shipping Info by Product ID
**Endpoint:** `/api/product-shipping-infos/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Description:** Retrieves shipping information for a specific product by product ID.

---

### 4. Add Product Shipping Info
**Endpoint:** `/api/product-shipping-infos`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Description:** Adds new shipping information for a product.

---

### 5. Update Product Shipping Info
**Endpoint:** `/api/product-shipping-infos/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "weight": "number",
  "dimensions": "string"
}
```
**Description:** Updates existing product shipping information by its ID.

---

## Product Supplier APIs

### 1. Get All Product Suppliers
**Endpoint:** `/api/product-suppliers`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "supplierId": "UUID",
    "isPrimary": "boolean"
  }
]
```
**Description:** Retrieves a list of all product suppliers.

---

### 2. Get Product Supplier by IDs
**Endpoint:** `/api/product-suppliers/{productId}/{supplierId}`  
**Method:** `GET`  
**Response:**
```json
{
  "productId": "UUID",
  "supplierId": "UUID",
  "isPrimary": "boolean"
}
```
**Description:** Retrieves a specific product supplier by product ID and supplier ID.

---

### 3. Get Product Suppliers by Product ID
**Endpoint:** `/api/product-suppliers/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "supplierId": "UUID",
    "isPrimary": "boolean"
  }
]
```
**Description:** Retrieves all suppliers for a specific product by product ID.

---

### 4. Get Product Suppliers by Supplier ID
**Endpoint:** `/api/product-suppliers/supplier/{supplierId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "supplierId": "UUID",
    "isPrimary": "boolean"
  }
]
```
**Description:** Retrieves all products associated with a specific supplier by supplier ID.

---

### 5. Get Primary Suppliers by Product ID
**Endpoint:** `/api/product-suppliers/product/{productId}/primary`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "supplierId": "UUID",
    "isPrimary": "boolean"
  }
]
```
**Description:** Retrieves all primary suppliers for a specific product by product ID.

---

## Product Tag APIs

### 1. Get All Product Tags
**Endpoint:** `/api/product-tags`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "tagId": "UUID"
  }
]
```
**Description:** Retrieves a list of all product tags.

---

### 2. Get Product Tag by IDs
**Endpoint:** `/api/product-tags/{productId}/{tagId}`  
**Method:** `GET`  
**Response:**
```json
{
  "productId": "UUID",
  "tagId": "UUID"
}
```
**Description:** Retrieves a specific product tag by product ID and tag ID.

---

### 3. Get Product Tags by Product ID
**Endpoint:** `/api/product-tags/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "tagId": "UUID"
  }
]
```
**Description:** Retrieves all tags associated with a specific product by product ID.

---

### 4. Get Product Tags by Tag ID
**Endpoint:** `/api/product-tags/tag/{tagId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "productId": "UUID",
    "tagId": "UUID"
  }
]
```
**Description:** Retrieves all products associated with a specific tag by tag ID.

---

### 5. Create Product Tag
**Endpoint:** `/api/product-tags`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "tagId": "UUID"
}
```
**Response:**
```json
{
  "productId": "UUID",
  "tagId": "UUID"
}
```
**Description:** Associates a tag with a product.

---
