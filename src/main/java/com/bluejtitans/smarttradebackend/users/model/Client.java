package com.bluejtitans.smarttradebackend.users.model;

import com.bluejtitans.smarttradebackend.lists.model.*;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "email")
public class Client extends User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @Column(name = "credit_card", nullable = true)
    private String creditCard;

    @OneToOne
    private Wishlist wishlist;

    @OneToOne
    private SavedForLater savedForLater;

    @OneToOne
    private ShoppingCart shoppingCart;

    @OneToOne
    private GiftList giftList;

    @OneToMany(mappedBy = "client")
    private List<Order> orderList;
}
