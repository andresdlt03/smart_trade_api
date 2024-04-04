package com.bluejtitans.smarttradebackend.users.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFactory {
    public static IUser createUserFromJson(String userType, String userJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return switch (userType) {
                case "admin" -> objectMapper.readValue(userJson, Admin.class);
                case "client" -> objectMapper.readValue(userJson, Client.class);
                case "seller" -> objectMapper.readValue(userJson, Seller.class);
                default -> throw new IllegalArgumentException("Invalid user type");
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid user JSON");
        }
    }
}
