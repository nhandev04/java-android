# API Documentation Part 3

This document contains API documentation for additional controllers in the Spring Boot application.

## Role APIs

### 1. Get All Roles
**Endpoint:** `/api/roles`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "description": "string"
  }
]
```
**Description:** Retrieves a list of all roles.

---

### 2. Get Role by ID
**Endpoint:** `/api/roles/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Retrieves a specific role by its ID.

---

### 3. Create Role
**Endpoint:** `/api/roles`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "description": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Creates a new role.

---

### 4. Update Role
**Endpoint:** `/api/roles/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "description": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Updates an existing role by its ID.

---

### 5. Delete Role
**Endpoint:** `/api/roles/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a role by its ID.

---

## Sells APIs

### 1. Get All Sells
**Endpoint:** `/api/sells`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "quantity": "integer",
    "date": "date-time",
    "amount": "number"
  }
]
```
**Description:** Retrieves a list of all sales records.

---

### 2. Get Sell by ID
**Endpoint:** `/api/sells/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "date": "date-time",
  "amount": "number"
}
```
**Description:** Retrieves a specific sale record by its ID.

---

### 3. Get Sells by Product ID
**Endpoint:** `/api/sells/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "productId": "UUID",
    "quantity": "integer",
    "date": "date-time",
    "amount": "number"
  }
]
```
**Description:** Retrieves all sales records for a specific product by product ID.

---

### 4. Add Sell
**Endpoint:** `/api/sells`  
**Method:** `POST`  
**Request Body:**
```json
{
  "productId": "UUID",
  "quantity": "integer",
  "date": "date-time",
  "amount": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "date": "date-time",
  "amount": "number"
}
```
**Description:** Records a new sale.

---

### 5. Update Sell
**Endpoint:** `/api/sells/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "productId": "UUID",
  "quantity": "integer",
  "date": "date-time",
  "amount": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "productId": "UUID",
  "quantity": "integer",
  "date": "date-time",
  "amount": "number"
}
```
**Description:** Updates an existing sale record by its ID.

---

### 6. Delete Sell
**Endpoint:** `/api/sells/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a sale record by its ID.

---

## Shipping Rate APIs

### 1. Get All Shipping Rates
**Endpoint:** `/api/shipping-rates`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "shippingZoneId": "UUID",
    "name": "string",
    "rate": "number",
    "deliveryTime": "string"
  }
]
```
**Description:** Retrieves a list of all shipping rates.

---

### 2. Get Shipping Rate by ID
**Endpoint:** `/api/shipping-rates/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "shippingZoneId": "UUID",
  "name": "string",
  "rate": "number",
  "deliveryTime": "string"
}
```
**Description:** Retrieves a specific shipping rate by its ID.

---

### 3. Get Shipping Rates by Shipping Zone ID
**Endpoint:** `/api/shipping-rates/shipping-zone/{shippingZoneId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "shippingZoneId": "UUID",
    "name": "string",
    "rate": "number",
    "deliveryTime": "string"
  }
]
```
**Description:** Retrieves all shipping rates for a specific shipping zone by zone ID.

---

### 4. Add Shipping Rate
**Endpoint:** `/api/shipping-rates`  
**Method:** `POST`  
**Request Body:**
```json
{
  "shippingZoneId": "UUID",
  "name": "string",
  "rate": "number",
  "deliveryTime": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "shippingZoneId": "UUID",
  "name": "string",
  "rate": "number",
  "deliveryTime": "string"
}
```
**Description:** Creates a new shipping rate.

---

### 5. Update Shipping Rate
**Endpoint:** `/api/shipping-rates/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "shippingZoneId": "UUID",
  "name": "string",
  "rate": "number",
  "deliveryTime": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "shippingZoneId": "UUID",
  "name": "string",
  "rate": "number",
  "deliveryTime": "string"
}
```
**Description:** Updates an existing shipping rate by its ID.

---

### 6. Delete Shipping Rate
**Endpoint:** `/api/shipping-rates/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a shipping rate by its ID.

---

## Shipping Zone APIs

### 1. Get All Shipping Zones
**Endpoint:** `/api/shipping-zones`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "regions": "string"
  }
]
```
**Description:** Retrieves a list of all shipping zones.

---

### 2. Get Shipping Zone by ID
**Endpoint:** `/api/shipping-zones/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "regions": "string"
}
```
**Description:** Retrieves a specific shipping zone by its ID.

---

### 3. Add Shipping Zone
**Endpoint:** `/api/shipping-zones`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "regions": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "regions": "string"
}
```
**Description:** Creates a new shipping zone.

---

### 4. Update Shipping Zone
**Endpoint:** `/api/shipping-zones/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "regions": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "regions": "string"
}
```
**Description:** Updates an existing shipping zone by its ID.

---

