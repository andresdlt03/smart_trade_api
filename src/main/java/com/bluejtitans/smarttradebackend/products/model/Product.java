package com.bluejtitans.smarttradebackend.products.model;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
public class Product implements IProduct{
    @Id
    private String name;
    private double price;
    private int quantity;
    private String description;
    private String technicalSheet;
    private List<String> images;

    public Product(String name, double price, int quantity, String description, String technicalSheet, List<String> images) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.technicalSheet = technicalSheet;
        this.images = images;
    }
    // region Getters & setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTechnicalSheet() {
        return technicalSheet;
    }
    public void setTechnicalSheet(String technicalSheet) {
        this.technicalSheet = technicalSheet;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
//endregion
}