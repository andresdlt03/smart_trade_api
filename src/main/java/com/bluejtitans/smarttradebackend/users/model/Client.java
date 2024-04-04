package com.bluejtitans.smarttradebackend.users.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends User {
    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
