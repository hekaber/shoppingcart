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
 * @param  variable Description text text text.          (3)
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
    public float price = 0;
    public float starRating = 0;
    public String imageUrl;

    public Product(){ }

    public Product(String productName, String productCode, float price,
                   Date releaseDate, String description, float starRating, String imageUrl){
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
        this.releaseDate = releaseDate;
        this.lastModified = new Date();
        this.description = description;
        this.starRating = starRating;
        this.imageUrl = imageUrl;
    }

    public String getId(){
        return this.id;
    }
}
