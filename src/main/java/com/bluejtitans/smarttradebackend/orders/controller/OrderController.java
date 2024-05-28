package com.bluejtitans.smarttradebackend.orders.controller;

import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.DTO.ListResponseDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.repository.GiftPersonRepository;
import com.bluejtitans.smarttradebackend.lists.repository.ShoppingCartRepository;
import com.bluejtitans.smarttradebackend.lists.service.*;
import com.bluejtitans.smarttradebackend.orders.DTO.OrderRequestDTO;
import com.bluejtitans.smarttradebackend.orders.DTO.OrderResponseDTO;
import com.bluejtitans.smarttradebackend.orders.DTO.ProductReviewDTO;
import com.bluejtitans.smarttradebackend.orders.models.*;
import com.bluejtitans.smarttradebackend.orders.repository.OrderRepository;
import com.bluejtitans.smarttradebackend.orders.service.OrderService;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients/{clientId}/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllOrders(@PathVariable String clientId) {
        return ResponseEntity.ok(orderService.getAllOrders(clientId));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> getOrder(@PathVariable String clientId, @PathVariable Long Id) {
        try {
            return ResponseEntity.ok(orderService.getOrder(Id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@PathVariable String clientId, @RequestBody OrderRequestDTO request) {
        try {
            OrderResponseDTO response = orderService.createOrder(clientId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{Id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable String clientId, @PathVariable Long Id) {
        try {
            OrderResponseDTO response = orderService.cancelOrder(Id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{Id}/review")
    public ResponseEntity<?> postReview(@PathVariable String clientId, @PathVariable Long Id, @RequestBody ProductReviewDTO request) {
        try {
            OrderResponseDTO response = orderService.postReview(Id, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{Id}/shippingAddress")
    public ResponseEntity<?> changeShippingAddress(@PathVariable String clientId, @PathVariable Long Id, @RequestBody String newShippingAddress) {
        try {
            OrderResponseDTO response = orderService.changeShippingAddress(Id,newShippingAddress);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{Id}/state/{state}")
    public ResponseEntity<?> changeState(@PathVariable String clientId, @PathVariable Long Id, @PathVariable String state) {
        try {
            OrderResponseDTO response = orderService.changeState(Id,state);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}