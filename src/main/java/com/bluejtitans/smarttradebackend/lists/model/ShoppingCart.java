package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
import java.util.List;

@Getter
@Setter
@Entity
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

    /*public void addProduct(ShoppingCartProduct product) {
        products.add(product);
        updatePrice(product);
    }
    public void removeProduct(ShoppingCartProduct product) {
        products.(product);
        updatePrice();
    }

    public void updatePrice() {
        for (Product p : this.getProducts()) {
            totalPrice += p.getPrice();
        }
    }*/
}