package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Saved_For_Later")
public class SavedForLater extends ProductList{
    @ManyToMany
    @JoinTable(
            name = "saved_for_later_product_availability",
            joinColumns = @JoinColumn(name = "saved_for_later_id"),
            inverseJoinColumns = @JoinColumn(name = "product_availability_id")
    )
    private List<ProductAvailability> productAvailabilities = new ArrayList<>();
}
