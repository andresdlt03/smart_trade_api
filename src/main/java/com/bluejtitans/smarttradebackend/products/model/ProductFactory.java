package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.exception.InvalidProductFormatException;
import com.bluejtitans.smarttradebackend.products.model.requests.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ProductFactory {
    public static IProduct createProductFromDTO(String category, ProductDTO product) throws JsonProcessingException {
        return switch (category) {
            case "technology" ->
                    new Technology(
                            product.getName(),
                            product.getDescription(),
                            product.getDataSheet(),
                            product.getPhoto(),
                            product.getConsume(),
                            product.getModel()
                    );
            case "book" ->
                    new Book(
                            product.getName(),
                            product.getDescription(),
                            product.getDataSheet(),
                            product.getPhoto(),
                            product.getISBN());
            case "food" ->
                    new Food(
                            product.getName(),
                            product.getDescription(),
                            product.getDataSheet(),
                            product.getPhoto(),
                            product.getCalories()
                    );
            case "clothes" ->
                    new Clothes(
                            product.getName(),
                            product.getDescription(),
                            product.getDataSheet(),
                            product.getPhoto(),
                            product.getSize()
                    );
            default -> throw new InvalidProductFormatException("Formato del producto no válido") {
            };
        };
    }
}
