# API Documentation

## Authentication APIs

### 1. Login (Staff)
**Endpoint:** `/api/auth/login`  
**Method:** `POST`  
**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```
**Response:**
```json
{
  "token": "string"
}
```
**Description:** This API allows staff to log in by providing their email and password. Upon successful authentication, a token is returned.

---

### 2. Get Current User (Staff)
**Endpoint:** `/api/auth/me`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "email": "string",
  "firstName": "string",
  "lastName": "string",
  "roles": ["string"]
}
```
**Description:** This API retrieves the information of the currently authenticated user.

---

## Staff Authentication APIs

### 1. Login (Customer)
**Endpoint:** `/api/auth/user/login`  
**Method:** `POST`  
**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```
**Response:**
```json
{
  "token": "string"
}
```
**Description:** This API allows customers to log in by providing their email and password. Upon successful authentication, a token is returned.

---

### 2. Get Current User (Customer)
**Endpoint:** `/api/auth/user/me`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "email": "string",
  "firstName": "string",
  "lastName": "string",
  "roles": ["string"]
}
```
**Description:** This API retrieves the information of the currently authenticated customer.

---

## Attribute APIs

### 1. Get All Attributes
**Endpoint:** `/api/attributes`  
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
**Description:** Retrieves a list of all attributes.

---

### 2. Get Attribute by ID
**Endpoint:** `/api/attributes/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Retrieves a specific attribute by its ID.

---

### 3. Add Attribute
**Endpoint:** `/api/attributes`  
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
**Description:** Adds a new attribute.

---

### 4. Update Attribute
**Endpoint:** `/api/attributes/{id}`  
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
**Description:** Updates an existing attribute by its ID.

---

### 5. Delete Attribute
**Endpoint:** `/api/attributes/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Attribute deleted successfully."
}
```
**Description:** Deletes an attribute by its ID.

---

## Card APIs

### 1. Get All Cards
**Endpoint:** `/api/cards`  
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
**Description:** Retrieves a list of all cards.

---

### 2. Get Card by ID
**Endpoint:** `/api/cards/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Retrieves a specific card by its ID.

---

### 3. Add Card
**Endpoint:** `/api/cards`  
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
**Description:** Adds a new card.

---

### 4. Update Card
**Endpoint:** `/api/cards/{id}`  
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
**Description:** Updates an existing card by its ID.

---

### 5. Delete Card
**Endpoint:** `/api/cards/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Card deleted successfully."
}
```
**Description:** Deletes a card by its ID.

---

## Category APIs

### 1. Get All Categories
**Endpoint:** `/api/categories`  
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
**Description:** Retrieves a list of all categories.

---

### 2. Get Category by ID
**Endpoint:** `/api/categories/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "description": "string"
}
```
**Description:** Retrieves a specific category by its ID.

---

### 3. Add Category
**Endpoint:** `/api/categories`  
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
**Description:** Adds a new category.

---

### 4. Update Category
**Endpoint:** `/api/categories/{id}`  
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
**Description:** Updates an existing category by its ID.

---

### 5. Delete Category
**Endpoint:** `/api/categories/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Category deleted successfully."
}
```
**Description:** Deletes a category by its ID.

---

## Country APIs

### 1. Get All Countries
**Endpoint:** `/api/countries`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "Long",
    "name": "string",
    "code": "string"
  }
]
```
**Description:** Retrieves a list of all countries.

---

### 2. Get Country by ID
**Endpoint:** `/api/countries/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "Long",
  "name": "string",
  "code": "string"
}
```
**Description:** Retrieves a specific country by its ID.

---

### 3. Add Country
**Endpoint:** `/api/countries`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "code": "string"
}
```
**Response:**
```json
{
  "id": "Long",
  "name": "string",
  "code": "string"
}
```
**Description:** Adds a new country.

---

### 4. Update Country
**Endpoint:** `/api/countries/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "code": "string"
}
```
**Response:**
```json
{
  "id": "Long",
  "name": "string",
  "code": "string"
}
```
**Description:** Updates an existing country by its ID.

---

### 5. Delete Country
**Endpoint:** `/api/countries/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Country deleted successfully."
}
```
**Description:** Deletes a country by its ID.

---

## Coupon APIs

