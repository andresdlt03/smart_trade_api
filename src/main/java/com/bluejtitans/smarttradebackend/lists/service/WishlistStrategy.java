package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAlreadyAddedException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.Wishlist;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;
    private Wishlist wishlist;

    @Autowired
    public WishlistStrategy(ProductAvailabilityRepository productAvailabilityRepository, Wishlist list){
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.wishlist = (Wishlist) list;
    }
    @Override
    public ProductList addProduct(ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(pa != null){
            if(!productInList(pa)){
                wishlist.getProductAvailabilities().add(pa);
                return wishlist;
            } else{
                throw new ProductAlreadyAddedException("Product already in Wishlist");
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
                wishlist.getProductAvailabilities().remove(pa);
                return wishlist;
            } else{
                throw new ProductNotInListException("Product not in Wishlist");
            }
        } else{
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }

    public boolean productInList(ProductAvailability pa){
        return wishlist.getProductAvailabilities().contains(pa);
    }
}
