package com.bluejtitans.smarttradebackend.lists.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
@Getter
@Setter
@Entity
public class PersonGiftProductAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_gift_id")
    private PersonGift personGift;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="product_id", referencedColumnName="product_id"),
            @JoinColumn(name="seller_id", referencedColumnName="seller_id")
    })
    private ProductAvailability productAvailability;

    @Column(name = "date")
    private LocalDate date;

    public PersonGiftProductAvailability() {
    }

    public PersonGiftProductAvailability(PersonGift personGift, ProductAvailability productAvailability, LocalDate date) {
        this.personGift = personGift;
        this.productAvailability = productAvailability;
        this.date = date;
    }
}
