package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.*;
import java.util.HashMap;

public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;
    public String userName;
    public HashMap<String, Product> products;
    private int quantity;
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, Product> getProduct() {
        return products;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}