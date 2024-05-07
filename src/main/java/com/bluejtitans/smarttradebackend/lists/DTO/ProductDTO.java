package com.bluejtitans.smarttradebackend.lists.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Product product;
    private String seller;
    private double price;

    public ProductDTO(Product product, String seller, double price){
        this.product = product;
        this.seller = seller;
        this.price = price;
    }
}
