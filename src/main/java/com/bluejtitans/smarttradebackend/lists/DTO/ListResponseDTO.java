package com.bluejtitans.smarttradebackend.lists.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResponseDTO {
    //For wishlist and saved for later
    private List<ProductDTO> products;

    //For shopping cart
    private List<ShoppingProductDTO> cartProducts;

    //For gifts
    private List<GiftProductDTO> giftProducts;

    //About shopping cart
    private double cartPrice;
    private double IVA;
    private double productsPrice;

    public List<ShoppingProductDTO> getCartProducts() {
        return cartProducts;
    }

    public void setCartPrice(double cartPrice) {
        this.cartPrice = cartPrice;
    }
}
