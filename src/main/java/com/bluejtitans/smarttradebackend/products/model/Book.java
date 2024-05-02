package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book extends Product {

    @Column
    private String ISBN;

}
