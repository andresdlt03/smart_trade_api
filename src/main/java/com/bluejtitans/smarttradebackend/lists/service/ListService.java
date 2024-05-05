package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.ListDoesntExistException;
import com.bluejtitans.smarttradebackend.lists.DTO.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.bluejtitans.smarttradebackend.lists.repository.*;
import com.bluejtitans.smarttradebackend.lists.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class ListService {
    private ListRepository listRepository;
    private IListStrategy listStrategy;

    @Autowired
    public ListService(ListRepository listRepository, IListStrategy listStrategy){
        this.listRepository = listRepository;
        this.listStrategy = listStrategy;
    }
    public ProductList getList(String clientId, String listType) throws Exception{
        if(listType.equals("wishlist")){
            return listRepository.findWishlistByClientId(clientId).get();
        } else if(listType.equals("savedforlater")){
            return listRepository.findSavedForLaterByClientId(clientId).get();
        } else if(listType.equals("shoppingcart")) {
            return listRepository.findShoppingCartByClientId(clientId).get();
        } else if (listType.equals("giftList")) {
            return listRepository.findGiftListByClientId(clientId).get();
        } else{
            throw new ListDoesntExistException("No existe la lista");
        }
    }

    public ProductList addProduct(String clientId, String listType, IListStrategy listStrategy, RequestDTO request) throws Exception {
        ProductList list = getList(clientId, listType);
        ProductList modifiedList = listStrategy.addProduct(list, request);
        listRepository.save(modifiedList);
        return modifiedList;
    }

    public ProductList removeProduct(String clientId, String listType, IListStrategy listStrategy, RequestDTO request) throws Exception {
        ProductList list = getList(clientId, listType);
        ProductList modifiedList = listStrategy.removeProduct(list, request);
        listRepository.save(modifiedList);
        return modifiedList;
    }
}
