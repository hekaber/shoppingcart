package com.hkb.shoppingcart.controller;

import com.hkb.shoppingcart.ShoppingcartApplication;
import com.hkb.shoppingcart.exceptions.ProductNotFoundException;
import com.hkb.shoppingcart.model.Product;
import com.hkb.shoppingcart.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    ProductRestController(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Product> getProducts(){
        logger.debug("---Getting all products---");
        return this.productRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Product input){
        logger.debug("---Adding new product '" + input.productName +"'---");
        Product result = this.productRepository.save(
                new Product(
                        input.productName,
                        input.productCode,
                        input.price,
                        input.releaseDate,
                        input.description,
                        input.starRating,
                        input.imageUrl
                ));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    Product readProduct(@PathVariable String productId){
        logger.debug("---Reading product '" + productId +"'---");
        Product product = this.productRepository.findOne(productId);

        if(product != null){
            return product;
        }
        else {
            throw new ProductNotFoundException(productId);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
    ResponseEntity<?> delete(@PathVariable String productId){
        if(this.productRepository.exists(productId)){
            logger.debug("---Deleting product '" + productId +"'---");
            return ResponseEntity.ok("Product '" + productId + "' deleted.");
        }
        else {
            throw new ProductNotFoundException(productId);
        }
    }
}
