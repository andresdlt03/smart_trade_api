package com.bluejtitans.smarttradebackend.orders.DTO;

import java.time.LocalDate;
import java.util.Date;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class OrderRequestDTO {
    private LocalDate date;
    private String payment;
    private String shippingAddress;
    private String billingAddress;
    //private String productsNumber;
    //private double totalPrice;
}
