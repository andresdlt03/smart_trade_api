package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public abstract class Clothes extends Product {
    private String size;
    public Clothes() {
        super();
    }
    public Clothes(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size) {
        super(name, description, price, quantity, dataSheet, photos);
        this.size = size;
    }
}
