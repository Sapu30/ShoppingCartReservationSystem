# Shopping Cart Reservation System

<pre>
     _______. __    __    ______   .______   .______    __  .__   __.   _______      ______     ___      .______     .___________.        _______.____    ____  _______.___________. _______ .___  ___. 
    /       ||  |  |  |  /  __  \  |   _  \  |   _  \  |  | |  \ |  |  /  _____|    /      |   /   \     |   _  \    |           |       /       |\   \  /   / /       |           ||   ____||   \/   | 
   |   (----`|  |__|  | |  |  |  | |  |_)  | |  |_)  | |  | |   \|  | |  |  __     |  ,----'  /  ^  \    |  |_)  |   `---|  |----`      |   (----` \   \/   / |   (----`---|  |----`|  |__   |  \  /  | 
    \   \    |   __   | |  |  |  | |   ___/  |   ___/  |  | |  . `  | |  | |_ |    |  |      /  /_\  \   |      /        |  |            \   \      \_    _/   \   \       |  |     |   __|  |  |\/|  | 
.----)   |   |  |  |  | |  `--'  | |  |      |  |      |  | |  |\   | |  |__| |    |  `----./  _____  \  |  |\  \----.   |  |        .----)   |       |  | .----)   |      |  |     |  |____ |  |  |  | 
|_______/    |__|  |__|  \______/  | _|      | _|      |__| |__| \__|  \______|     \______/__/     \__\ | _| `._____|   |__|        |_______/        |__| |_______/       |__|     |_______||__|  |__| 
                                                                                                                                                                                                        
</pre>

A shopping cart reservation system with CRUD functionality, designed to manage product reservations seamlessly.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
- [Examples](#Examples)

## Introduction

  This system is desing keeping customers and mostly administrators in mind, to have an effective reservation system.
  The Shopping Cart Reservation System aims to address this need by offering a seamless experience for users looking to reserve products and for administrators managing the reservation process.

  I developed a Shopping Cart Reservation System using Java 17, Spring Boot, MySQL, Maven, Spring Data JPA, and JWT authentication.
The main purpose of this project was to allow users to add products to a cart, reserve items, update cart items, delete items, and finally place an order.

I created REST APIs for user signup, cart creation, fetching carts by cart ID or username, adding products to a cart, deleting products from a cart, placing orders, and deleting carts.

For database operations, I used Spring Data JPA with MySQL. For security, I implemented login authentication using JWT token, so only authenticated users can access protected APIs.

The main entities were User, Cart, CartItem, Product, and Order. The cart maintained the selected products and their reservation status, so admins or users could track whether a product was reserved, updated, deleted, or ordered.

This project helped me understand how to design CRUD APIs, manage relationships between entities, handle authentication, and build a backend flow similar to an e-commerce cart system.

## Features

- **CRUD Operations:** Create, Read, Update, and Delete operations for managing product reservations.
- **Login Authentication:** An intuitive authentication system which provide JWT token for better security.
- **Reservation Management:** Efficient handling of product reservations with clear status tracking.

## Technologies Used

List the key technologies and frameworks used in your project. For example:

- **Backend:**
    - Java 17 
    - Spring Boot
    - MySQL
    - Maven

- **Frontend:**
    - TBD

## Installation

Follow step-by-step instructions on how to install and set up the project locally. Include any dependencies and prerequisites that users need to install.

```bash
# Clone the repository
git clone https://github.com/Sapu30/ShoppingCartReservationSystem.git

# Navigate to the project directory
cd your-project

# Install dependencies
Build your project and set JAVA version as 17

```
## API Endpoints

Below are the key API endpoints provided by the Shopping Cart Reservation System:

| Endpoint     | Method | Description                |
|--------------|--------|----------------------------|
| `/users/signup` | POST   | Inserting a new User       |
| `/carts`     | GET    | Get all the Carts          |
| `/carts/{id}` | GET    | Get a cart by Cart Id      |
| `/api/user/{userName}` | GET    | Get a cart by username     |
| `/carts`     | POST   | Add a new Cart             |
| `/carts/{cartId}/cartItem` | POST   | Update a product in a cart |
| `/carts/{cartId}/cartItem`    | DELETE | Delete a product in a cart |
| `/order/{cartId}` | POST   | To Order a cart            |
| `/{cartId}` | DELETE | Delete a cart by ID        |


                    +----------------+
                    |      User      |
                    +----------------+
                            |
                            v
                    +----------------+
                    |  Frontend/UI   |
                    +----------------+
                            |
                            v
                    +----------------+
                    |  API Gateway   |
                    | Auth Routing   |
                    | Rate Limiting  |
                    +----------------+
                            |
                            v
                    +----------------+
                    | Load Balancer  |
                    +----------------+
                            |
        ------------------------------------------------
        |                         |                    |
        v                         v                    v
+----------------+       +----------------+     +----------------+
| Auth Service   |       | Cart Service   |     | Order Service  |
| Signup/Login   |       | Create Cart    |     | Place Order    |
| JWT Token      |       | Add/Delete Item|     | Order Status   |
+----------------+       +----------------+     +----------------+
        |                         |                    |
        --------------------------|---------------------
                                  v
                          +----------------+
                          | Reservation    |
                          | Logic          |
                          | Check Stock    |
                          | Reserve Item   |
                          +----------------+
                                  |
                                  v
                          +----------------+
                          | Product/Stock  |
                          | Service        |
                          +----------------+
                                  |
                                  v
                          +----------------+
                          | MySQL Database |
                          | User           |
                          | Cart           |
                          | CartItem       |
                          | Product        |
                          | Order          |
                          +----------------+



### Examples

#### POST `/users/signup`

Sign Up a new user.

```bash
curl --location 'http://localhost:8080/users/signup' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic QW5rdXNoOmJsYWhiYWg=' \
--data-raw '{
    "email":"sk02@gmail.com",
    "firstName": "Tony",
    "lastName":"Stark",
    "password":"arc",
    "userName":"tonny"

}'
