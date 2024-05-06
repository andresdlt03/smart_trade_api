package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Food extends Product {
    @Column
    private String calories;

    public Food(String name, String description, String dataSheet, List<String> photos, String calories) {
        super(
            name,
            description,
            dataSheet,
            photos,
                "food"
        );
        this.calories = calories;
    }

    public Food() {

    }
}