package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAlreadyAddedException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.SavedForLater;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavedForLaterStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;

    @Autowired
    public SavedForLaterStrategy(ProductAvailabilityRepository productAvailabilityRepository) {
        this.productAvailabilityRepository = productAvailabilityRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if (list instanceof SavedForLater) {
            SavedForLater savedForLater = (SavedForLater) list;
            if (!productInList(savedForLater, pa)) {
                savedForLater.getProductAvailabilities().add(pa);
                return savedForLater;
            } else {
                throw new ProductAlreadyAddedException("Product already in Saved For Later");
            }
        } else {
            throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
        }
    }

    @Override
    public ProductList removeProduct(ProductList list, ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(list instanceof SavedForLater){
            SavedForLater savedForLater = (SavedForLater) list;
            if(productInList(savedForLater, pa)){
                savedForLater.getProductAvailabilities().remove(pa);
                return savedForLater;
            } else{
                throw new ProductNotInListException("Product not in Saved For Later");
            }
        } else{
            throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
        }
    }

    public boolean productInList(SavedForLater list, ProductAvailability pa ){
        return list.getProductAvailabilities().contains(pa);
    }
}
