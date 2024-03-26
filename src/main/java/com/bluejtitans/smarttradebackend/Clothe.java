package com.bluejtitans.smarttradebackend;

public abstract class Clothe extends Product{
    private Size size;

    public com.bluejtitans.smarttradebackend.Size getSize() {
        return size;
    }

    public void setSize(com.bluejtitans.smarttradebackend.Size size) {
        this.size = size;
    }
}
public enum Size {
    XS, // Extra pequeño
    S,  // Pequeño
    M,  // Mediano
    L,  // Grande
    XL  // Extra grande
}
