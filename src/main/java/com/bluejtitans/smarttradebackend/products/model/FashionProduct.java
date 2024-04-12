package com.bluejtitans.smarttradebackend.products.model;
import java.util.List;

public class FashionProduct extends Product {
    private String brand;
    private String clothingType;
    private String size;
    private String color;
    private String department;

    public FashionProduct(String name, double price, int quantity, String description, String technicalSheet, List<String> images, String brand, String clothingType, String size, String color, String department) {
        super(name, price, quantity, description, technicalSheet, images);
        this.brand = brand;
        this.clothingType = clothingType;
        this.size = size;
        this.color = color;
        this.department = department;
    }

    //region Getters & setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClothingType() {
        return clothingType;
    }

    public void setClothingType(String clothingType) {
        this.clothingType = clothingType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //endregion
}
