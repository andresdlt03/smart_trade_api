package ourProducts.model;

import java.util.List;
public abstract class Clothe extends Product {
    private String size;
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    // Constructor sin argumentos (constructor por defecto)
    public Clothe() {
        super();
    }
    // Constructor con argumentos
    public Clothe(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size) {
        super(name, description, price, quantity, dataSheet, photos);
        this.size = size;
    }
}
