package com.bluejtitans.smarttradebackend.products.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    private ProductDTO info;
    private Double price;
    private Integer stock;
    private String sellerEmail;
}