package com.hkb.shoppingcart.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class CartUser {

    @Id
    private String id;

    public String firstName;
    public String lastName;
    private String userName;
    private String password;
    public String email;

    public CartUser(){}

    public CartUser(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId(){
        return this.id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
                "CartUser[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
