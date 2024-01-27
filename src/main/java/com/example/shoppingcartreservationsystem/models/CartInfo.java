package com.example.shoppingcartreservationsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="shoppingCart")
public class CartInfo {

    public static String ORDERED = "ordered";
    public static String PENDING = "pending";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    public String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    public CartInfo(){ }

    public CartInfo(String status, User user){
        this.status = status;
        this.user = user;
    }

    public Long getCartId(){
        return cartId;
    }

    public void setCartId(Long cartId){
        this.cartId = cartId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}