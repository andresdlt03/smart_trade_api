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
    private IListStrategy listStrategy;

    @Autowired
    public ListService(ProductListRepository listRepository){
        this.listRepository = listRepository;
    }
    public ProductList getList(String clientId, String listType) throws Exception{
        return switch (listType) {
            case "wishlist" -> listRepository.findWishlistByClientEmail(clientId).get();
            case "savedforlater" -> listRepository.findSavedForLaterByClientEmail(clientId).get();
            case "shoppingcart" -> listRepository.findShoppingCartByClientEmail(clientId).get();
            case "giftlist" -> listRepository.findGiftListByClientEmail(clientId).get();
            default -> throw new ListDoesntExistException("No existe la lista");
        };
    }

    public ProductList addProduct(IListStrategy listStrategy, ListRequestDTO request) throws Exception {
        ProductList modifiedList = listStrategy.addProduct(request);
        listRepository.save(modifiedList);
        return modifiedList;
    }

    public ProductList removeProduct(IListStrategy listStrategy, ListRequestDTO request) throws Exception {
        ProductList modifiedList = listStrategy.removeProduct(request);
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
            case "shoppingcart":
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
            case "giftlist":
                GiftList giftList = (GiftList) list;
                List<PersonGift> personGiftList = giftList.getPersonGifts();
                List<GiftProductDTO> giftProductDTOList = response.getGiftProducts();
                for (PersonGift pg : personGiftList) {
                    //List<ProductAvailability> productAvailabilities = pg.getProductAvailabilities();
                    List<PersonGiftProductAvailability> personGiftProductAvailabilities = pg.getPersonGiftProductAvailabilities();
                    GiftProductDTO giftProductDTO = new GiftProductDTO(pg.getReceiver());
                    for (PersonGiftProductAvailability pgpa : personGiftProductAvailabilities) {
                        giftProductDTO.getPersonGifts().add(new PersonGiftsDTO(pgpa.getProductAvailability().getProduct(), pgpa.getProductAvailability().getSeller().getName(), pgpa.getProductAvailability().getPrice(), pgpa.getDate()));
                    }
                    giftProductDTOList.add(giftProductDTO);
                }
                response.setGiftProducts(giftProductDTOList);
                break;
            default:
                // Manejar caso predeterminado si es necesario
                break;
        }
    }
}
