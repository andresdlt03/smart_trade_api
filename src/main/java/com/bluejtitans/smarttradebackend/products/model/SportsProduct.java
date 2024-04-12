package com.bluejtitans.smarttradebackend.products.model;
import java.util.List;

public class SportsProduct extends Product {
    private String type;
    private String brand;
    private String model;

    public SportsProduct(String name, double price, int quantity, String description, String technicalSheet, List<String> images, String type, String brand, String model) {
        super(name, price, quantity, description, technicalSheet, images);
        this.type = type;
        this.brand = brand;
        this.model = model;
    }

    //region Getters & setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    //endregion
}
