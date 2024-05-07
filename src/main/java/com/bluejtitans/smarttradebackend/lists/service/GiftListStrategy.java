package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.*;
import com.bluejtitans.smarttradebackend.lists.repository.GiftListRepository;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiftListStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;
    private final GiftListRepository personGiftRepository;

    @Autowired
    public GiftListStrategy(ProductAvailabilityRepository productAvailabilityRepository, GiftListRepository personGiftRepository){
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.personGiftRepository = personGiftRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if (pa != null) {
            if (list instanceof GiftList) {
                GiftList giftList = (GiftList) list;
                Optional<PersonGift> existingGift = giftList.getPersonGifts().stream()
                        .filter(pg -> pg.getReceiver().equals(request.getReceiver()))
                        .findFirst();
                if (existingGift.isPresent()) {
                    // Agregar el producto a la lista de ProductAvailabilities existente
                    PersonGift personGift = existingGift.get();
                    personGift.getProductAvailabilities().add(pa);
                    personGiftRepository.save(personGift);
                } else {
                    // Crear un nuevo PersonGift y agregarlo a la lista de regalos
                    PersonGift newPersonGift = new PersonGift(request.getReceiver(), giftList, pa, request.getReminder());
                    giftList.getPersonGifts().add(newPersonGift);
                    personGiftRepository.save(newPersonGift);
                }
                return giftList;
            } else {
                throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
            }
        } else {
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }
    @Override
    public ProductList removeProduct(ProductList list, ListRequestDTO request) throws Exception {
        if (list instanceof GiftList) {
            GiftList giftList = (GiftList) list;
            Optional<PersonGift> targetGift = giftList.getPersonGifts().stream()
                    .filter(pg -> pg.getProductAvailabilities().stream()
                            .anyMatch(pa -> pa.getProduct().getName().equals(request.getProductId()))
                            && pg.getReceiver().equals(request.getReceiver())
                            && pg.getDate().equals(request.getReminder()))
                    .findFirst();
            if (targetGift.isPresent()) {
                giftList.getPersonGifts().remove(targetGift.get());
                personGiftRepository.delete(targetGift.get());
                return giftList;
            } else {
                throw new ProductNotInListException("Product not found in Gift List");
            }
        } else {
            throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
        }
    }
}
