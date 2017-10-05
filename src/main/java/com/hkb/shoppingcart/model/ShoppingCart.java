package com.hkb.shoppingcart.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;

@Document(collection = "ShoppingCarts")
public class ShoppingCart {

    @Id
    private String id;

    public String status; //pending, ordered

    public String userName;

    public HashMap<String, Product> products;

    public Date lastModified;

    public Date orderDate;

    //total price
    public float totalPrice = 0;

    public ShoppingCart(){}

    public ShoppingCart(String status, String userName, HashMap<String, Product> products, Date orderDate, Date lastModified){
        this.status = status;
        this.userName = userName;
        this.products = products;
        this.orderDate = orderDate;
        this.lastModified = lastModified;
    }

    public String getId(){
        return this.id;
    }

    public Product getProductFromId(String productId){
        return this.products.get(productId);
    }
}
