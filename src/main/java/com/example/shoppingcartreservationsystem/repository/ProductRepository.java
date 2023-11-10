package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product p WHERE p.product_id = :productId")
    List<Product> findByProductId(Long productId);

    List<Product> findByProductName(String productName);
//    @Query("SELECT p FROM Product p WHERE p.product_name = :productName")
//    List<Product> findByProductName(String productName);

}