package com.example.shoppingcartreservationsystem.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    //    public String userName;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id")
//    public List<Product> products;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    public List<CartItems> cartItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

//    public int productQuantities;
    public int totalQuantity;
    public double totalPrice = 0.0;
//    public int totalPrice = 0;


    public ShoppingCart(){ }

    public ShoppingCart(String status, String userName,
                        List<CartItems> cartItem, User user,
                        int totalQuantity,
                        double totalPrice){
        this.status = status;
        this.cartItems = cartItem;
        this.user = user;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }


    public Long getCartId(){
        return cartId;
    }

    public void setCartId(Long cartId){
        this.cartId = cartId;
    }



//    public Long getProductFromId(){
//        return this.product.getProductId();
//    }

//    public void addProduct(Product product){
//
//        Product fromCart = products.get(products.getStock());
//
//        if(fromCart == null){
//            this.products.put(product.getProductId(), product);
//        }
//
//    }
//    public void addProduct(Product product) {
////        if (products == null) {
////            products = new ArrayList<>();
////        }
//
//        // Check if the product is already in the cart by comparing product IDs.
//        boolean productExistsInCart = products.stream()
//                .anyMatch(p -> p.getProductId().equals(product.getProductId()));
//
//        // If the product doesn't exist in the cart, add it.
//        if (!productExistsInCart) {
//            products.add(product);
//        }
//    }


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


//    public void addProductQuantity(Product productToAdd) {
//        Long productIdToAdd = productToAdd.getProductId();
//
//        // Check if the product is already in the cart
//        for (Product product : products) {
//            if (product.getProductId().equals(productIdToAdd)) {
//                // Product found in the cart, increment the quantity
//
//                int newQuantity = this.getTotalQuantity() + 1;
//                this.setTotalQuantity(newQuantity);
////                product.setProductQuantity(product.getProductQuantity()+1);
////                //remove stock
//                product.setStock(product.getStock() - 1);
//                this.totalPrice += product.getPrice();
//                return; // Exit the method
//            }
//        }
//
//
//        // If the product is not in the cart, add it
//        productToAdd.setStock(1); // Assuming you have a set method for quantity
//        products.add(productToAdd);
//        this.totalPrice += productToAdd.getPrice();
//    }
//
//
//    public void removeProduct(Long productId){
//        if(this.products.contains(productId)){
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

//    public void removeProductQuantity(Product productToRemove) {
//        // Find the productToRemove in the products list.
//        for (Product product : products) {
//            if (product.getProductId().equals(productToRemove.getProductId())) {
//                // Decrement the product quantity.
//                int quantity = product.getStock();
//                if (quantity > 1) {
//                    product.setStock(quantity - 1);
//                } else {
//                    // Remove the product from the list when quantity is 1.
//                    products.remove(product);
//                }
//
//                // Update the total price.
//                totalPrice -= productToRemove.getPrice();
//                break; // Exit the loop since we found and processed the product.
//            }
//        }
//    }


//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public int getTotalQuantity() {
//        return totalQuantity;
//    }
//
//    public void setTotalQuantity(int totalQuantity) {
//        this.totalQuantity = totalQuantity;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }


}