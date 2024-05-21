package com.bluejtitans.smarttradebackend.lists.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ListRequestDTO {
    //Common
    private String sellerEmail;
    private String productId;

    //ShoppingCart
    private int quantity;

    //Gift
    private String receiver;
    private LocalDate reminder;
}