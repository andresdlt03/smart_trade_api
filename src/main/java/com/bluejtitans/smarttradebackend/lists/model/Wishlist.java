package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.users.model.Client;
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
            joinColumns = {
                    @JoinColumn(name = "wishlist_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
            }
    )
    private List<ProductAvailability> productAvailabilities;

    public Wishlist(Client client){
        this.setClient(client);
    }
}
