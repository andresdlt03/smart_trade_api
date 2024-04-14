package ourProducts.model;

import java.util.List;
public class Phone extends Technology {
    public Phone() {
        super();
    }
    public Phone(String name, String description, double price, int quantity, String dataSheet,
                 List<String> photos, int energyDrain, String model) {
        super(name, description, price, quantity, dataSheet, photos, energyDrain, model);
    }
}