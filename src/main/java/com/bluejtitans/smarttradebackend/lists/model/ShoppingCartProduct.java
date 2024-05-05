package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
@Entity
@Getter
@Setter
@Table(name = "shoppingCart_product")
public class ShoppingCartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartProduct(ShoppingCart shoppingCart, Product product, int quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }
}
