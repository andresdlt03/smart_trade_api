package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Book extends Product {
    private String ISBN;
    public Book() {
        super();
    }
    public Book(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String ISBN) {
        super(name, description, price, quantity, dataSheet, photos);
        this.ISBN = ISBN;
    }
}