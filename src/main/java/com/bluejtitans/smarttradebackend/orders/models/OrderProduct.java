package com.bluejtitans.smarttradebackend.orders.models;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Order_Product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private boolean reviewed;
    private double valoration;
    private String comment;
    private int quantity;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    })
    private ProductAvailability productAvailability;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(ProductAvailability productAvailability, int quantity, Order order){
        this.productAvailability = productAvailability;
        reviewed = false;
        valoration = 0.0;
        comment = "";
        this.quantity = quantity;
        this.order = order;
    }
}
