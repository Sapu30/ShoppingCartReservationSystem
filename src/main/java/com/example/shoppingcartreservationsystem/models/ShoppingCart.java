package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name="shoppingCart")
public class ShoppingCart {

    public static String ORDERED = "ordered";
    public static String PENDING = "pending";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    public String status;
    public String userName;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Product> products;
    public int productQuantities;
    public int totalPrice = 0;

    public ShoppingCart(){ }

    public ShoppingCart(String status, String userName,
                        List<Product> products,
                        int productQuantities,
                        int totalPrice){
        this.status = status;
        this.userName = userName;
        this.products = products;
        this.productQuantities = productQuantities;
        this.totalPrice = totalPrice;
    }

    public Long getCartId(){
        return this.cartId;
    }

    public Product getProductFromId(String productId){
        return this.products.get(Integer.parseInt(productId));
    }

//    public void addProduct(Product product){
//
//        Product fromCart = this.products.get(products.getProductId());
//
//        if(fromCart == null){
//            this.products.put(product.getProductId(), product);
//        }
//
//    }
    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }

        // Check if the product is already in the cart by comparing product IDs.
        boolean productExistsInCart = products.stream()
                .anyMatch(p -> p.getProductId().equals(product.getProductId()));

        // If the product doesn't exist in the cart, add it.
        if (!productExistsInCart) {
            products.add(product);
        }
    }


//    public void addProductQuantity(Product product){
//        Long productId = product.getProductId();
//        if(this.productQuantities.containsKey(productId)){
//            int quantity = this.productQuantities.get(productId);
//            quantity++;
//            this.productQuantities.put(productId, quantity);
//        }
//        else {
//            // init the product quantities if key not found
//            this.productQuantities.put(productId, 1);
//        }
//        this.totalPrice += product.getPrice();
//    }

    public void addProductQuantity(Product productToAdd) {
        Long productIdToAdd = productToAdd.getProductId();

        // Check if the product is already in the cart
        for (Product product : products) {
            if (product.getProductId().equals(productIdToAdd)) {
                // Product found in the cart, increment the quantity
                int newQuantity = product.getStock() + 1;
                product.setStock(newQuantity);
                this.totalPrice += product.getPrice();
                return; // Exit the method
            }
        }

        // If the product is not in the cart, add it
        productToAdd.setStock(1); // Assuming you have a set method for quantity
        products.add(productToAdd);
        this.totalPrice += productToAdd.getPrice();
    }




//
//    public void removeProduct(Long productId){
//        if(this.products.containsKey(productId)){
//            this.products.remove(productId);
//        }
//    }
//
//    public void removeProductQuantity(Product product){
//
//        Long productId = product.getProductId();
//        if(this.productQuantities.containsKey(productId)){
//            int quantity = this.productQuantities.get(productId);
//            quantity--;
//            //remove datas from the HashMaps when quantity is too low
//            if(quantity <1){
//                this.productQuantities.remove(productId);
//                this.removeProduct(productId);
//            }
//            else {
//                this.productQuantities.put(productId, quantity);
//            }
//            this.totalPrice -= product.getPrice();
//        }
//    }

    public void removeProductQuantity(Product productToRemove) {
        // Find the productToRemove in the products list.
        for (Product product : products) {
            if (product.getProductId().equals(productToRemove.getProductId())) {
                // Decrement the product quantity.
                int quantity = product.getStock();
                if (quantity > 1) {
                    product.setStock(quantity - 1);
                } else {
                    // Remove the product from the list when quantity is 1.
                    products.remove(product);
                }

                // Update the total price.
                totalPrice -= productToRemove.getPrice();
                break; // Exit the loop since we found and processed the product.
            }
        }
    }

}