package com.hkb.shoppingcart.model;

import org.springframework.data.annotation.Id;


public class User {

    @Id
    private String id;

    public String firstName;
    public String lastName;
    public String email;

    public User(){}

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
