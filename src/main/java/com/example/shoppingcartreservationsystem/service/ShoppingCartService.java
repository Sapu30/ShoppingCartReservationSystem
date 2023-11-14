package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.ShoppingCart;

import java.util.List;


public interface ShoppingCartService {

    public List<ShoppingCart> findAll();

    boolean findByUserName(String userName);

    ShoppingCart findByCartId(Long cartId);

    ShoppingCart save(ShoppingCart shoppingCart);

    void deleteShoppingCart(Long cartId);
}
