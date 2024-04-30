package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ourProducts.model.Product;

@Getter
@Setter
@Entity
@Table(name = "Wishlist")
public class Wishlist extends ProductList {
}
