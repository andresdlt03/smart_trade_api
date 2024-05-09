package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

    @Getter
    @Setter
    @Entity
    @Table(name = "Person_Gift")
    public class PersonGift {

        @Column(name = "receiver")
        private String receiver;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToMany
        @JoinTable(
                name = "person_gift_product_availability",
                joinColumns = @JoinColumn(name = "person_gift_id"),
                inverseJoinColumns = {
                        @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                        @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
                }
        )
        private List<ProductAvailability> productAvailabilities = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "giftList_id")
        private GiftList giftList;

        @Column(name = "date", nullable = true)
        private LocalDate date;

        public PersonGift(String receiver, GiftList giftList, ProductAvailability productAvailability, LocalDate date){
            this.receiver = receiver;
            this.giftList = giftList;
            productAvailabilities.add(productAvailability);
            this.date = date;
        }
    }
