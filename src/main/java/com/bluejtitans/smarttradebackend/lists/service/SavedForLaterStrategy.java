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
    private SavedForLater savedForLater;

    @Autowired
    public SavedForLaterStrategy(ProductAvailabilityRepository productAvailabilityRepository,  SavedForLater list) {
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.savedForLater = (SavedForLater) list;
    }
    @Override
    public ProductList addProduct(ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(pa != null){
            if (!productInList(pa)) {
                savedForLater.getProductAvailabilities().add(pa);
                return savedForLater;
            } else {
                throw new ProductAlreadyAddedException("Product already in Saved For Later");
            }
        } else{
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }

    @Override
    public ProductList removeProduct(ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(pa != null){
            if(productInList(pa)){
                savedForLater.getProductAvailabilities().remove(pa);
                return savedForLater;
            } else{
                throw new ProductNotInListException("Product not in Saved For Later");
            }
        } else{
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }

    public boolean productInList(ProductAvailability pa){
        return savedForLater.getProductAvailabilities().contains(pa);
    }
}
