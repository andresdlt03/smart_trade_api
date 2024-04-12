package com.bluejtitans.smarttradebackend.products.model;
import java.util.List;

public class FoodProduct extends Product {
    private String origin;
    private boolean packaged;
    private double weight;
    private List<String> ingredients;

    public FoodProduct(String name, double price, int quantity, String description, String technicalSheet, List<String> images, String origin, boolean packaged, double weight, List<String> ingredients) {
        super(name, price, quantity, description, technicalSheet, images);
        this.origin = origin;
        this.packaged = packaged;
        this.weight = weight;
        this.ingredients = ingredients;
    }
    // region Getters & setters
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean isPackaged() {
        return packaged;
    }

    public void setPackaged(boolean packaged) {
        this.packaged = packaged;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    //endregion
}