package com.example.shoppingcartreservationsystem.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private CartInfo cartInfo;
    private List<CartItems> cartItems;
    public double totalPrice = 0.0;

    public ShoppingCart() {

    }

    public ShoppingCart(CartInfo cartInfo, List<CartItems> cartItems, double totalPrice) {
        this.cartInfo = cartInfo;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public CartInfo getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(CartInfo cartInfo) {
        this.cartInfo = cartInfo;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //statics//
    public static List<ShoppingCart> of(List<CartInfo> cartInfos, List<CartItems> cartItems) {
        List<ShoppingCart> finalCarts = new ArrayList<>();

        for (CartInfo cartInfo : cartInfos) {
            ShoppingCart finalCart = new ShoppingCart();
            List<CartItems> filteredCartItems = cartItems.stream()
                    .filter(item -> item.getShoppingCartId().equals(cartInfo.getCartId())).toList();

            finalCart.setCartItems(filteredCartItems);
            finalCart.setTotalPrice(calFinalPrice(filteredCartItems));
            finalCart.setCartInfo(cartInfo);

            finalCarts.add(finalCart);
        }

        return finalCarts;
    }

    public static Double calFinalPrice(List<CartItems> cartItems) {

        Double finalPrice = 0.0;
        for (CartItems cart : cartItems) {
            finalPrice += cart.getPrice() * cart.getProductQuantity();
        }

        return finalPrice;
    }

}
