package com.bluejtitans.smarttradebackend.products.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Home extends Technology {
    public Home() {
        super();
    }
    public Home(String name, String description, double price, int quantity, String dataSheet,
                List<String> photos, int energyDrain, String model) {
        super(name, description, price, quantity, dataSheet, photos, energyDrain, model);
    }
}