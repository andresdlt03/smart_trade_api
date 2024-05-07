package com.bluejtitans.smarttradebackend.lists.controller;

import com.bluejtitans.smarttradebackend.lists.DTO.*;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/client/{clientId}/lists")
public class ListController {
    private final ListService listService;
    private final GiftListStrategy giftListStrategy;
    private final SavedForLaterStrategy savedForLaterStrategy;
    private  final ShoppingCartStrategy shoppingCartStrategy;
    private  final WishlistStrategy wishlistStrategy;
    @Autowired
    public ListController(ListService listService, GiftListStrategy giftListStrategy, SavedForLaterStrategy savedForLaterStrategy, ShoppingCartStrategy shoppingCartStrategy, WishlistStrategy wishlistStrategy) {
        this.listService = listService;
        this.giftListStrategy = giftListStrategy;
        this.savedForLaterStrategy = savedForLaterStrategy;
        this.shoppingCartStrategy = shoppingCartStrategy;
        this.wishlistStrategy = wishlistStrategy;
    }
    @GetMapping("/{listType}")
    public ResponseEntity<?> getList(@PathVariable String clientId, @PathVariable String listType) {
        try {
            ProductList list = listService.getList(clientId, listType.toLowerCase());
            ListResponseDTO response = new ListResponseDTO();
            listService.setResponseDTO(response, list, listType);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{listType}/products")
    public ResponseEntity<?> addProductToList(
            @PathVariable String clientId,
            @PathVariable String listType,
            @RequestBody ListRequestDTO request) {

        IListStrategy strategy;

        //Determines the Strategy to use depending on the type of list
        switch (listType) {
            case "wishlist":
                strategy = wishlistStrategy;
                break;
            case "savedforlater":
                strategy = savedForLaterStrategy;
                break;
            case "shoppingcart":
                strategy = shoppingCartStrategy;
                break;
            case "giftlist":
                strategy = giftListStrategy;
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        try {
            ProductList result = listService.addProduct(clientId, listType, strategy, request);
            ListResponseDTO response = new ListResponseDTO();
            listService.setResponseDTO(response, result, listType);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Wrong RequestDTO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{listType}/products")
    public ResponseEntity<?> deleteProductFromList(
            @PathVariable String clientId,
            @PathVariable String listType,
            @RequestBody ListRequestDTO request) {

        IListStrategy strategy;

        //Determines the Strategy to use depending on the type of list
        switch (listType) {
            case "wishlist":
                strategy = wishlistStrategy;
                break;
            case "savedforlater":
                strategy = savedForLaterStrategy;
                break;
            case "shoppingcart":
                strategy = shoppingCartStrategy;
                break;
            case "giftlist":
                strategy = giftListStrategy;
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        try {
            ProductList result = listService.removeProduct(clientId, listType, strategy, request);
            ListResponseDTO response = new ListResponseDTO();
            listService.setResponseDTO(response, result, listType);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Wrong RequestDTO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}