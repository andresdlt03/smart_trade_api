package com.bluejtitans.smarttradebackend.products.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class Technology extends Product {
    private int energyDrain;
    private String model;
    public Technology() {
        super();
    }
    public Technology(String name, String description, double price, int quantity, String dataSheet,
                           List<String> photos, int energyDrain, String model) {
        super(name, description, price, quantity, dataSheet, photos);
        this.energyDrain = energyDrain;
        this.model = model;
    }
    
}
