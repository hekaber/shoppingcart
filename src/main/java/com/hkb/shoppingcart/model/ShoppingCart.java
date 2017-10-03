package com.hkb.shoppingcart.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

    @Id
    private String id;

    public String status;

    public List<Product> products;

    @JsonDeserialize(using = MongoDateConverter.class)
    public Date lastModified;

    @JsonDeserialize(using = MongoDateConverter.class)
    public Date orderDate;

    //total price
    public float totalPrice = 0;

    public ShoppingCart(){}

    public ShoppingCart(String status){
        this.status = status;
        this.products = new ArrayList<Product>();
    }

    public ShoppingCart(String status, List<Product> products){
        this.status = status;
        this.products = products;
    }

    public String getId(){
        return this.id;
    }
}
