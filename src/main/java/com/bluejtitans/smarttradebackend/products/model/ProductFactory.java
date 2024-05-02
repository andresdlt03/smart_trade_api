package com.bluejtitans.smarttradebackend.products.model;

import com.bluejtitans.smarttradebackend.products.dto.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ProductFactory {
    public static IProduct createProductFromDTO(String category, ProductDTO product) throws JsonProcessingException {
        return switch (category) {
            case "technology" ->
                    new Technology(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getConsume(), product.getModel());
            case "book" ->
                    new Book(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getISBN());
            case "food" ->
                    new Food(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getCalories());
            case "clothes" ->
                    new Clothes(product.getName(), product.getDescription(), product.getDataSheet(), product.getPhotos(), product.getSize());
            default -> throw new JsonProcessingException("Invalid product type") {
            };
        };
    }
}
