package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Sweatshirt extends Clothes {
    public Sweatshirt(){
        super();
    }
    public Sweatshirt(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size){
        super(name, description, price, quantity, dataSheet, photos, size);
    }
}