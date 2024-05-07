package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.exception.ListDoesntExistException;
import com.bluejtitans.smarttradebackend.lists.DTO.*;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import com.bluejtitans.smarttradebackend.lists.repository.*;
import com.bluejtitans.smarttradebackend.lists.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class ListService {
    private final ProductListRepository listRepository;
    private final IListStrategy listStrategy;

    @Autowired
    public ListService(ProductListRepository listRepository, IListStrategy listStrategy){
        this.listRepository = listRepository;
        this.listStrategy = listStrategy;
    }
    public ProductList getList(String clientId, String listType) throws Exception{
        if(listType.equals("wishlist")){
            return listRepository.findWishlistByClientId(clientId).get();
        } else if(listType.equals("savedforlater")){
            return listRepository.findSavedForLaterByClientId(clientId).get();
        } else if(listType.equals("shoppingcart")) {
            return listRepository.findShoppingCartByClientId(clientId).get();
        } else if (listType.equals("giftList")) {
            return listRepository.findGiftListByClientId(clientId).get();
        } else{
            throw new ListDoesntExistException("No existe la lista");
        }
    }

    public ProductList addProduct(String clientId, String listType, IListStrategy listStrategy, ListRequestDTO request) throws Exception {
        ProductList list = getList(clientId, listType);
        ProductList modifiedList = listStrategy.addProduct(list, request);
        listRepository.save(modifiedList);
        return modifiedList;
    }

    public ProductList removeProduct(String clientId, String listType, IListStrategy listStrategy, ListRequestDTO request) throws Exception {
        ProductList list = getList(clientId, listType);
        ProductList modifiedList = listStrategy.removeProduct(list, request);
        listRepository.save(modifiedList);
        return modifiedList;
    }

    public void setResponseDTO(ListResponseDTO response, ProductList list, String listType){
        List<ProductAvailability> paList;
        List<ProductDTO> productDTOList;

        switch (listType) {
            case "wishlist":
                Wishlist wishlist = (Wishlist) list;
                paList = wishlist.getProductAvailabilities();
                productDTOList = response.getProducts();
                for (ProductAvailability pa : paList) {
                    productDTOList.add(new ProductDTO(pa.getProduct(), pa.getSeller().getName(), pa.getPrice()));
                }
                response.setProducts(productDTOList);
                break;
            case "savedforlater":
                SavedForLater savedForLater = (SavedForLater) list;
                paList = savedForLater.getProductAvailabilities();
                productDTOList = response.getProducts();
                for (ProductAvailability pa : paList) {
                    productDTOList.add(new ProductDTO(pa.getProduct(), pa.getSeller().getName(), pa.getPrice()));
                }
                response.setProducts(productDTOList);
                break;
            case "shoppingCart":
                ShoppingCart shoppingCart = (ShoppingCart) list;
                List<ShoppingCartProduct> shoppingProductList = shoppingCart.getShoppingCartProducts();
                List<ShoppingProductDTO> shoppingProductDTOList = response.getCartProducts();
                for (ShoppingCartProduct scp : shoppingProductList) {
                    shoppingProductDTOList.add(new ShoppingProductDTO(scp.getQuantity(), scp.getProductAvailability().getProduct(), scp.getProductAvailability().getSeller().getName(), scp.getProductAvailability().getPrice()));
                }
                response.setCartProducts(shoppingProductDTOList);
                response.setCartPrice(shoppingCart.getTotalPrice());
                response.setIVA(shoppingCart.getIva());
                response.setProductsPrice(shoppingCart.getProductsPrice());
                break;
            case "giftList":
                GiftList giftList = (GiftList) list;
                List<PersonGift> personGiftList = giftList.getPersonGifts();
                List<GiftProductDTO> giftProductDTOList = response.getGiftProducts();
                for (PersonGift pg : personGiftList) {
                    giftProductDTOList.add(new GiftProductDTO(pg.getDate(), pg.getReceiver(), pg.getProductAvailability().getProduct(), pg.getProductAvailability().getSeller().getName(), pg.getProductAvailability().getPrice()));
                }
                response.setGiftProducts(giftProductDTOList);
                break;
            default:
                // Manejar caso predeterminado si es necesario
                break;
        }
    }
}
