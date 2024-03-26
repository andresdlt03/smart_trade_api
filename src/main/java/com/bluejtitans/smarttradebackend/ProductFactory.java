package com.bluejtitans.smarttradebackend;

public class ProductFactory {
    public Product createProduct(String category, Object... attributes) {
        switch (category.toLowerCase()) {
            case "phone":
                return new phone(attributes);
            case "home":
                return new home(attributes);
            case "computer":
                return new computer(attributes);
            case "book":
                return new BookProduct(attributes);
            case "food":
                return new food(attributes);
            case "pant":
                return new pant(attributes);
            case "shirt":
                return new shirt(attributes);
            case "sweatshirt":
                return new sweatshirt(attributes);
            case "dress":
                return new dress(attributes);
            default:
                throw new IllegalArgumentException("Invalid product category: " + category);
        }
    }
}
