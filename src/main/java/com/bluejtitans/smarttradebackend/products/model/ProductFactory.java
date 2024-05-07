package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.products.dto.requests.InfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ProductFactory {
    public static IProduct createProductFromDTO(String category, InfoDTO product) throws JsonProcessingException {
        return switch (category) {
            case "technology" ->
                    new Technology(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getConsume(), product.getModel());
            case "book" ->
                    new Book(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getISBN());
            case "food" ->
                    new Food(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getCalories());
            case "clothes" ->
                    new Clothes(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getSize());
            default -> throw new InvalidProductFormatException("Invalid product format") {
            };
        };
    }
}
