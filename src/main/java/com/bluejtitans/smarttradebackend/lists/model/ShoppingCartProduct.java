package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
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
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    })
    private ProductAvailability productAvailability;

    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartProduct(ShoppingCart shoppingCart, ProductAvailability productAvailability, int quantity) {
        this.shoppingCart = shoppingCart;
        this.productAvailability = productAvailability;
        this.quantity = quantity;
    }
}
