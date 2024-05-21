package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements IProduct{
    @Id
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "data_sheet")
    private String dataSheet;
    @ElementCollection
    private List<String> photos;
    @Column(name = "verified")
    private Boolean verified = false;
    @Column(name = "category")
    private String category;
    @Column(name = "price")
    private Double price = 0.0;
    @ElementCollection
    private List<Double> historyPrice = new ArrayList<>();

    public Product(String name, String description, String dataSheet, List<String> photos, String category) {
        this.name = name;
        this.description = description;
        this.dataSheet = dataSheet;
        this.photos = photos;
        this.category = category;
    }

    public Product() {

    }

    public Double getLastHistoryPrice() {
        return historyPrice.get(historyPrice.size() - 1);
    }

    public void addToHistoryPrice(Double price) {
        historyPrice.add(price);
    }
}
