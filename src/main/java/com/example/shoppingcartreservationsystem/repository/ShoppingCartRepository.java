package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    List<ShoppingCart> findAll();

    @Query(value = "SELECT s.user_name FROM shopping_cart s INNER JOIN users u ON s.user_name = u.user_name;", nativeQuery = true)
    List<String> findByUserName();

    List<ShoppingCart> findByCartId(Long CartId);


}