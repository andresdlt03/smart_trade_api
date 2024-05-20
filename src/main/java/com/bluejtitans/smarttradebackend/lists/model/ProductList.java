package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.users.model.Client;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public ProductList(){

    }
}