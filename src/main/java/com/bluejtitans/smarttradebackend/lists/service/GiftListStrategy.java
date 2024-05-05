package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.BadRequestException;
import com.bluejtitans.smarttradebackend.exception.ProductNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.GiftRequestDTO;
import com.bluejtitans.smarttradebackend.lists.DTO.RequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.*;
import com.bluejtitans.smarttradebackend.lists.repository.PersonGiftRepository;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GiftListStrategy implements IListStrategy{
    private final ProductRepository productRepository;
    private final PersonGiftRepository personGiftRepository;

    @Autowired
    public GiftListStrategy(ProductRepository productRepository, PersonGiftRepository personGiftRepository){
        this.productRepository = productRepository;
        this.personGiftRepository = personGiftRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, RequestDTO request) throws Exception {
        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(request instanceof GiftRequestDTO){
                GiftRequestDTO giftRequestDTO = (GiftRequestDTO) request;
                if(list instanceof GiftList){
                    GiftList giftList = (GiftList) list;
                    PersonGift personGift = new PersonGift(giftRequestDTO.getReceiver(), giftList, product, giftRequestDTO.getReminderDate());
                    giftList.getPersonGifts().add(personGift);
                    personGiftRepository.save(personGift);
                    return giftList;
                } else{
                    throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
                }
            } else{
                throw new BadRequestException("Wrong RequestDTO for the list");
            }
        } else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public ProductList removeProduct(ProductList list, RequestDTO request) throws Exception {
        if(request instanceof GiftRequestDTO){
            GiftRequestDTO giftRequestDTO = (GiftRequestDTO) request;
            if(list instanceof GiftList){
                GiftList giftList = (GiftList) list;
                Optional<PersonGift> targetGift =
                        giftList.getPersonGifts().stream().filter(pg -> pg.getProduct().getName().equals(giftRequestDTO.getProductId()) &&
                                pg.getReceiver().equals(giftRequestDTO.getReceiver()) &&
                                pg.getDate().equals(giftRequestDTO.getReminderDate())).findFirst();
                if(targetGift.isPresent()){
                    giftList.getPersonGifts().remove(targetGift.get());
                    personGiftRepository.delete(targetGift.get());
                    return giftList;
                } else{
                       throw new ProductNotInListException("Product not found in Shopping Cart");
                }
            } else{
                throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
            }
        } else{
            throw new BadRequestException("Wrong RequestDTO for the list");
        }
    }
}
