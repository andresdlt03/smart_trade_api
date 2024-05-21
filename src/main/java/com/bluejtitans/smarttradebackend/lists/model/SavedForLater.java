package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.users.model.Client;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Component
@Table(name = "Saved_For_Later")
public class SavedForLater extends ProductList{
    @ManyToMany
    @JoinTable(
            name = "saved_for_later_product_availability",
            joinColumns = {
                    @JoinColumn(name = "saved_for_later_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
            }
    )
    private List<ProductAvailability> productAvailabilities;

    public SavedForLater(Client client){
        this.setClient(client);
    }

    public SavedForLater(){

    }
}
