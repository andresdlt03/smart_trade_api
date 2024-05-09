package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.lists.repository.ShoppingCartRepository;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;
    private final ShoppingCartRepository shoppingCartProductRepository;
    private ShoppingCart shoppingCart;

    @Autowired
    public ShoppingCartStrategy(ProductAvailabilityRepository productAvailabilityRepository, ShoppingCartRepository shoppingCartProductRepository, ProductList list){
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.shoppingCartProductRepository = shoppingCartProductRepository;
        this.shoppingCart = (ShoppingCart) list;
    }
    @Override
    public ProductList addProduct(ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(pa != null){
            ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(shoppingCart, pa, request.getQuantity());
            pa.getShoppingCartProducts().add(shoppingCartProduct);
            shoppingCart.getShoppingCartProducts().add(shoppingCartProduct);
            addPrice(pa.getPrice());
            shoppingCartProductRepository.save(shoppingCartProduct);
            productAvailabilityRepository.save(pa);
            return shoppingCart;
        } else{
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }

    @Override
    public ProductList removeProduct(ListRequestDTO request) throws Exception {
        Optional<ShoppingCartProduct> targetProduct = shoppingCart.getShoppingCartProducts().stream().filter(cp -> cp.getProductAvailability().getProduct().getName().equals(request.getProductId())).findFirst();
        if(targetProduct.isPresent()){
            targetProduct.get().getProductAvailability().getShoppingCartProducts().remove(targetProduct.get());
            shoppingCart.getShoppingCartProducts().remove(targetProduct.get());
            removePrice(request.getQuantity());
            shoppingCartProductRepository.delete(targetProduct.get());
            productAvailabilityRepository.save(targetProduct.get().getProductAvailability());
            return shoppingCart;
        } else{
            throw new ProductNotInListException("Product not found in Shopping Cart");
        }
    }

    public void removePrice(double price){
        double newTotalPrice = shoppingCart.getTotalPrice() - price;
        calculateNewPrice(newTotalPrice);
    }

    public void addPrice(double price){
        double newTotalPrice = shoppingCart.getTotalPrice() + price;
        calculateNewPrice(newTotalPrice);
    }

    public void calculateNewPrice(double newTotalPrice){
        double newProductPrice = newTotalPrice / (1 + 0.21);
        double newIVA = newProductPrice * 0.21;
        shoppingCart.setTotalPrice(newTotalPrice);
        shoppingCart.setProductsPrice(newProductPrice);
        shoppingCart.setIva(newIVA);
    }
}
