package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        List<Product> products = productRepository.findByProductId(productId);
        if (products.isEmpty()) {
            throw new RuntimeException("Product with ID " + productId + " not found");
        }
        return products.get(0);
    }

    public Product getProductByName(String productName) {
        List<Product> products = productRepository.findByProductName(productName);
        if (products.isEmpty()) {
            throw new RuntimeException("Product with ID " + productName + " not found");
        }
        return products.get(0);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        if (productRepository.existsById(productId)) {
            product.setProductId(productId);
            return productRepository.save(product);
        }
        return null;
    }

    @Override
    public Product save(Product input) {
        return productRepository.save(input);
    }

    public boolean exists(Long productId) {
        return true;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Object findByProductId(Long productId) {
        return null;
    }

    @Override
    public void addStock(List<Product> productList) {

    }


}
