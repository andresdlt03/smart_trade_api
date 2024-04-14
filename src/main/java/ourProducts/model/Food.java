package ourProducts.model;

import java.util.List;
public class Food extends Product {
    private int calories;
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public Food(){
        super();
    }
    public Food(String name, String description, double price, int quantity, String dataSheet, List<String> photos, int calories) {
        super(name, description, price, quantity, dataSheet, photos);
        this.calories = calories;
    }
}