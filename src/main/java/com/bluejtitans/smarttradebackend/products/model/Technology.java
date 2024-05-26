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
public class Technology extends Product {
    @Column
    private String consume;

    @Column
    private String model;

    public Technology(String name, String description, String dataSheet, byte[] photo, String consume, String model) {
        super(
            name,
            description,
            dataSheet,
            photo,
                "technology"
        );
        this.consume = consume;
        this.model = model;
    }

    public Technology() {

    }
}
