package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Person_Gift")
public class PersonGift extends ProductList{
    @Column(name = "receiver")
    private String receiver;
}
