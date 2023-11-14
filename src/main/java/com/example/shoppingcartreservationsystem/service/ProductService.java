package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Product;

import java.util.List;


public interface ProductService {

//    @Autowired
//    ProductRepository productRepository;

//    public abstract List<Product> getAllProducts();

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product getProductById(Long productId);

//    public Product getProductByName(String productName);


    Product updateProduct(Long id, Product product);

    Product save(Product input);

    boolean exists(Long productId);

    void deleteProduct(Long productId);

    Product findByProductId(Long productId);

    void addStock(List<Product> productList);

    Product getProductByName(String productName);
}
