package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ShoppingCartService {

    public List<ShoppingCart> findAll();

    List<ShoppingCart> findByUserName(String userName);

    ShoppingCart findByCartId(Long cartId);

    ShoppingCart save(ShoppingCart shoppingCart);

    void deleteShoppingCart(Long cartId);
}
