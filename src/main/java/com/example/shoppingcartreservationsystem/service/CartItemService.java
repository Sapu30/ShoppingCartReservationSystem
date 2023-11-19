package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.CartItems;
import com.example.shoppingcartreservationsystem.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    //TODO: Implement group by in query
    public List<CartItems> findAllCartItems() {
        return cartItemRepository.findAllCartItems();
    }

    public List<CartItems> findAllByShoppingCartID(Long shoppingCartId) {
        return cartItemRepository.findAllByShoppingCartID(shoppingCartId);
    }

    @Transactional
    public Integer save(List<CartItems> cartItems) {

        List<CartItems> persistedCartItems = this.findAllByShoppingCartID(cartItems.get(0).getShoppingCartId());
        Integer updateQueries = 0;
        // Upating quantities

        for (CartItems cartItem : cartItems) {
            // Finding if same product exist in the database
            CartItems filterdCart = persistedCartItems.stream()
                    .filter(persistedCartItem -> persistedCartItem.getProductId().equals(cartItem.getProductId()))
                    .findAny().orElse(null);

            // IF YES, updating quantity
            if (Objects.nonNull(filterdCart)) {
                Integer updateQuantity = filterdCart.getProductQuantity() + cartItem.getProductQuantity();
                cartItem.setProductQuantity(updateQuantity);

                //Droppping the old product
                updateQueries += cartItemRepository.removeCartItem(cartItem);

                //Inserting the upated product
                updateQueries += cartItemRepository.saveCartItems(cartItem);
            } else {
                updateQueries += cartItemRepository.saveCartItems(cartItem);
            }
        }

        return updateQueries;
    }

    @Transactional
    public Integer removeCartItemsFromCart(List<CartItems> cartItems) {
        Integer updateQueries = 0;
        for (CartItems cartItem : cartItems) {
            updateQueries += cartItemRepository.removeCartItem(cartItem);
        }

        return updateQueries;
    }

    @Transactional
    public Integer deleteCartItemsByShoppingCartId(Long shoppingCartId) {
        return cartItemRepository.DeleteByShoppingCartId(shoppingCartId);
    }
}
