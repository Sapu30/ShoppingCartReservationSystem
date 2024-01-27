package com.example.shoppingcartreservationsystem.controller;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.repository.ProductRepository;
import com.example.shoppingcartreservationsystem.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;


    /* logger -> A logger in software development records messages
                 for debugging and monitoring application behavior. */
     private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    /**
     * get all products
     * @return list of products
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Product>> getAllProducts(){
        logger.info("---Getting all products---");
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()){
            logger.error("---Did not find any product---");
            throw new RuntimeException();
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    /**
     * create new product
     * @params Product
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> createProduct(@RequestBody Product input){
        logger.debug("---Adding new product '" + input.getProductName() +"'---");
        Product result = productService.createProduct(
                new Product(
                        input.getProductId(),
                        input.getProductName(),
                        input.getPrice(),
                        input.getStock()
                ));
        return new ResponseEntity<Product>(result, HttpStatus.CREATED);
    }


    /**
     * get product id
     * @params ProductId
     * @return Product
     */
    @RequestMapping(method = RequestMethod.GET, value = "/id/{productId}")
    ResponseEntity<?> getProductById(@PathVariable Long productId){
        logger.debug("---Getting product '" + productId +"'---");
        Product product = productService.getProductById(productId);

        if(product == null){
            logger.error("---Unable to get product'" + productId +"' not found---");
            throw new RuntimeException();
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }


    /**
     * get product name
     * @params ProductId
     * @return Product
     */
    @RequestMapping(method = RequestMethod.GET, value = "/name/{productName}")
    ResponseEntity<?> getProductByName(@PathVariable String productName){
        logger.debug("---Getting product '" + productName +"'---");
        Product product = productService.getProductByName(productName);

        if(product == null){
            logger.error("---Unable to get product'" + productName +"' not found---");
            throw new RuntimeException();
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    /**
     * update product name
     * @params ProductId
     * @return Product
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
    ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product input){
        logger.info("---Updating product '" + productId +"'---");
        Product product = productService.updateProduct(productId,input);

        if(product == null){
            logger.error("---Unable to update product'" + productId +"' not found---");
            throw new RuntimeException();
        }

        Product updated = productService.save(input);
        return new ResponseEntity<Product>(updated, HttpStatus.OK);

    }

    /**
     * delete product
     * @params ProductId
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
    ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        if(productService.exists(productId)){
            logger.debug("---Deleting product '" + productId +"'---");
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product '" + productId + "' deleted.");
        }
        else {
            throw new RuntimeException();
        }
    }

}
