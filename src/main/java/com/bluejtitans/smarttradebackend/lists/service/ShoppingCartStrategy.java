package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.lists.repository.ShoppingCartProductRepository;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ShoppingCartStrategy implements IListStrategy{
    private final ProductAvailabilityRepository productAvailabilityRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    public ShoppingCartStrategy(ProductAvailabilityRepository productAvailabilityRepository, ShoppingCartProductRepository shoppingCartProductRepository){
        this.productAvailabilityRepository = productAvailabilityRepository;
        this.shoppingCartProductRepository = shoppingCartProductRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, ListRequestDTO request) throws Exception {
        ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerEmail());
        if(pa != null){
            if(list instanceof ShoppingCart){
                ShoppingCart shoppingCart = (ShoppingCart) list;
                ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(shoppingCart, pa, request.getQuantity());
                shoppingCart.getShoppingCartProducts().add(shoppingCartProduct);
                addPrice(shoppingCart, pa.getPrice());
                shoppingCartProductRepository.save(shoppingCartProduct);
                return shoppingCart;
            } else{
                throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
            }
        } else{
            throw new ProductAvailabilityNotFoundException("Product not found");
        }
    }

    @Override
    public ProductList removeProduct(ProductList list, ListRequestDTO request) throws Exception {
        if(list instanceof ShoppingCart){
            ShoppingCart shoppingCart = (ShoppingCart) list;
            Optional<ShoppingCartProduct> targetProduct = shoppingCart.getShoppingCartProducts().stream().filter(cp -> cp.getProductAvailability().getProduct().getName().equals(request.getProductId())).findFirst();
            if(targetProduct.isPresent()){
                shoppingCart.getShoppingCartProducts().remove(targetProduct.get());
                removePrice(shoppingCart, request.getQuantity());
                shoppingCartProductRepository.delete(targetProduct.get());
                return shoppingCart;
            } else{
                throw new ProductNotInListException("Product not found in Shopping Cart");
            }
        } else{
            throw new BadListStrategyCombinationException("Incorrect list-strategy combination");
        }
    }

    public void removePrice(ShoppingCart shoppingCart, double price){
        double newTotalPrice = shoppingCart.getTotalPrice() - price;
        double newProductPrice = newTotalPrice / (1 + 0.21);
        double newIVA = newProductPrice * 0.21;
        shoppingCart.setTotalPrice(newTotalPrice);
        shoppingCart.setProductsPrice(newProductPrice);
        shoppingCart.setIva(newIVA);
    }

    public void addPrice(ShoppingCart shoppingCart, double price){
        double newTotalPrice = shoppingCart.getTotalPrice() + price;
        double newProductPrice = newTotalPrice / (1 + 0.21);
        double newIVA = newProductPrice * 0.21;
        shoppingCart.setTotalPrice(newTotalPrice);
        shoppingCart.setProductsPrice(newProductPrice);
        shoppingCart.setIva(newIVA);
    }
}
