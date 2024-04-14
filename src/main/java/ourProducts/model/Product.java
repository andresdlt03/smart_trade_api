package ourProducts.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public class Product {
    @Id
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String dataSheet;
    private List<String> photos;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDataSheet() {
        return dataSheet;
    }
    public void setDataSheet(String dataSheet) {
        this.dataSheet = dataSheet;
    }
    public List<String> getPhotos() {
        return photos;
    }
    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
    public Product(){
    }
    public Product(String name, String description, double price, int quantity, String dataSheet, List<String> photos) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.dataSheet = dataSheet;
        this.photos = photos;
    }
}
