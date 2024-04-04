package com.bluejtitans.smarttradebackend.users.controller;

import com.bluejtitans.smarttradebackend.users.model.Admin;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.repository.AdminRepository;
import com.bluejtitans.smarttradebackend.users.repository.ClientRepository;
import com.bluejtitans.smarttradebackend.users.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsersController {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public UsersController(AdminRepository adminRepository,
                           ClientRepository clientRepository,
                           SellerRepository sellerRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> registerUser(@RequestParam("type") String userType, @RequestBody User user) {
        switch (userType) {
            case "admin" -> adminRepository.save((Admin) user);
            case "client" -> clientRepository.save((Client) user);
            case "seller" -> sellerRepository.save((Seller) user);
        }

        return ResponseEntity.ok(user);
    }

}
