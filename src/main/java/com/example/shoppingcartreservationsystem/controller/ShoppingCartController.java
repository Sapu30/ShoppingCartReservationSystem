package com.example.shoppingcartreservationsystem.controller;

import com.example.shoppingcartreservationsystem.models.CartItems;
import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.CartInfo;
import com.example.shoppingcartreservationsystem.service.MainService;
import com.example.shoppingcartreservationsystem.service.ProductService;
import com.example.shoppingcartreservationsystem.service.ShoppingCartService;
import com.example.shoppingcartreservationsystem.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("carts")
public class ShoppingCartController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
    private final MainService mainService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productServices;

    public ShoppingCartController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ShoppingCart>> getShoppingCarts() {
        logger.info("---Getting all shopping carts---");
        List<ShoppingCart> carts = this.mainService.fetchAllCarts();
        if (carts.isEmpty()) {
            logger.error("---Did not find any shopping carts---");
            throw new RuntimeException("---Did not find any shopping carts---");
        }
        return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{shoppingCartId}")
    ResponseEntity<?> readShoppingCart(@PathVariable Long shoppingCartId) {
        logger.info("---Reading shopping cart '" + shoppingCartId + "'---");
        ShoppingCart cart = this.mainService.findByCartId(shoppingCartId);

        if (!Objects.nonNull(cart) || !Objects.nonNull(cart.getCartInfo())) {
            logger.error("---Unable to read shopping cart '" + shoppingCartId + "' not found---");
            throw new RuntimeException("---Did not find shopping carts for cartId'" + shoppingCartId + "' not found---");
        }

        return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    ResponseEntity<ShoppingCart> getShoppingCartsByUserName(@PathVariable String userName) {
        logger.info("---Getting all shopping carts for user '" + userName + "'---");
        ShoppingCart cart = this.mainService.findByUserName(userName);

        if (!Objects.nonNull(cart)) {
            logger.error("---Did not find shopping carts for user '" + userName + "'---");
            throw new RuntimeException("---Did not find shopping carts for user '" + userName + "'---");
        }
        return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody ShoppingCart input) {
        if (!Objects.nonNull(userService.findByUserName(input.getCartInfo().user.getUserName()))) {
            return ResponseEntity.ok("user not existed");
        }
        if (Objects.nonNull(shoppingCartService.findByUserName(input.getCartInfo().user.getUserName()))) {
            return ResponseEntity.ok("shoppingCart of the user already existed");
        }

        if (Objects.nonNull(this.mainService.save(input))) {
            return new ResponseEntity<>("Created Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{cartId}/cartItem")
    ResponseEntity<?> updatetProduct(@PathVariable Long cartId, @RequestBody List<CartItems> cartItems) {
        logger.info("---Adding cartItems to shopping cart " + cartId + "'---");
        ShoppingCart cart = this.mainService.findByCartId(cartId);

        if (!Objects.nonNull(cartId)) {
            logger.error("---Unable to update shopping cart'" + cartId + "' not found---");
            return new ResponseEntity<>("shoppingCart of the user does not existed", HttpStatus.NOT_IMPLEMENTED);
        }

        Long cartProductId = cartItems.get(0).getProductId();
        Product product = this.productServices.getProductById(cartProductId);

        if (Objects.nonNull(this.mainService.addCartItemsToCart(cartId, cartItems, product))) {
            return new ResponseEntity<>("Product Added Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}/cartItem")
    ResponseEntity<?> removeProduct(@PathVariable Long cartId, @RequestBody List<CartItems> CartItems) {
        logger.info("---Removing cartItems with shopping cart id" + cartId + "'---");
        ShoppingCart cart = this.mainService.findByCartId(cartId);

        if (!Objects.nonNull(cart)) {
            logger.error("---Unable to update shopping cart'" + cartId + "' not found---");
            return new ResponseEntity<>("shoppingCart of the user does not existed", HttpStatus.NOT_IMPLEMENTED);
        }

        if (Objects.nonNull(this.mainService.removeCartItemsFromCart(cartId, CartItems))) {
            return new ResponseEntity<>("Product Delete Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/{cartId}")
    ResponseEntity<?> order(@PathVariable Long cartId) {
        logger.info("---Ordering Shopping Cart with Cart id" + cartId + "'---");
        ShoppingCart cart = this.mainService.findByCartId(cartId);

        if (!Objects.nonNull(cart)) {
            logger.error("---Unable to find shopping cart'" + cartId + "' not found---");
            return new ResponseEntity<>("shoppingCart of the user does not existed", HttpStatus.NOT_IMPLEMENTED);
        }

        // setting status to Ordered
        cart.getCartInfo().setStatus(CartInfo.ORDERED);

        if (Objects.nonNull(this.mainService.updateStatus(cart))) {
            return new ResponseEntity<>("Status Updated Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}")
    public ResponseEntity<?> delete(@PathVariable Long cartId) {
        logger.info("---Deleting Shopping Cart with Cart id" + cartId + "'---");
        ShoppingCart cart = this.mainService.findByCartId(cartId);

        if (!Objects.nonNull(cart)) {
            logger.error("---Unable to find shopping cart'" + cartId + "' not found---");
            return new ResponseEntity<>("shoppingCart of the user does not existed", HttpStatus.NOT_IMPLEMENTED);
        }

        if (Objects.nonNull(this.mainService.deleteShoppingCart(cartId))) {
            return new ResponseEntity<>("Shopping Cart Deleted Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