### 1. Get All Coupons
**Endpoint:** `/api/coupons`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "code": "string",
    "discount": "number"
  }
]
```
**Description:** Retrieves a list of all coupons.

---

### 2. Get Coupon by ID
**Endpoint:** `/api/coupons/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "code": "string",
  "discount": "number"
}
```
**Description:** Retrieves a specific coupon by its ID.

---

### 3. Get Coupon by Code
**Endpoint:** `/api/coupons/code/{code}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "code": "string",
  "discount": "number"
}
```
**Description:** Retrieves a specific coupon by its code.

---

### 4. Add Coupon
**Endpoint:** `/api/coupons`  
**Method:** `POST`  
**Request Body:**
```json
{
  "code": "string",
  "discount": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "code": "string",
  "discount": "number"
}
```
**Description:** Adds a new coupon.

---

### 5. Update Coupon
**Endpoint:** `/api/coupons/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "code": "string",
  "discount": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "code": "string",
  "discount": "number"
}
```
**Description:** Updates an existing coupon by its ID.

---

### 6. Delete Coupon
**Endpoint:** `/api/coupons/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Coupon deleted successfully."
}
```

**Description:** Deletes a coupon by its ID.

---

## Customer APIs

### 1. Get All Customers
**Endpoint:** `/api/customers`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "email": "string"
  }
]
```
**Description:** Retrieves a list of all customers.

---

### 2. Get Customer by ID
**Endpoint:** `/api/customers/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string"
}
```
**Description:** Retrieves a specific customer by their ID.

---

### 3. Get Customer by Email
**Endpoint:** `/api/customers/email/{email}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string"
}
```
**Description:** Retrieves a specific customer by their email.

---

### 4. Add Customer
**Endpoint:** `/api/customers`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "email": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string"
}
```
**Description:** Adds a new customer.

---

### 5. Update Customer
**Endpoint:** `/api/customers/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "email": "string"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "email": "string"
}
```
**Description:** Updates an existing customer by their ID.

---

## Gallery APIs

### 1. Get All Galleries
**Endpoint:** `/api/galleries`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "productId": "UUID"
  }
]
```
**Description:** Retrieves a list of all galleries.

---

### 2. Get Gallery by ID
**Endpoint:** `/api/galleries/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "productId": "UUID"
}
```
**Description:** Retrieves a specific gallery by its ID.

---

### 3. Get Galleries by Product ID
**Endpoint:** `/api/galleries/product/{productId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "name": "string",
    "productId": "UUID"
  }
]
```
**Description:** Retrieves galleries associated with a specific product ID.

---

### 4. Add Gallery
**Endpoint:** `/api/galleries`  
**Method:** `POST`  
**Request Body:**
```json
{
  "name": "string",
  "productId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "productId": "UUID"
}
```
**Description:** Adds a new gallery.

---

### 5. Update Gallery
**Endpoint:** `/api/galleries/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "name": "string",
  "productId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "name": "string",
  "productId": "UUID"
}
```
**Description:** Updates an existing gallery by its ID.

---

## Notification APIs

### 1. Get All Notifications
**Endpoint:** `/api/notifications`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "message": "string",
    "accountId": "UUID"
  }
]
```
**Description:** Retrieves a list of all notifications.

---

### 2. Get Notification by ID
**Endpoint:** `/api/notifications/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "message": "string",
  "accountId": "UUID"
}
```
**Description:** Retrieves a specific notification by its ID.

---

### 3. Get Notifications by Account ID
**Endpoint:** `/api/notifications/account/{accountId}`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "message": "string",
    "accountId": "UUID"
  }
]
```
**Description:** Retrieves notifications associated with a specific account ID.

---

### 4. Add Notification
**Endpoint:** `/api/notifications`  
**Method:** `POST`  
**Request Body:**
```json
{
  "message": "string",
  "accountId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "message": "string",
  "accountId": "UUID"
}
```
**Description:** Adds a new notification.

---

### 5. Update Notification
**Endpoint:** `/api/notifications/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "message": "string",
  "accountId": "UUID"
}
```
**Response:**
```json
{
  "id": "UUID",
  "message": "string",
  "accountId": "UUID"
}
```
**Description:** Updates an existing notification by its ID.

---

## Order APIs

### 1. Get All Orders
**Endpoint:** `/api/orders`  
**Method:** `GET`  
**Response:**
```json
[
  {
    "id": "UUID",
    "customerId": "UUID",
    "total": "number"
  }
]
```
**Description:** Retrieves a list of all orders.

---

### 2. Get Order by ID
**Endpoint:** `/api/orders/{id}`  
**Method:** `GET`  
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "total": "number"
}
```
**Description:** Retrieves a specific order by its ID.

---

### 3. Add Order
**Endpoint:** `/api/orders`  
**Method:** `POST`  
**Request Body:**
```json
{
  "customerId": "UUID",
  "total": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "total": "number"
}
```
**Description:** Adds a new order.

---

### 4. Update Order
**Endpoint:** `/api/orders/{id}`  
**Method:** `PUT`  
**Request Body:**
```json
{
  "customerId": "UUID",
  "total": "number"
}
```
**Response:**
```json
{
  "id": "UUID",
  "customerId": "UUID",
  "total": "number"
}
```
**Description:** Updates an existing order by its ID.

---

### 5. Delete Order
**Endpoint:** `/api/orders/{id}`  
**Method:** `DELETE`  
**Response:**
```json
{
  "message": "Order deleted successfully."
}
```
**Description:** Deletes an order by its ID.

---
