package com.bluejtitans.smarttradebackend.lists.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingProductDTO {
    private int stock;
    private Product product;
    private String seller;
    private double price;

    public ShoppingProductDTO(int stock, Product product, String seller, double price){
        this.stock = stock;
        this.product = product;
        this.seller = seller;
        this.price = price;
    }
}
