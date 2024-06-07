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
    private IListStrategy ShoppingCartStrategy;

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
        when(listRepository.findWishlistByClientEmail("client@example.com")).thenReturn(Optional.empty());

        assertThrows(ListDoesntExistException.class, () -> {
            listService.getList("client@example.com", "nonexistent");
        });
    }

    @Test
    void testAddProduct() throws Exception {
        ListRequestDTO request = new ListRequestDTO();
        request.setQuantity(5);

        ShoppingCart updatedCart = new ShoppingCart();
        ShoppingCartProduct updatedCartProduct = createAndGetShoppingCartProduct("Product1", 100.0, 5);
        updatedCart.setShoppingCartProducts(List.of(updatedCartProduct));

        when(ShoppingCartStrategy.addProduct(any(ListRequestDTO.class))).thenAnswer(invocation -> {
            ListRequestDTO req = invocation.getArgument(0);
            ShoppingCart cart = new ShoppingCart();
            ShoppingCartProduct cartProduct = createAndGetShoppingCartProduct("Product1", 100.0, req.getQuantity());
            cart.setShoppingCartProducts(List.of(cartProduct));
            return cart;
        });
        when(listRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ShoppingCart resultCart = (ShoppingCart) listService.addProduct(ShoppingCartStrategy, request);

        assertNotNull(resultCart);
        assertEquals(1, resultCart.getShoppingCartProducts().size());
        ShoppingCartProduct resultProduct = resultCart.getShoppingCartProducts().get(0);
        assertEquals(updatedCartProduct.getQuantity(), resultProduct.getQuantity());

        verify(listRepository, times(1)).save(resultCart);
    }


    @Test
    void testAddProduct1() throws Exception {
        ListRequestDTO request = new ListRequestDTO();
        request.setQuantity(10);

        ShoppingCart updatedCart = new ShoppingCart();
        ShoppingCartProduct updatedCartProduct = createAndGetShoppingCartProduct("Product1", 100.0, 10);
        updatedCart.setShoppingCartProducts(List.of(updatedCartProduct));

        when(ShoppingCartStrategy.addProduct(any(ListRequestDTO.class))).thenAnswer(invocation -> {
            ListRequestDTO req = invocation.getArgument(0);
            ShoppingCart cart = new ShoppingCart();
            ShoppingCartProduct cartProduct = createAndGetShoppingCartProduct("Product1", 100.0, req.getQuantity());
            cart.setShoppingCartProducts(List.of(cartProduct));
            return cart;
        });
        when(listRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ShoppingCart resultCart = (ShoppingCart) listService.addProduct(ShoppingCartStrategy, request);

        assertNotNull(resultCart);
        assertEquals(1, resultCart.getShoppingCartProducts().size());
        ShoppingCartProduct resultProduct = resultCart.getShoppingCartProducts().get(0);
        assertEquals(updatedCartProduct.getQuantity(), resultProduct.getQuantity());

        verify(listRepository, times(1)).save(resultCart);
    }


    @Test
    void testRemoveProduct() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        ListRequestDTO request = new ListRequestDTO();
        request.setQuantity(10);

        ShoppingCartProduct shoppingCartProduct = createAndGetShoppingCartProduct("Product1", 100.0, 10);
        shoppingCart.setShoppingCartProducts(List.of(shoppingCartProduct));

        when(ShoppingCartStrategy.removeProduct(any(ListRequestDTO.class))).thenAnswer(invocation -> {
            ListRequestDTO req = invocation.getArgument(0);
            ShoppingCart cart = new ShoppingCart();
            if (shoppingCartProduct.getQuantity() > req.getQuantity()) {
                ShoppingCartProduct updatedCartProduct = createAndGetShoppingCartProduct(
                        "Product1", 100.0, shoppingCartProduct.getQuantity() - req.getQuantity());
                cart.setShoppingCartProducts(List.of(updatedCartProduct));
            } else {
                cart.setShoppingCartProducts(new ArrayList<>());
            }
            return cart;
        });
        when(listRepository.save(any(ShoppingCart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ShoppingCart resultCart = (ShoppingCart) listService.removeProduct(ShoppingCartStrategy, request);

        assertNotNull(resultCart);
        assertEquals(0, resultCart.getShoppingCartProducts().size()); // El carrito debería estar vacío
        verify(listRepository, times(1)).save(resultCart);
    }



    private static ShoppingCartProduct createAndGetShoppingCartProduct(String name, double price, int quantity) {
        Product product = new Product();
        product.setName(name);

        ProductAvailability productAvailability = new ProductAvailability();
        productAvailability.setProduct(product);
        productAvailability.setPrice(price);

        ShoppingCartProduct shoppingCartProduct = new ShoppingCartProduct();
        shoppingCartProduct.setProductAvailability(productAvailability);
        shoppingCartProduct.setQuantity(quantity);
        return shoppingCartProduct;
    }

    @Test
    void testSetResponseDTO_Wishlist() {
        Wishlist wishlist = getInitialicedWishlist();

        ListResponseDTO response = new ListResponseDTO();
        response.setProducts(new ArrayList<>());

        listService.setResponseDTO(response, wishlist, "wishlist");

        assertEquals(2, response.getProducts().size());
        assertEquals("Prod11123euct1", response.getProducts().get(0).getProduct().getName());
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
