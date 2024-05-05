package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Wishlist")
public class Wishlist extends ProductList {
    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    private List<Product> products;
}
