package com.bluejtitans.smarttradebackend.users.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "email")
public class Admin extends User {
    @Id
    @Column(name = "email")
    private String email;
}
