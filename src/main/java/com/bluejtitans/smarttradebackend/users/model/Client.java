package com.bluejtitans.smarttradebackend.users.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Client")
@PrimaryKeyJoinColumn(name = "email")
public class Client extends User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "dni", nullable = false)
    private String dni;
}
