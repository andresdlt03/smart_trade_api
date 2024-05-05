package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Clothes extends Product {
    @Column
    private String size;

    public Clothes(String name, String description, String dataSheet, List<String> photos, String size) {
        super(name, description, dataSheet, photos);
        this.size = size;
    }

    public Clothes() {

    }
}
