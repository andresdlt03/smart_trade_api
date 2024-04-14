package ourProducts.model;

import java.util.List;
public class Home extends Technology {
    public Home() {
        super();
    }
    public Home(String name, String description, double price, int quantity, String dataSheet,
                List<String> photos, int energyDrain, String model) {
        super(name, description, price, quantity, dataSheet, photos, energyDrain, model);
    }
}