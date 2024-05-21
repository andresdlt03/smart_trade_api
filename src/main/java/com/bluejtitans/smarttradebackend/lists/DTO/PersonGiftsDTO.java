package com.bluejtitans.smarttradebackend.lists.DTO;

import java.time.LocalDate;

import com.bluejtitans.smarttradebackend.products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonGiftsDTO {
    private LocalDate reminder;
    private Product product;
    private String seller;
    private double price;
    public PersonGiftsDTO(Product product, String seller, double price, LocalDate reminder){
        this.product = product;
        this.seller = seller;
        this.price = price;
        this.reminder = reminder;
    }
}
