package com.bluejtitans.smarttradebackend.products.model.requests;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    // common attributes
    public String name;
    public String description;
    public String dataSheet;
    public byte[] photo;
    public String category;

    // technology specific attributes
    public String consume;
    public String model;

    // book specific attributes
    public String ISBN;

    // food specific attributes
    public String calories;

    // clothes specific attributes
    public String size;
}
