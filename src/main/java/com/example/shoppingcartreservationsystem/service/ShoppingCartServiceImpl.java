package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {


    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<ShoppingCart> findAll(){
        return shoppingCartRepository.findAll();
    }

    @Override
    public boolean findByUserName(String userName) {
        List<String> userNames = shoppingCartRepository.findByUserName();
        String filterUserName =  userNames.stream().filter(u -> u.equals(userName)).findFirst().orElse(null);
        if(filterUserName == null){
            return false;
        }
        return true;
    }

    @Override
    public ShoppingCart findByCartId(Long cartId) {
        List<ShoppingCart> shoppingCart = shoppingCartRepository.findByCartId(cartId);
        if (shoppingCart.isEmpty()) {
            throw new RuntimeException("Cart with ID " + cartId + " not found");
        }
        return shoppingCart.get(0);
    }

    @Override
    @Transactional
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteShoppingCart(Long cartId) {

    }


}
