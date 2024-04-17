package ourProducts.model;

import java.util.List;
public class Shirt extends Clothe {
    public Shirt(){
        super();
    }
    public Shirt(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size){
        super(name, description, price, quantity, dataSheet, photos, size);
    }
}