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
