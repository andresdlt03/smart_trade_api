package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class ProductAvailability {
    @EmbeddedId
    private ProductAvailabilityId id = new ProductAvailabilityId();

    private int stock;
    private double price;

    @OneToMany(mappedBy = "productAvailability")
    private List<ShoppingCartProduct> shoppingCartProducts = new ArrayList<>();

    public ProductAvailability(Product product, Seller seller, int stock, double price) {
        this.id = new ProductAvailabilityId(product, seller);
        this.stock = stock;
        this.price = price;
    }

    public ProductAvailability() {

    }
    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public void setSeller(Seller seller) {
        this.id.setSeller(seller);
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public Seller getSeller() {
        return this.id.getSeller();
    }
}

@Embeddable
@Getter
@Setter
class ProductAvailabilityId implements Serializable {
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public ProductAvailabilityId(Product product, Seller seller) {
        this.product = product;
        this.seller = seller;
    }

    public ProductAvailabilityId() {

    }
}