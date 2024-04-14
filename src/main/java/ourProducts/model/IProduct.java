package ourProducts.model;

import java.util.List;

public interface IProduct {
    public String getName();
    public void setName(String name);
    public String getDescription();
    public void setDescription(String description);
    public double getPrice();
    public void setPrice(double price);
    public int getQuantity();
    public void setQuantity(int quantity);
    public String getDataSheet();
    public void setDataSheet(String dataSheet);
    public List<String> getPhotos();
    public void setPhotos(List<String> photos);
}
