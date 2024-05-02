package com.bluejtitans.smarttradebackend.products.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class ProductFactory {
    public static IProduct createProductFromJson(String category, String productJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return switch (category.toLowerCase()) {
            case "book" -> objectMapper.readValue(productJson, Book.class);
            case "technology" -> objectMapper.readValue(productJson, Technology.class);
            case "clothes" -> objectMapper.readValue(productJson, Clothes.class);
            case "food" -> objectMapper.readValue(productJson, Food.class);
            default -> throw new IllegalArgumentException("Invalid product category: " + category);
        };
    }
}
