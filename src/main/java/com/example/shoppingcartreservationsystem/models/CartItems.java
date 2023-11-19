package com.example.shoppingcartreservationsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="cartItem")
public class CartItems {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;
    @JsonIgnore
    private Long shoppingCartId;
    private Long productId;
    private String productName;
    private Integer productQuantity;
    private Double price;

    public Integer getCartItemId() {
        return cartItemId;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
