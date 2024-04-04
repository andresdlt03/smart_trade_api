package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.users.model.*;
import com.bluejtitans.smarttradebackend.users.repository.AdminRepository;
import com.bluejtitans.smarttradebackend.users.repository.ClientRepository;
import com.bluejtitans.smarttradebackend.users.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public UserService(AdminRepository adminRepository, ClientRepository clientRepository, SellerRepository sellerRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    public IUser registerUser(IUser user) {
        CrudRepository<? extends User, String> repository = getRepository(user);

        repository.findById(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User already exists");
        });

        return user;
    }

    private CrudRepository<? extends User, String> getRepository(IUser user) {
        if (user instanceof Admin) {
            return adminRepository;
        } else if (user instanceof Client) {
            return clientRepository;
        } else if (user instanceof Seller) {
            return sellerRepository;
        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }

}
