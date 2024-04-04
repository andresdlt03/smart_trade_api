package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.users.model.Admin;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.model.IUser;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import com.bluejtitans.smarttradebackend.users.repository.AdminRepository;
import com.bluejtitans.smarttradebackend.users.repository.ClientRepository;
import com.bluejtitans.smarttradebackend.users.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public UserService(AdminRepository adminRepository,
                       ClientRepository clientRepository,
                       SellerRepository sellerRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    public IUser saveUser(String userType, IUser user) {
        return switch (userType) {
            case "clients" -> {
                Client client = (Client) user;
                yield clientRepository.save(client);
            }
            case "sellers" -> {
                Seller seller = (Seller) user;
                yield sellerRepository.save(seller);
            }
            case "admins" -> {
                Admin admin = (Admin) user;
                yield adminRepository.save(admin);
            }
            default -> null;
        };
    }

}
