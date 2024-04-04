package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.users.model.Admin;
import com.bluejtitans.smarttradebackend.users.model.Client;
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

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

}
