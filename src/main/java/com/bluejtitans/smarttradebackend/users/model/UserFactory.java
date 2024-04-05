package com.bluejtitans.smarttradebackend.users.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserFactory {
    public static IUser createUserFromJson(String userType, String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return switch (userType) {
                case "client" -> objectMapper.readValue(json, Client.class);
                case "seller" -> objectMapper.readValue(json, Seller.class);
                case "admin" -> objectMapper.readValue(json, Admin.class);
                default -> null;
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
