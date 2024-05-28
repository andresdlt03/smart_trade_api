package com.bluejtitans.smarttradebackend.orders.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderResponseDTO {
    private Long Id;
    private LocalDate date;
    private String payment;
    private String shippingAddress;
    private String billingAddress;
    private int productsNumber;
    private double totalPrice;
    private List<OrderProductDTO> orderProducts = new ArrayList<>();
    private String state;

    public OrderResponseDTO(){

    }
    public OrderResponseDTO(LocalDate date, String payment, String shippingAddress, String billingAddress, int productsNumber, Double totalPrice, List<OrderProductDTO> orderProductDTOs){
        this.date = date;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.productsNumber = productsNumber;
        this.totalPrice = totalPrice;
        this.orderProducts = orderProductDTOs;
    }
}
