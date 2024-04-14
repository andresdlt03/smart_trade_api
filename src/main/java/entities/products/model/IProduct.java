package entities.products.model;

import java.util.List;
public interface IProduct {
    public String getName();
    public void setName(String name);
    public double getPrice();
    public void setPrice(double price);
    public int getQuantity();
    public void setQuantity(int quantity);
    public String getDescription();
    public void setDescription(String description);
    public String getTechnicalSheet();
    public void setTechnicalSheet(String technicalSheet);
    public List<String> getImages();
    public void setImages(List<String> images);
}
