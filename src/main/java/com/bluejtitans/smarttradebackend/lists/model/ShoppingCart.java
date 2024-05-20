package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.users.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Entity
@Component
@Table(name = "Shopping_Cart")
public class ShoppingCart extends ProductList {
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<ShoppingCartProduct> shoppingCartProducts;

    @Column(name = "iva")
    private double iva;

    @Column(name = "productsPrice")
    private double productsPrice;

    @Column(name = "total_price")
    private double totalPrice;

    public ShoppingCart(Client client){
        this.setClient(client);
        this.iva = 0.0;
        this.productsPrice = 0.0;
        this.totalPrice = 0.0;
    }

    public ShoppingCart(){

    }
}