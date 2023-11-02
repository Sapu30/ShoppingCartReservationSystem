package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    public List<ShoppingCart> findAll();

    public List<ShoppingCart> findByUserName(String userName);


}