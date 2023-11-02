package com.example.shoppingcartreservationsystem.controller;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import com.example.shoppingcartreservationsystem.service.ProductService;
import com.example.shoppingcartreservationsystem.service.ShoppingCartService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("carts")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
    private ProductService productServices;

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<ShoppingCart>> getShoppingCarts(){
        logger.info("---Getting all shopping carts---");
        List<ShoppingCart> carts = this.shoppingCartService.findAll();

        if(carts.isEmpty()){
            logger.error("---Did not find any shopping carts---");
            throw new RuntimeException();
        }
        return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    ResponseEntity<List<ShoppingCart>> getShoppingCartsByUserName(@PathVariable String userName) {
        logger.info("---Getting all shopping carts for user '"+ userName +"'---");
        List<ShoppingCart> carts = this.shoppingCartService.findByUserName(userName);

        if(carts.isEmpty()){
            logger.error("---Did not find shopping carts for user '"+ userName +"'---");
            throw new RuntimeException(userName);
        }
        return new ResponseEntity<List<ShoppingCart>>(carts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cartId}")
    ResponseEntity<?> readShoppingCart(@PathVariable Long cartId){
        logger.info("---Reading shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);

        if(cart == null){
            logger.error("---Unable to read shopping cart '" + cartId +"' not found---");
            throw new RuntimeException(String.valueOf(cartId));
        }

        return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody ShoppingCart input, UriComponentsBuilder ucBuilder){
        ShoppingCart result = this.shoppingCartService.save(
                new ShoppingCart(
                        ShoppingCart.PENDING,
                        input.userName,
                        input.products,
                        input.productQuantities,
                        input.totalPrice
                ));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/carts/{id}").buildAndExpand(result.getCartId()).toUri());
        return new ResponseEntity<ShoppingCart>(result, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{cartId}/product/{productId}")
    ResponseEntity<?> addProduct(@PathVariable Long cartId, @PathVariable Long productId){
        logger.info("---Adding product'" + productId +"' to shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);

        if(cart == null){
            logger.error("---Unable to update shopping cart'" + cartId +"' not found---");
            throw new RuntimeException(String.valueOf(cartId));
        }

        Product product = (Product) this.productServices.findByProductId(productId);

        if(product == null){
            logger.error("---Unable to update product'" + productId +"' not found---");
            throw new RuntimeException(String.valueOf(productId));
        }

        cart.addProduct(product);
        cart.addProductQuantity(product);
        //update product stock
        if(!product.removeStock()){
            return new ResponseEntity<ShoppingCart>(cart, HttpStatus.ACCEPTED);
        }

        ShoppingCart updated = this.shoppingCartService.save(cart);
        this.productServices.save(product);

        return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}/product/{productId}")
    ResponseEntity<?> removeProduct(@PathVariable Long cartId, @PathVariable Long productId){
        logger.info("---Removing product'" + productId +"' from shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);

        if(cart == null){
            logger.error("---Unable to update shopping cart'" + cartId +"' not found---");
            throw new RuntimeException(String.valueOf(cartId));
        }

        Product product = (Product) this.productServices.findByProductId(productId);

        if(product == null){
            logger.error("---Unable to update product'" + productId +"' not found---");
            throw new RuntimeException(String.valueOf(productId));
        }

        cart.removeProductQuantity(product);

        product.addStock();

        ShoppingCart updated = this.shoppingCartService.save(cart);
        this.productServices.save(product);

        return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{cartId}")
    ResponseEntity<?> update(@RequestBody ShoppingCart input, @PathVariable Long cartId){
        logger.info("---Updating shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);

        if(cart == null){
            logger.error("---Unable to update shopping cart'" + cartId +"' not found---");
            throw new RuntimeException(String.valueOf(cartId));
        }

        ShoppingCart updated = this.shoppingCartService.save(input);
        return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/order/{cartId}")
    ResponseEntity<?> order(@RequestBody ShoppingCart input, @PathVariable Long cartId){
        logger.info("---Placing order on shopping cart '" + cartId +"'---");
        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);
        if(cart == null){
            logger.error("---Unable to place order on shopping cart'" + cartId +"' not found---");
            throw new RuntimeException(String.valueOf(cartId));
        }
        //we only get the status and total price info from the input
        cart.status = ShoppingCart.ORDERED;

        ShoppingCart updated = this.shoppingCartService.save(cart);
        return new ResponseEntity<ShoppingCart>(updated, HttpStatus.OK);
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/{cartId}")
//    ResponseEntity<?> delete(@PathVariable Long cartId){
//        logger.info("---Deleting shopping cart '" + cartId +"'---");
//        ShoppingCart cart = this.shoppingCartService.findByCartId(cartId);
//
//        if(cart == null){
//            logger.error("---Unable to delete shopping cart '" + cartId +"' not found---");
//            throw new RuntimeException(String.valueOf(cartId));
//        }
//
//        if(!cart.productQuantities.isEmpty()){
//            List<Product> productList = new ArrayList<Product>();
//            for(Map.Entry<Long, Integer> entry: cart.productQuantities.entrySet()){
//                Product product = (Product) this.productServices.findByProductId(entry.getKey());
//                if(product != null){
//                    product.addStock();
//                    productList.add(product);
//                }
//            }
//            if(!productList.isEmpty()) this.productServices.addStock(productList);
//        }
//
//        this.shoppingCartService.deleteShoppingCart(cartId);
//        return new ResponseEntity<ShoppingCart>(HttpStatus.NO_CONTENT);
//
//    }
}
