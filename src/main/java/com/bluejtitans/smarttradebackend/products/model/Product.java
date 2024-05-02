package com.bluejtitans.smarttradebackend.products.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product implements IProduct{
    @Id
    private String name;
    private String description;
    private String dataSheet;
    @ElementCollection
    private List<String> photos;
}
