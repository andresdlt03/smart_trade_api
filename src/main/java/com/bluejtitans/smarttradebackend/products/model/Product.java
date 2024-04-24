package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Product implements IProduct{
    @Id
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String dataSheet;
    private List<String> photos;

    public Product() {

    }
    public Product(String name, String description, double price, int quantity, String dataSheet, List<String> photos) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.dataSheet = dataSheet;
        this.photos = photos;
    }
}
