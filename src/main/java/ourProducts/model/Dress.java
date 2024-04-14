package ourProducts.model;

import java.util.List;
public class Dress extends Clothe {
    public Dress() {
        super();
    }
    public Dress(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size) {
        super(name, description, price, quantity, dataSheet, photos, size);
    }
}