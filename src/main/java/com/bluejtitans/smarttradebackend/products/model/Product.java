package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements IProduct{
    @Id
    private String name;
    private String description;
    private String dataSheet;
    @ElementCollection
    private List<String> photos;
    private Boolean verified = false;

    public Product(String name, String description, String dataSheet, List<String> photos) {
        this.name = name;
        this.description = description;
        this.dataSheet = dataSheet;
        this.photos = photos;
    }

    public Product() {

    }
}
