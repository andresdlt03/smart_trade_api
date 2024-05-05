package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.BadListStrategyCombinationException;
import com.bluejtitans.smarttradebackend.exception.BadRequestException;
import com.bluejtitans.smarttradebackend.exception.ProductNotFoundException;
import com.bluejtitans.smarttradebackend.exception.ProductNotInListException;
import com.bluejtitans.smarttradebackend.lists.DTO.RequestDTO;
import com.bluejtitans.smarttradebackend.lists.DTO.ShoppingCartRequestDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.SavedForLater;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.lists.repository.ShoppingCartProductRepository;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ShoppingCartStrategy implements IListStrategy{
    ProductRepository productRepository;
    ShoppingCartProductRepository shoppingCartProductRepository;

    @Autowired
    public ShoppingCartStrategy(ProductRepository productRepository, ShoppingCartProductRepository shoppingCartProductRepository){
        this.productRepository = productRepository;
        this.shoppingCartProductRepository = shoppingCartProductRepository;
    }
    @Override
    public ProductList addProduct(ProductList list, RequestDTO request) throws Exception {
        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(request instanceof ShoppingCartRequestDTO){
                ShoppingCartRequestDTO shoppingCartRequest = (ShoppingCartRequestDTO) request;
                if(list instanceof ShoppingCart){
                    ShoppingCart shoppingCart = (ShoppingCart) list;
                    ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct(shoppingCart, product, shoppingCartRequest.getQuantity());
                    shoppingCart.getProducts().add(shoppingCartProduct);
                    //INSERTAR METODO ACTUALIZAR PRECIO
                    shoppingCartProductRepository.save(shoppingCartProduct);
                    return shoppingCart;
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
        Optional<Product> productOptional = productRepository.findById(request.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(request instanceof ShoppingCartRequestDTO){
                ShoppingCartRequestDTO shoppingCartRequest = (ShoppingCartRequestDTO) request;
                if(list instanceof ShoppingCart){
                    ShoppingCart shoppingCart = (ShoppingCart) list;
                    Optional<ShoppingCartProduct> targetProduct = shoppingCart.getProducts().stream().filter(cp -> cp.getProduct().getName().equals(request.getProductId())).findFirst();
                    if(targetProduct.isPresent()){
                        shoppingCart.getProducts().remove(targetProduct.get());
                        //INSERTAR METODO ACTUALIZAR PRECIO
                        shoppingCartProductRepository.delete(targetProduct.get());
                        return shoppingCart;
                    } else{
                        throw new ProductNotInListException("Product not found in Shopping Cart");
                    }
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
