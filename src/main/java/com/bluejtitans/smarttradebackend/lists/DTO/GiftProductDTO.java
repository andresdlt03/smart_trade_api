package com.bluejtitans.smarttradebackend.lists.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftProductDTO {
    private LocalDate reminder;
    private String receiver;
    private Product product;
    private String seller;
    private double price;
    public GiftProductDTO(LocalDate reminder, String receiver, Product product, String seller, double price){
        this.reminder = reminder;
        this.receiver = receiver;
        this.product = product;
        this.seller = seller;
        this.price = price;
    }
}
