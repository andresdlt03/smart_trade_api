package com.bluejtitans.smarttradebackend.users.service;

import com.bluejtitans.smarttradebackend.exception.UserAlreadyExistsException;
import com.bluejtitans.smarttradebackend.lists.model.GiftList;
import com.bluejtitans.smarttradebackend.lists.model.SavedForLater;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.Wishlist;
import com.bluejtitans.smarttradebackend.lists.repository.ProductListRepository;
import com.bluejtitans.smarttradebackend.users.http.login.*;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterFailed;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterResponse;
import com.bluejtitans.smarttradebackend.users.http.register.RegisterSuccess;
import com.bluejtitans.smarttradebackend.users.model.Admin;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import com.bluejtitans.smarttradebackend.users.model.User;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductListRepository productListRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductListRepository productListRepository) {
        this.userRepository = userRepository;
        this.productListRepository = productListRepository;
    }

    public ResponseEntity<RegisterResponse> saveUser(User user) {
        RegisterFailed registerFailed = new RegisterFailed();
        registerFailed.setEmail(user.getEmail());
        try {
            if(userRepository.existsById(user.getEmail()))
                throw new UserAlreadyExistsException("Ya existe un usuario con el correo " + user.getEmail());
            userRepository.save(user);
            initializeLists((Client) user);
            userRepository.save(user);
        } catch(UserAlreadyExistsException e) {
            registerFailed.setErrorMessage(e.getMessage());
            return ResponseEntity.badRequest().body(registerFailed);
        } catch(Exception e) {
            registerFailed.setErrorMessage("Un error desconocido ha ocurrido al registrar el usuario. Inténtelo de nuevo mas tarde");
            return ResponseEntity.internalServerError().body(registerFailed);
        }
        RegisterSuccess registerSuccess = new RegisterSuccess();
        registerSuccess.setEmail(user.getEmail());
        return ResponseEntity.ok(registerSuccess);
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        LoginFailed loginFailed = new LoginFailed();
        LoginCredentials loginCredentials = loginRequest.getCredentials();
        loginFailed.setEmail(loginCredentials.getEmail());

        User user = userRepository.findById(loginCredentials.getEmail()).orElse(null);
        if(user == null || !user.getPassword().equals(loginCredentials.getPassword())) {
            loginFailed.setErrorMessage("Credenciales incorrectas. Inténtelo de nuevo.");
            return ResponseEntity.badRequest().body(loginFailed);
        }

        LoginSuccess loginSuccess = new LoginSuccess();
        loginSuccess.setEmail(loginCredentials.getEmail());
        if(user instanceof Client) {
            loginSuccess.setUserType("client");
        } else if (user instanceof Seller) {
            loginSuccess.setUserType("seller");
        } else if (user instanceof Admin) {
            loginSuccess.setUserType("admin");
        }
        return ResponseEntity.ok(loginSuccess);
    }

    public void initializeLists(Client client){
        Wishlist wishlist = new Wishlist(client);
        SavedForLater savedForLater = new SavedForLater(client);
        ShoppingCart shoppingCart = new ShoppingCart(client);
        GiftList giftList = new GiftList(client);

        productListRepository.save(wishlist);
        productListRepository.save(savedForLater);
        productListRepository.save(shoppingCart);
        productListRepository.save(giftList);

        client.setWishlist(wishlist);
        client.setSavedForLater(savedForLater);
        client.setShoppingCart(shoppingCart);
        client.setGiftList(giftList);
    }
}
