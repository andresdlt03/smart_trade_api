package com.bluejtitans.smarttradebackend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bluejtitans.smarttradebackend.exception.ListDoesntExistException;
import com.bluejtitans.smarttradebackend.lists.DTO.ListRequestDTO;
import com.bluejtitans.smarttradebackend.lists.DTO.ListResponseDTO;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.lists.model.Wishlist;
import com.bluejtitans.smarttradebackend.lists.repository.ProductListRepository;
import com.bluejtitans.smarttradebackend.lists.service.IListStrategy;
import com.bluejtitans.smarttradebackend.lists.service.ListService;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.users.model.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ListServiceTest {

    @Mock
    private ProductListRepository listRepository;

    @Mock
    private IListStrategy listStrategy;

    @InjectMocks
    private ListService listService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetList_Wishlist() throws Exception {
        Wishlist wishlist = new Wishlist();
        when(listRepository.findWishlistByClientEmail("client@example.com")).thenReturn(Optional.of(wishlist));

        ProductList result = listService.getList("client@example.com", "wishlist");

        assertEquals(wishlist, result);
        verify(listRepository, times(1)).findWishlistByClientEmail("client@example.com");
    }

    @Test
    void testGetList_ListDoesntExist() {
        assertThrows(ListDoesntExistException.class, () -> {
            listService.getList("client@example.com", "nonexistent");
        });
    }

    @Test
    void testAddProduct() throws Exception {
        ProductList productList = new ProductList();
        ListRequestDTO request = new ListRequestDTO();

        when(listStrategy.addProduct(request)).thenReturn(productList);
        when(listRepository.save(productList)).thenReturn(productList);

        ProductList result = listService.addProduct(listStrategy, request);

        assertEquals(productList, result);
        verify(listRepository, times(1)).save(productList);
    }

    @Test
    void testRemoveProduct() throws Exception {
        ProductList productList = new ProductList();
        ListRequestDTO request = new ListRequestDTO();

        when(listStrategy.removeProduct(request)).thenReturn(productList);
        when(listRepository.save(productList)).thenReturn(productList);

        ProductList result = listService.removeProduct(listStrategy, request);

        assertEquals(productList, result);
        verify(listRepository, times(1)).save(productList);
    }

    @Test
    void testSetResponseDTO_Wishlist() {
        Wishlist wishlist = getInitialicedWishlist();

        ListResponseDTO response = new ListResponseDTO();
        response.setProducts(new ArrayList<>());

        listService.setResponseDTO(response, wishlist, "wishlist");

        assertEquals(2, response.getProducts().size());
        assertEquals("Product1", response.getProducts().get(0).getProduct().getName());
        assertEquals("Seller1", response.getProducts().get(0).getSeller());
        assertEquals(100.0, response.getProducts().get(0).getPrice());

        assertEquals("Product2", response.getProducts().get(1).getProduct().getName());
        assertEquals("Seller2", response.getProducts().get(1).getSeller());
        assertEquals(200.0, response.getProducts().get(1).getPrice());
    }

    private static Wishlist getInitialicedWishlist() {
        Wishlist wishlist = new Wishlist();
        Product product1 = new Product();
        product1.setName("Product1");
        Seller seller1 = new Seller();
        seller1.setName("Seller1");
        ProductAvailability pa1 = new ProductAvailability();
        pa1.setProduct(product1);
        pa1.setSeller(seller1);
        pa1.setPrice(100.0);

        Product product2 = new Product();
        product2.setName("Product2");
        Seller seller2 = new Seller();
        seller2.setName("Seller2");
        ProductAvailability pa2 = new ProductAvailability();
        pa2.setProduct(product2);
        pa2.setSeller(seller2);
        pa2.setPrice(200.0);

        wishlist.setProductAvailabilities(List.of(pa1, pa2));
        return wishlist;
    }

    @Test
    void testSetResponseDTO_ShoppingCart() {
        ShoppingCart shoppingCart = getInitialicedShoppingCart();

        ListResponseDTO response = new ListResponseDTO();
        response.setCartProducts(new ArrayList<>());

        listService.setResponseDTO(response, shoppingCart, "shoppingcart");

        assertEquals(2, response.getCartProducts().size());
        assertEquals(100.0, response.getCartPrice());
        assertEquals(21.0, response.getIVA());
        assertEquals(121.0, response.getProductsPrice());
    }

    private static ShoppingCart getInitialicedShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Product product1 = new Product();
        product1.setName("Product1");
        Seller seller1 = new Seller();
        seller1.setName("Seller1");
        ProductAvailability pa1 = new ProductAvailability();
        pa1.setProduct(product1);
        pa1.setSeller(seller1);
        pa1.setPrice(50.0);

        Product product2 = new Product();
        product2.setName("Product2");
        Seller seller2 = new Seller();
        seller2.setName("Seller2");
        ProductAvailability pa2 = new ProductAvailability();
        pa2.setProduct(product2);
        pa2.setSeller(seller2);
        pa2.setPrice(70.0);

        ShoppingCartProduct scp1 = new ShoppingCartProduct();
        scp1.setProductAvailability(pa1);
        scp1.setQuantity(1);

        ShoppingCartProduct scp2 = new ShoppingCartProduct();
        scp2.setProductAvailability(pa2);
        scp2.setQuantity(1);

        shoppingCart.setShoppingCartProducts(List.of(scp1, scp2));
        shoppingCart.setTotalPrice(100.0);
        shoppingCart.setIva(21.0);
        shoppingCart.setProductsPrice(121.0);
        return shoppingCart;
    }

}
