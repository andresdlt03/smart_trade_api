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
    @Column(name = "min_price")
    private Double minPrice = 0.0;

    public Product(String name, String description, String dataSheet, List<String> photos, String category) {
        this.name = name;
        this.description = description;
        this.dataSheet = dataSheet;
        this.photos = photos;
        this.category = category;
    }

    public Product() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    @Override
    public String getDataSheet() {
        return null;
    }

    @Override
    public void setDataSheet(String dataSheet) {

    }

    @Override
    public List<String> getPhotos() {
        return null;
    }

    @Override
    public void setPhotos(List<String> photos) {

    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public void setCategory(String category) {

    }
}
