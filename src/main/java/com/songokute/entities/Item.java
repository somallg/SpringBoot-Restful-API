package com.songokute.entities;

import java.io.Serializable;

/**
 * Created by Admin on 6/18/2016.
 */
public class Item implements Serializable {
    String code;
    String name;
    String description;
    float price;

    public String getCode() {
        return code;
    }

    public Item setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public Item setPrice(float price) {
        this.price = price;
        return this;
    }
}
