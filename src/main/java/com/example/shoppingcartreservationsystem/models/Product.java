package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private double price;
    private int stock;

//    private int productQuantity;


    public Product(){ }

    public Product(Long productId, String productName, double price, int stock){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

//    public void setProductName(String productName) {
//        this.productName = productName;
//    }

    public double getPrice() {
        return price;
    }

//    public void setPrice(double price) {
//        this.price = price;
//    }

    public int getStock() {
        return stock;
    }


    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean removeStock() {
        return false;
    }

    public void addStock() {
    }


//    public int getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(int productQuantity) {
//        this.productQuantity = productQuantity;
//    }

}