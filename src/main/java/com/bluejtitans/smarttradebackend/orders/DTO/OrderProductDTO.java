package com.bluejtitans.smarttradebackend.orders.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDTO {
    private boolean reviewed;
    private double valoration;
    private String comment;
    private int stock;
    private Product product;
    private String seller;
    private double price;

    public OrderProductDTO(boolean reviewed, double valoration, String comment, int stock, Product product, String seller, double price) {
        this.reviewed = reviewed;
        this.valoration = valoration;
        this.comment = comment;
        this.stock = stock;
        this.product = product;
        this.seller = seller;
        this.price = price;
    }
}
