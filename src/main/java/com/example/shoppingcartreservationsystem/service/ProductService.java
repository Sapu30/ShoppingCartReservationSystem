package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        List<Product> products = productRepository.findByProductId(productId);
        if (products.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
        return products.get(0);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setProductId(id);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
