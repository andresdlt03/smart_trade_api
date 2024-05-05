package com.bluejtitans.smarttradebackend.lists.controller;

import com.bluejtitans.smarttradebackend.lists.DTO.*;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.service.*;
import com.bluejtitans.smarttradebackend.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/client/{clientId}/lists")
public class ListController {
    private ListService listService;
    private GiftListStrategy giftListStrategy;
    private SavedForLaterStrategy savedForLaterStrategy;
    private  ShoppingCartStrategy shoppingCartStrategy;
    private  WishlistStrategy wishlistStrategy;
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
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/products/{listType}")
    public ResponseEntity<?> addProductToList(
            @PathVariable String clientId,
            @PathVariable String listType,
            @RequestBody RequestDTO request) {

        IListStrategy strategy;
        Class<?> dtoClass;

        //Determines the type of DTO and Strategy to use depending on the type of list
        switch (listType) {
            case "wishlist":
                strategy = wishlistStrategy;
                dtoClass = RequestDTO.class;
                break;
            case "savedforlater":
                strategy = savedForLaterStrategy;
                dtoClass = RequestDTO.class;
                break;
            case "shoppingcart":
                strategy = shoppingCartStrategy;
                dtoClass = ShoppingCartRequestDTO.class;
                break;
            case "giftlist":
                strategy = giftListStrategy;
                dtoClass = GiftRequestDTO.class;
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            RequestDTO requestBody = (RequestDTO) mapper.convertValue(request, dtoClass);
            ProductList result = listService.addProduct(clientId, listType, strategy, requestBody);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Wrong RequestDTO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/products/{listType}")
    public ResponseEntity<?> deleteProductFromList(
            @PathVariable String clientId,
            @PathVariable String listType,
            @RequestBody RequestDTO request) {

        IListStrategy strategy;
        Class<?> dtoClass;

        //Determines the type of DTO and Strategy to use depending on the type of list
        switch (listType) {
            case "wishlist":
                strategy = wishlistStrategy;
                dtoClass = RequestDTO.class;
                break;
            case "savedforlater":
                strategy = savedForLaterStrategy;
                dtoClass = RequestDTO.class;
                break;
            case "shoppingcart":
                strategy = shoppingCartStrategy;
                dtoClass = ShoppingCartRequestDTO.class;
                break;
            case "giftlist":
                strategy = giftListStrategy;
                dtoClass = GiftRequestDTO.class;
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            RequestDTO requestBody = (RequestDTO) mapper.convertValue(request, dtoClass);
            ProductList result = listService.removeProduct(clientId, listType, strategy, requestBody);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Wrong RequestDTO");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}