### 5. Delete Shipping Zone
**Endpoint:** `/api/shipping-zones/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a shipping zone by its ID.

---

## SlideShow APIs

### 1. Get All SlideShows
**Endpoint:** `/api/slideshows`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "title": "string",
    "imageUrl": "string",
    "link": "string",
    "order": "integer"
  }
]
```
**Description:** Retrieves a list of all slideshows.

---

### 2. Get SlideShow by ID
**Endpoint:** `/api/slideshows/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "title": "string",
  "imageUrl": "string",
  "link": "string",
  "order": "integer"
}
```
**Description:** Retrieves a specific slideshow by its ID.

---

### 3. Add SlideShow
**Endpoint:** `/api/slideshows`  
**Method:** `POST`  
**Request Body:**
```json
{
  "title": "string",
  "imageUrl": "string",
  "link": "string",
  "order": "integer"
}
```
**Response:**
```json
{
  "id": "UUID",
  "title": "string",
  "imageUrl": "string",
  "link": "string",
  "order": "integer"
}
```
**Description:** Creates a new slideshow.

---

### 4. Update SlideShow
**Endpoint:** `/api/slideshows/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "title": "string",
  "imageUrl": "string",
  "link": "string",
  "order": "integer"
}
```
**Response:**
```json
{
  "id": "UUID",
  "title": "string",
  "imageUrl": "string",
  "link": "string",
  "order": "integer"
}
```
**Description:** Updates an existing slideshow by its ID.

---

### 5. Delete SlideShow
**Endpoint:** `/api/slideshows/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a slideshow by its ID.

---

## Staff Account APIs

### 1. Get All Staff Accounts
**Endpoint:** `/api/staff-accounts`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "email": "string",
    "roleId": "UUID",
    "active": "boolean"
  }
]
```
**Description:** Retrieves a list of all staff accounts.

---

### 2. Get Staff Account by ID
**Endpoint:** `/api/staff-accounts/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string",
  "roleId": "UUID",
  "active": "boolean"
}
```
**Description:** Retrieves a specific staff account by its ID.

---

### 3. Get Staff Account by Email
**Endpoint:** `/api/staff-accounts/email/{email}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string",
  "roleId": "UUID",
  "active": "boolean"
}
```
**Description:** Retrieves a staff account by its email address.

---

### 4. Create Staff Account
**Endpoint:** `/api/staff-accounts`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "email": "string",
  "password": "string", 
  "roleId": "UUID",
  "active": "boolean"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string",
  "roleId": "UUID",
  "active": "boolean"
}
```
**Description:** Creates a new staff account.

---

### 5. Update Staff Account
**Endpoint:** `/api/staff-accounts/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "email": "string",
  "password": "string", 
  "roleId": "UUID",
  "active": "boolean"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string",
  "roleId": "UUID",
  "active": "boolean"
}
```
**Description:** Updates an existing staff account by its ID.

---

### 6. Delete Staff Account
**Endpoint:** `/api/staff-accounts/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a staff account by its ID.

---

## Supplier APIs

### 1. Get All Suppliers
**Endpoint:** `/api/suppliers`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "contactName": "string",
    "email": "string",
    "phone": "string",
    "address": "string"
  }
]
```
**Description:** Retrieves a list of all suppliers.

---

### 2. Get Supplier by ID
**Endpoint:** `/api/suppliers/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "contactName": "string",
  "email": "string",
  "phone": "string",
  "address": "string"
}
```
**Description:** Retrieves a specific supplier by its ID.

---

### 3. Add Supplier
**Endpoint:** `/api/suppliers`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "contactName": "string",
  "email": "string",
  "phone": "string",
  "address": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "contactName": "string",
  "email": "string",
  "phone": "string",
  "address": "string"
}
```
**Description:** Creates a new supplier.

---

### 4. Update Supplier
**Endpoint:** `/api/suppliers/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "contactName": "string",
  "email": "string",
  "phone": "string",
  "address": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "contactName": "string",
  "email": "string",
  "phone": "string",
  "address": "string"
}
```
**Description:** Updates an existing supplier by its ID.

---

### 5. Delete Supplier
**Endpoint:** `/api/suppliers/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a supplier by its ID.

---

## Tag APIs

### 1. Get All Tags
**Endpoint:** `/api/tags`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "description": "string"
  }
]
```
**Description:** Retrieves a list of all tags.

---

### 2. Get Tag by ID
**Endpoint:** `/api/tags/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Retrieves a specific tag by its ID.

---

### 3. Add Tag
**Endpoint:** `/api/tags`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "description": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Creates a new tag.

---

### 4. Update Tag
**Endpoint:** `/api/tags/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "description": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Updates an existing tag by its ID.

---

### 5. Delete Tag
**Endpoint:** `/api/tags/{id}`  
**Method:** `DELETE`  
**Response:** No content (204)  
**Description:** Deletes a tag by its ID.

---
