package com.bluejtitans.smarttradebackend.users.model;

import jakarta.persistence.Entity;

@Entity
public class Seller extends User {
    private String companyName;
    private String cif;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
