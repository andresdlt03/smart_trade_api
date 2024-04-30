package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ourProducts.model.Product;

@Getter
@Setter
@Entity
@Table(name = "Shopping_Cart")
public class ShoppingCart extends ProductList {
    @Column(name = "total_price")
    private double totalPrice;

    @Override
    public void addProduct(Product product) {
        super.addProduct(product);
        updatePrice();
    }

    @Override
    public Product removeProduct(Product product) {
        Product removed = super.removeProduct(product);
        updatePrice();
        return removed;
    }

    public void updatePrice() {
        for (Product p : this.getProducts()) {
            totalPrice += p.getPrice();
        }
    }
}