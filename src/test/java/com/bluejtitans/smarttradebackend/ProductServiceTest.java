package com.bluejtitans.smarttradebackend;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bluejtitans.smarttradebackend.exception.UserNotRegisteredException;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityBySeller;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductAvailabilityWithCategory;
import com.bluejtitans.smarttradebackend.products.dto.responses.ProductWithCategory;
import com.bluejtitans.smarttradebackend.products.model.Product;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import com.bluejtitans.smarttradebackend.products.repository.ProductRepository;
import com.bluejtitans.smarttradebackend.products.service.ProductService;
import com.bluejtitans.smarttradebackend.users.model.Seller;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductAvailabilityRepository productAvailabilityRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProduct_NewProduct() {
        Product product = new Product();
        product.setName("Product1");

        ProductAvailability productAvailability = new ProductAvailability();
        productAvailability.setPrice(100.0);

        Seller seller = new Seller();
        seller.setEmail("seller@example.com");

        when(productRepository.findById(product.getName())).thenReturn(Optional.empty());
        when(userRepository.findSellerById(seller.getEmail())).thenReturn(seller);

        productService.saveProduct(new ProductService.SaveProductRequest(product, productAvailability, seller.getEmail()));

        verify(productRepository, times(1)).save(product);
        verify(productAvailabilityRepository, times(1)).save(productAvailability);
        assertEquals(product, productAvailability.getProduct());
        assertEquals(seller, productAvailability.getSeller());
        assertEquals(100.0, product.getPrice());
    }

    @Test
    void testSaveProduct_ExistingProduct() {
        Product existingProduct = new Product();
        existingProduct.setName("Product1");
        existingProduct.setPrice(150.0);

        ProductAvailability newProductAvailability = new ProductAvailability();
        newProductAvailability.setPrice(100.0);
        newProductAvailability.setStock(12);

        Seller existingSeller = new Seller();
        existingSeller.setEmail("existingSeller@example.com");

        Seller newSeller = new Seller();
        newSeller.setEmail("newSeller@example.com");

        when(productRepository.findById(existingProduct.getName())).thenReturn(Optional.of(existingProduct));
        when(userRepository.findSellerById(existingSeller.getEmail())).thenReturn(existingSeller);
        when(userRepository.findSellerById(newSeller.getEmail())).thenReturn(newSeller);

        productService.saveProduct(new ProductService.SaveProductRequest(existingProduct, newProductAvailability, newSeller.getEmail()));

        verify(productRepository, times(1)).save(existingProduct);
        verify(productAvailabilityRepository, times(1)).save(newProductAvailability);

        assertEquals(existingProduct, newProductAvailability.getProduct());
        assertEquals(newSeller, newProductAvailability.getSeller());
        assertEquals(100.0, newProductAvailability.getPrice());
    }



    @Test
    void testSaveProduct_SellerNotFound() {
        Product product = new Product();
        product.setName("Product1");

        ProductAvailability productAvailability = new ProductAvailability();
        productAvailability.setPrice(100.0);

        String sellerEmail = "seller@example.com";

        when(userRepository.findSellerById(sellerEmail)).thenReturn(null);

        assertThrows(UserNotRegisteredException.class, () -> {
            productService.saveProduct(new ProductService.SaveProductRequest(product, productAvailability, sellerEmail));
        });

        verify(productRepository, never()).save(any());
        verify(productAvailabilityRepository, never()).save(any());
    }

    @Test
    void testGetProductsByVerified() {
        Product product1 = new Product();
        product1.setVerified(true);
        product1.setCategory("technology");

        Product product2 = new Product();
        product2.setVerified(true);
        product2.setCategory("technology");

        List<Product> products = List.of(product1, product2);

        when(productRepository.findAllByVerified(true)).thenReturn(products);

        List<ProductWithCategory> result = productService.getProductsByVerified(true);

        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAllByVerified(true);
    }

    @Test
    void testGetProductById_Found() {
        Product product = new Product();
        product.setName("Product1");
        product.setCategory("technology");

        when(productRepository.findById("Product1")).thenReturn(Optional.of(product));

        ProductWithCategory result = productService.getProductById("Product1");

        assertNotNull(result);
        assertEquals(product, result.getProduct());
        verify(productRepository, times(1)).findById("Product1");
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById("Product1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productService.getProductById("Product1");
        });

        verify(productRepository, times(1)).findById("Product1");
    }

    @Test
    void testGetProductAvailabilities() {

        List<ProductAvailability> availabilities = getProductAvailabilities();

        when(productAvailabilityRepository.findAllByProductId("Product1")).thenReturn(availabilities);

        List<ProductAvailabilityBySeller> result = productService.getProductAvailabilities("Product1");

        assertEquals(2, result.size());

        verify(productAvailabilityRepository, times(1)).findAllByProductId("Product1");

        assertEquals(100.0, result.get(0).getPrice());
        assertEquals(10, result.get(0).getStock());
        assertEquals("seller1@example.com", result.get(0).getSellerEmail());

        assertEquals(200.0, result.get(1).getPrice());
        assertEquals(5, result.get(1).getStock());
        assertEquals("seller2@example.com", result.get(1).getSellerEmail());
    }

    private static List<ProductAvailability> getProductAvailabilities() {
        ProductAvailability availability1 = new ProductAvailability();
        availability1.setPrice(100.0);
        availability1.setStock(10);
        Seller seller1 = new Seller();
        seller1.setEmail("seller1@example.com");
        availability1.setSeller(seller1);

        ProductAvailability availability2 = new ProductAvailability();
        availability2.setPrice(200.0);
        availability2.setStock(5);
        Seller seller2 = new Seller();
        seller2.setEmail("seller2@example.com");
        availability2.setSeller(seller2);

        return List.of(availability1, availability2);
    }


    @Test
    void testGetProductsBySeller() {

        Product product1 = new Product();
        product1.setName("Product1");
        product1.setCategory("Category1");

        Product product2 = new Product();
        product2.setName("Product2");
        product2.setCategory("Category2");

        ProductAvailability availability1 = new ProductAvailability();
        availability1.setPrice(100.0);
        availability1.setStock(10);
        availability1.setProduct(product1);

        ProductAvailability availability2 = new ProductAvailability();
        availability2.setPrice(200.0);
        availability2.setStock(5);
        availability2.setProduct(product2);

        Seller seller = new Seller();
        seller.setEmail("seller@example.com");
        availability1.setSeller(seller);
        availability2.setSeller(seller);

        List<ProductAvailability> availabilities = List.of(availability1, availability2);

        when(productAvailabilityRepository.findAllBySellerId("seller@example.com")).thenReturn(availabilities);

        List<ProductAvailabilityWithCategory> result = productService.getProductsBySeller("seller@example.com");

        assertEquals(2, result.size());

        verify(productAvailabilityRepository, times(1)).findAllBySellerId("seller@example.com");

        assertEquals("Category1", result.get(0).getCategory());
        assertEquals("Category2", result.get(1).getCategory());
    }


    @Test
    void testSetProductVerification_Found() {
        Product product = new Product();
        product.setName("Product1");

        when(productRepository.findById("Product1")).thenReturn(Optional.of(product));

        productService.setProductVerification("Product1", true);

        verify(productRepository, times(1)).save(product);
        assertTrue(product.isVerified());
    }

    @Test
    void testSetProductVerification_NotFound() {
        when(productRepository.findById("Product1")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            productService.setProductVerification("Product1", true);
        });

        verify(productRepository, never()).save(any());
    }
}
