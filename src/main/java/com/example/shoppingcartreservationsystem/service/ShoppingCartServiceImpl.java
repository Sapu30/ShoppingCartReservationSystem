package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.repository.ProductRepository;
import com.example.shoppingcartreservationsystem.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> findAll(){
        return shoppingCartRepository.findAll();
    }

    @Override
    public List<ShoppingCart> findByUserName(String userName) {
        return null;
    }

    @Override
    public ShoppingCart findByCartId(Long cartId) {
        return null;
    }

    @Override
    public ShoppingCart save(ShoppingCart ShoppingCart) {
        return null;
    }

    @Override
    public void deleteShoppingCart(Long cartId) {

    }


}
