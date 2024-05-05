package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAlreadyAddedException;
import com.bluejtitans.smarttradebackend.exception.ProductNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.RequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.Wishlist;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class WishlistStrategy implements IListStrategy{
    ProductRepository productRepository;

    @Autowired
    public WishlistStrategy(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, RequestDTO request) throws Exception {
        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(list instanceof Wishlist){
                Wishlist wishlist = (Wishlist) list;
                if(!productInList(wishlist, product)){
                    wishlist.getProducts().add(product);
                    return wishlist;
                } else{
                    throw new ProductAlreadyAddedException("Product already in Wishlist");
                }
            } else{
                throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
            }
        } else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public ProductList removeProduct(ProductList list, RequestDTO request) throws Exception {
        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(list instanceof Wishlist){
                Wishlist wishlist = (Wishlist) list;
                if(productInList(wishlist, product)){
                    wishlist.getProducts().remove(product);
                    return wishlist;
                } else{
                    throw new ProductNotInListException("Product not in Wishlist");
                }
            } else{
                throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
            }
        } else{
            throw new ProductNotFoundException("Product not found");
        }
    }

    public boolean productInList(Wishlist list, Product product){
        return list.getProducts().contains(product);
    }
}
