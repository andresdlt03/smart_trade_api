package ourProducts.model;

import java.util.List;
public abstract class Technology extends Product {
    private int energyDrain;
    private String model;
    public int getEnergyDrain() {
        return energyDrain;
    }
    public void setEnergyDrain(int energyDrain) {
        this.energyDrain = energyDrain;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Technology() {
        super();
    }
    public Technology(String name, String description, double price, int quantity, String dataSheet,
                           List<String> photos, int energyDrain, String model) {
        super(name, description, price, quantity, dataSheet, photos);
        this.energyDrain = energyDrain;
        this.model = model;
    }
    
}
