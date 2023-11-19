package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.CartInfo;
import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.models.User;

import java.util.List;


public interface ShoppingCartService {

    public List<CartInfo> findAll();

    CartInfo findByShoppingCartID(Long shoppingCartId);

    Long findByUserName(String userName);

    Integer save(CartInfo cartInfo, User user);

    Integer updateStatus(CartInfo cartInfo);

    Integer deleteShoppingCart(Long cartId);


    CartInfo findByCartId(Long cartId);

    List<CartInfo> listOfCartItems(User user);


}
