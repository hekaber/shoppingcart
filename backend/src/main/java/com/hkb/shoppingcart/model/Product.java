package com.hkb.shoppingcart.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

/**
 * Short one line description.                           (1)
 * <p>
 * Longer description. If there were any, it would be    (2)
 * here.
 * <p>
 * And even more explanations to follow in consecutive
 * paragraphs separated by HTML paragraph breaks.
 *
 * @return Description text text text.
 */

@Document(collection = "Products")
public class Product {

    @Id
    private String id;

    public String productName;
    public String productCode;

    public Date releaseDate;

    public Date lastModified;

    public String description;
    public int price = 0;
    public float starRating = 0;
    public String imageUrl;
    private int stock = 1000;

    public Product(){ }

    public Product(String productName, String productCode, int price,
                   Date releaseDate, String description, float starRating, String imageUrl, int stock){
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
        this.releaseDate = releaseDate;
        this.lastModified = new Date();
        this.description = description;
        this.starRating = starRating;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    public String getId(){
        return this.id;
    }

    public int getStock() { return this.stock; }

    public void setStock(int value) { this.stock = value; }

    public boolean removeStock(){
        if(this.stock > 0){
            this.stock--;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeStock(int value){
        if(this.stock > 0){
            this.stock -= value;
            return true;
        }
        else {
            return false;
        }
    }

    public void addStock(){
        this.stock++;
    }

    public void addStock(int value){
        this.stock += value;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, productName='%s', productCode='%s']",
                id, productName, productCode);
    }
}
