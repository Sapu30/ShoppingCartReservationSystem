package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.CartInfo;
import com.example.shoppingcartreservationsystem.models.User;
import com.example.shoppingcartreservationsystem.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<CartInfo> findAll() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public CartInfo findByShoppingCartID(Long shoppingCartId) {
        return shoppingCartRepository.findByShoppingCartID(shoppingCartId);
    }

    @Override
    public Long findByUserName(String userName) {
        Long cartId = shoppingCartRepository.findByUserName(userName);

        return cartId;
    }

    @Override
    public CartInfo findByCartId(Long cartId) {
        List<CartInfo> shoppingCart = shoppingCartRepository.findByCartId(cartId);
        if (shoppingCart.isEmpty()) {
            throw new RuntimeException("Cart with ID " + cartId + " not found");
        }
        return shoppingCart.get(0);
    }

    @Transactional
    public Integer save(CartInfo cartInfo, User user) {
        return shoppingCartRepository.saveCartInfo(cartInfo, user.getUserId());
    }

    @Transactional
    public Integer updateStatus(CartInfo cartInfo) {
        return shoppingCartRepository.updateCartStatus(cartInfo);
    }

    @Transactional
    public Integer deleteShoppingCart(Long cartId) {
        return shoppingCartRepository.deleteCartInfo(cartId);
    }

    @Override
    public List<CartInfo> listOfCartItems(User user) {
        return shoppingCartRepository.findByUser(user);
    }



}
