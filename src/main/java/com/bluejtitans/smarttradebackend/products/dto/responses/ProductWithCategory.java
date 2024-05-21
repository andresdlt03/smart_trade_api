package com.bluejtitans.smarttradebackend.products.dto.responses;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWithCategory {

    private String category;
    private Product product;

    public ProductWithCategory(Product product, String category) {
        this.product = product;
        this.category = category;
    }
}
