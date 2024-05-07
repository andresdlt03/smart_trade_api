package com.bluejtitans.smarttradebackend.lists.model;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bluejtitans.smarttradebackend.products.model.Product;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "product_availability_id")
    private ProductAvailability productAvailability;

    @ManyToOne
    @JoinColumn(name = "giftList_id")
    private GiftList giftList;

    @Column(name = "date", nullable = true)
    private LocalDate date;

    public PersonGift(String receiver, GiftList giftList, ProductAvailability productAvailability, LocalDate date){
        this.receiver = receiver;
        this.giftList = giftList;
        this.productAvailability = productAvailability;
        this.date = date;
    }
}
