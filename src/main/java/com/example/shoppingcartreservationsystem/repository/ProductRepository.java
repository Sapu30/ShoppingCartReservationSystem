package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByProductId(Long productId);
    public List<Product> findByProductName(String productName);
    public List<Product> findAll();

}