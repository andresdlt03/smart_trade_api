package com.bluejtitans.smarttradebackend.users.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
