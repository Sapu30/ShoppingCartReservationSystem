package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name="cartItem")

public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    private Long cartId;

    private Long productId;
    private String productName;
    private int productQuantity;
    private double price;

}
