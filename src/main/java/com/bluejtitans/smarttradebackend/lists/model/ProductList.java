package com.bluejtitans.smarttradebackend.lists.model;

import ourProducts.model.Product;
import com.bluejtitans.smarttradebackend.users.model.Client;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class ProductList implements IProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToMany
    @JoinTable(
            name = "lista_producto",
            joinColumns = @JoinColumn(name = "lista_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id"))
    private java.util.List<Product> products;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product removeProduct(Product product) {
        int removeIndex = products.indexOf(product);
        return products.remove(removeIndex);
    }
}