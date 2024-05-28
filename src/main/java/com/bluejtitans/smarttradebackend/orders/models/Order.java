package com.bluejtitans.smarttradebackend.orders.models;

import com.bluejtitans.smarttradebackend.orders.service.*;
import com.bluejtitans.smarttradebackend.users.model.Client;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDate date;
    private String payment;
    private String shippingAddress;
    private String billingAddress;
    private int productsNumber;
    private double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Transient
    private OrderState orderState;

    private EnumStates state;

    @ManyToOne
    private Client client;

    public Order() {
    }

    public Order(LocalDate date, String payment, String shippingAddress, String billingAddress, int productsNumber, double totalPrice, List<OrderProduct> orderProducts, EnumStates state, Client client) {
        this.date = date;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.productsNumber = productsNumber;
        this.totalPrice = totalPrice;
        this.orderProducts = orderProducts;
        this.state = state;
        this.client = client;
    }

    public OrderState getOrderState() {
        if (orderState == null) {
            orderState = mapStateEnumToState(state);
        }
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        this.state = mapStateToStateEnum(orderState);
    }

    private OrderState mapStateEnumToState(EnumStates enumState) {
        switch (enumState) {
            case MADE: return new MadeState();
            case SENT: return new SentState();
            case RECEIVED: return new ReceivedState();
            case CANCELLED: return new CancelledState();
            default: throw new IllegalArgumentException("Unknown state: " + enumState);
        }
    }

    private EnumStates mapStateToStateEnum(OrderState state) {
        if (state instanceof MadeState) return EnumStates.MADE;
        if (state instanceof SentState) return EnumStates.SENT;
        if (state instanceof ReceivedState) return EnumStates.RECEIVED;
        if (state instanceof CancelledState) return EnumStates.CANCELLED;
        throw new IllegalArgumentException("Unknown state class: " + state.getClass().getName());
    }
}
