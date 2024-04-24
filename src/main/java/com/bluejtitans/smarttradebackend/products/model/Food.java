package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Food extends Product {
    private int calories;
    public Food(){
        super();
    }
    public Food(String name, String description, double price, int quantity, String dataSheet, List<String> photos, int calories) {
        super(name, description, price, quantity, dataSheet, photos);
        this.calories = calories;
    }
}