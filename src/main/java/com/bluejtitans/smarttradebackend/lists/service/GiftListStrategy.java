package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAlreadyAddedException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.*;
import com.bluejtitans.smarttradebackend.lists.repository.GiftPersonRepository;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiftListStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;
    private final GiftPersonRepository personGiftRepository;
    private GiftList giftList;

    @Autowired
    public GiftListStrategy(ProductAvailabilityRepository productAvailabilityRepository, GiftPersonRepository personGiftRepository, GiftList list){
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.personGiftRepository = personGiftRepository;
        this.giftList = (GiftList) list;
    }
    @Override
    public ProductList addProduct(ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if (pa != null) {
                Optional<PersonGift> existingReceiver = giftList.getPersonGifts().stream()
                        .filter(pg -> pg.getReceiver().equals(request.getReceiver()))
                        .findFirst();
                if (existingReceiver.isPresent()) {
                    // Agregar el producto a la lista de ProductAvailabilities del receptor de PersonGift
                    PersonGift personGift = existingReceiver.get();
                    Optional<PersonGiftProductAvailability> existingPersonGiftProductAvailability = personGift.getPersonGiftProductAvailabilities().stream()
                            .filter(pgpa -> pgpa.getProductAvailability().getProduct().getName().equals(request.getProductId())
                                            && pgpa.getDate().equals(request.getReminder())).findFirst();
                    if(existingPersonGiftProductAvailability.isPresent()){
                        throw new ProductAlreadyAddedException("Ya se ha añadido el regalo: " + request.getProductId() + " - " + request.getReminder());
                    } else{
                        personGift.getPersonGiftProductAvailabilities().add(new PersonGiftProductAvailability(personGift, pa, request.getReminder()));
                        personGiftRepository.save(personGift);
                    }
                } else {
                    // Crear un nuevo PersonGift y agregarlo a la lista de regalos
                    PersonGift newPersonGift = new PersonGift(request.getReceiver(), giftList);
                    newPersonGift.getPersonGiftProductAvailabilities().add(new PersonGiftProductAvailability(newPersonGift, pa, request.getReminder()));
                    giftList.getPersonGifts().add(newPersonGift);
                    personGiftRepository.save(newPersonGift);
                }
                return giftList;
        } else {
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }
    @Override
    public ProductList removeProduct(ListRequestDTO request) throws Exception {
        /*Optional<PersonGift> targetGift = giftList.getPersonGifts().stream()
                .filter(pg -> pg.getProductAvailabilities().stream()
                        .anyMatch(pa -> pa.getProduct().getName().equals(request.getProductId()))
                        && pg.getReceiver().equals(request.getReceiver())
                        && pg.getDate().equals(request.getReminder()))
                .findFirst();*/

        /*Optional<PersonGift> targetGift = giftList.getPersonGifts().stream()
                .filter(pg -> pg.getPersonGiftProductAvailabilities().stream()
                        .anyMatch(pga -> pga.getProductAvailability().getProduct().getName().equals(request.getProductId())
                                && pga.getDate().equals(request.getReminder()))
                        && pg.getReceiver().equals(request.getReceiver()))
                .findFirst();*/
        /*if (targetGift.isPresent()) {
            giftList.getPersonGifts().remove(targetGift.get());
            personGiftRepository.delete(targetGift.get());
            return giftList;
        } else {
            throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
        }*/

        // Buscar el PersonGift correspondiente
        Optional<PersonGift> targetGift = giftList.getPersonGifts().stream()
                .filter(pg -> pg.getReceiver().equals(request.getReceiver()))
                .findFirst();

        if (targetGift.isPresent()) {
            PersonGift personGift = targetGift.get();
            // Encontrar la asociación específica a eliminar
            Optional<PersonGiftProductAvailability> targetPersonGiftProductAvailability = personGift.getPersonGiftProductAvailabilities().stream()
                    .filter(pga -> pga.getProductAvailability().getProduct().getName().equals(request.getProductId())
                            && pga.getDate().equals(request.getReminder()))
                    .findFirst();

            if (targetPersonGiftProductAvailability.isPresent()) {
                // Eliminar la asociación específica
                personGift.getPersonGiftProductAvailabilities().remove(targetPersonGiftProductAvailability.get());
                personGiftRepository.save(personGift); // Guardar los cambios en PersonGift
                return giftList;
            } else {
                throw new ProductAvailabilityNotFoundException("Product and date combination not found");
            }
        } else {
            throw new BadListStrategyCombinationException("Receiver not found in the gift list");
        }
    }
}
