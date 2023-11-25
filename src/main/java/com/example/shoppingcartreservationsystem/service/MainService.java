package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MainService {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productServices;

    @Autowired
    private CartItemService cartItemService;


    public List<ShoppingCart> fetchAllCarts() {
        List<CartInfo> shoppingCart = this.shoppingCartService.findAll();
        List<CartItems> cartItems = this.cartItemService.findAllCartItems();

        return ShoppingCart.of(shoppingCart, cartItems);
    }

    public ShoppingCart findByCartId(Long shoppingCartId) {
        CartInfo shoppingCart = this.shoppingCartService.findByShoppingCartID(shoppingCartId);
        List<CartItems> cartItems = this.cartItemService.findAllByShoppingCartID(shoppingCartId);

        Double totalPrice = ShoppingCart.calFinalPrice(cartItems);

        return new ShoppingCart(shoppingCart, cartItems, totalPrice);
    }

    public ShoppingCart findByProductId(Long productId) {
        CartInfo shoppingCart = this.shoppingCartService.findByShoppingCartID(productId);
        List<CartItems> cartItems = this.cartItemService.findAllByShoppingCartID(productId);

        Double totalPrice = ShoppingCart.calFinalPrice(cartItems);

        return new ShoppingCart(shoppingCart, cartItems, totalPrice);
    }

    public ShoppingCart findByUserName(String userName) {
        Long cartId = this.shoppingCartService.findByUserName(userName);
        if (!Objects.nonNull(cartId)) {
            return null;
        }

        return this.findByCartId(cartId);
    }


    public Integer save(ShoppingCart shoppingCart) {
        final CartInfo cartInfo = shoppingCart.getCartInfo();
        final List<CartItems> cartItems = shoppingCart.getCartItems();
        Integer updateQueries = 0;
        // Fetching user
        final User user = this.userService.findByUserName(cartInfo.user.getUserName());

        //Inserting CartInfo
        updateQueries += this.shoppingCartService.save(cartInfo, user);

        // Fetching shopping cart Id to set it into the cart Items
        Long shoppingCartId = this.shoppingCartService.findByUserName(user.getUserName());

        //setting shopping cart Id into cart Items
        cartItems.forEach(cartItem -> cartItem.setShoppingCartId(shoppingCartId));

        // Inserting Cart Items
        updateQueries += this.cartItemService.save(cartItems);

        return updateQueries;
    }


    public Integer addCartItemsToCart(Long cartId, List<CartItems> cartItems, Product product) {

        cartItems.forEach(cartItem -> cartItem.setShoppingCartId(cartId));
        Integer quantity = cartItems.get(0).getProductQuantity();
        Integer updateQueries = this.cartItemService.save(cartItems);

        //update product stock
        product.setStock(product.getStock() - quantity);
        this.productServices.save(product);

        return updateQueries;
    }

    public Integer removeCartItemsFromCart(Long cartId, List<CartItems> cartItems) {
        cartItems.forEach(cartItem -> cartItem.setShoppingCartId(cartId));

        return this.cartItemService.removeCartItemsFromCart(cartItems);
    }

    public Integer updateStatus(ShoppingCart cart) {

        //fetching all cartitems
        List<CartItems> cartItems = this.cartItemService.findAllByShoppingCartID(cart.getCartInfo().getCartId());

        //updating cart items
        this.cartItemService.updateCartItemStatusAfterOrder(cartItems);

        // Updating cart
        return this.shoppingCartService.updateStatus(cart.getCartInfo());
    }

    public Integer deleteShoppingCart(Long cartId) {
        Integer UpdatedQueries = 0;
        this.shoppingCartService.deleteShoppingCart(cartId);
        UpdatedQueries += this.cartItemService.deleteCartItemsByShoppingCartId(cartId);

        return UpdatedQueries;
    }


    public List<CartItems> fetchAllOrderedCarts() {
        List<CartItems> cartItems = this.cartItemService.findAllCartItems();

        return cartItems;
    }
}
