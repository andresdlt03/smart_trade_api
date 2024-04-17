package com.bluejtitans.smarttradebackend.users.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Seller")
@PrimaryKeyJoinColumn(name = "email")
public class Seller extends User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "cif", nullable = false)
    private String cif;
}
