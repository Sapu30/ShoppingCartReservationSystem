package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.models.User;

import java.util.List;


public interface ShoppingCartService {

    public List<ShoppingCart> findAll();

    boolean findByUserName(String userName);

    ShoppingCart findByCartId(Long cartId);

    ShoppingCart save(ShoppingCart shoppingCart);

   List<ShoppingCart> listOfCartItems(User user);

    void deleteShoppingCart(Long cartId);
}
