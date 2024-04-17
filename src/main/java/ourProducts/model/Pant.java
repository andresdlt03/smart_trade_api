package ourProducts.model;

import java.util.List;
public class Pant extends Clothe {
    public Pant() {
        super();
    }
    public Pant(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size) {
        super(name, description, price, quantity, dataSheet, photos, size);
    }
}