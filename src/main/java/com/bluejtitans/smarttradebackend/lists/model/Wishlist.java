package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
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
    @ManyToMany
    @JoinTable(
            name = "wishlist_product_availability",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_availability_id")
    )
    private List<ProductAvailability> productAvailabilities;
}
