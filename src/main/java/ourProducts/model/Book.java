package ourProducts.model;

import java.util.List;
public class Book extends Product {
    private String ISBN;
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public Book() {
        super();
    }
    public Book(String name, String description, double price, int quantity, String dataSheet, List<String> photos, String ISBN) {
        super(name, description, price, quantity, dataSheet, photos);
        this.ISBN = ISBN;
    }
}