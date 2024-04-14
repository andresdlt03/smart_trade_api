package ourProducts.Factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import ourProducts.modelDTO.ProductAttributesDTO;
import ourProducts.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
public class ProductFactory {
    private static String name;
    private static String description;
    private static double price;
    private static int quantity;
    private static String dataSheet;
    private static List<String> photos;
    public static Product createProduct(String category, String productJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Product product;
        switch (category.toLowerCase()) {
            case "phone":
                return product = objectMapper.readValue(productJson, Phone.class);
            case "home":
                return product = objectMapper.readValue(productJson, Home.class);
            case "computer":
                return product = objectMapper.readValue(productJson, Computer.class);
            case "book":
                return product = objectMapper.readValue(productJson, Book.class);
            case "food":
                return product = objectMapper.readValue(productJson, Food.class);
            case "pant":
                return product = objectMapper.readValue(productJson, Pant.class);
            case "shirt":
                return product = objectMapper.readValue(productJson, Shirt.class);
            case "sweatshirt":
                return product = objectMapper.readValue(productJson, Sweatshirt.class);
            case "dress":
                return product = objectMapper.readValue(productJson, Dress.class);
            default:
                throw new IllegalArgumentException("Invalid product category: " + category);
        }
    }
}
