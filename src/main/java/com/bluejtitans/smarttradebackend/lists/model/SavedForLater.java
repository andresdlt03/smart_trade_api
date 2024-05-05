package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Saved_For_Later")
public class SavedForLater extends ProductList{
    @OneToMany(mappedBy = "savedForLater", cascade = CascadeType.ALL)
    private List<Product> products;
}
