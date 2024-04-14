package ourProducts.model;

import java.util.List;
public class Sweatshirt extends Clothe {
    public Sweatshirt(){
        super();
    }
    public Sweatshirt(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String size){
        super(name, description, price, quantity, dataSheet, photos, size);
    }
}