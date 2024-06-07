package com.bluejtitans.smarttradebackend.orders.service;

import com.bluejtitans.smarttradebackend.exception.OrderNotFoundException;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCart;
import com.bluejtitans.smarttradebackend.lists.model.ShoppingCartProduct;
import com.bluejtitans.smarttradebackend.lists.repository.ProductListRepository;
import com.bluejtitans.smarttradebackend.orders.DTO.*;
import com.bluejtitans.smarttradebackend.orders.models.*;
import com.bluejtitans.smarttradebackend.orders.repository.OrderRepository;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import com.bluejtitans.smarttradebackend.products.repository.ProductAvailabilityRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import com.bluejtitans.smarttradebackend.users.model.Client;
import com.bluejtitans.smarttradebackend.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductListRepository productListRepository;
    private ProductAvailabilityRepository productAvailabilityRepository;
    private OrderState state;
    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductListRepository productListRepository, ProductAvailabilityRepository productAvailabilityRepository){
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productListRepository = productListRepository;
        this.productAvailabilityRepository = productAvailabilityRepository;
    }

    public AllOrderResponseDTO getAllOrders(String clientEmail){
        AllOrderResponseDTO response = new AllOrderResponseDTO();
        List<Order> allOrders = orderRepository.findAllByClientEmail(clientEmail);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for(Order order : allOrders){
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
            setResponseDTO(orderResponseDTO, order);
            orderResponseDTOList.add(orderResponseDTO);
        }
        response.setAllOrders(orderResponseDTOList);
        return response;
    }

    public OrderResponseDTO getOrder(Long Id) throws Exception{
        Optional<Order> target = orderRepository.findById(Id);
        if(target.isPresent()){
            Order order = target.get();
            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            return response;
        } else{
            throw new OrderNotFoundException("Order not found");
        }
    }

    public OrderResponseDTO createOrder(String clientEmail, OrderRequestDTO request) {
        try {
            Client client = (Client) userRepository.findById(clientEmail).orElseThrow(() -> new IllegalArgumentException("Client not found"));
            ShoppingCart shoppingCart = productListRepository.findShoppingCartByClientEmail(clientEmail).orElseThrow(() -> new IllegalArgumentException("Shopping cart not found"));

            List<OrderProduct> orderProducts = new ArrayList<>();
            Order order = new Order(request.getDate(), request.getPayment(), request.getShippingAddress(), request.getBillingAddress(), shoppingCart.getShoppingCartProducts().size(), shoppingCart.getTotalPrice(), orderProducts, EnumStates.MADE, client);
            for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getShoppingCartProducts()) {
                OrderProduct orderProduct = new OrderProduct(shoppingCartProduct.getProductAvailability(), shoppingCartProduct.getQuantity(), order);
                orderProducts.add(orderProduct);
            }
            order.setOrderProducts(orderProducts);
            orderRepository.save(order);

            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            buyShoppingCart(shoppingCart);

            return response;
        } catch (Exception e) {
            throw e;  // Rethrow the exception to be handled by the controller
        }
    }

    public OrderResponseDTO cancelOrder(Long Id) throws Exception{
        Optional<Order> target = orderRepository.findById(Id);
        if(target.isPresent()){
            Order order = target.get();
            order.getOrderState().cancelOrder(order);
            orderRepository.save(order);
            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            return response;
        } else{
            throw new OrderNotFoundException("Order not found");
        }
    }

    public OrderResponseDTO postReview(Long Id, ProductReviewDTO request) throws Exception{
        Optional<Order> target = orderRepository.findById(Id);
        if(target.isPresent()){
            Order order = target.get();
            ProductAvailability pa = productAvailabilityRepository.findProductAvailabilityByProductIdAndSellerId(request.getProductId(), request.getSellerId());
            order.getOrderState().postReview(order, pa, request.getValoration(), request.getComment());
            orderRepository.save(order);
            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            return response;
        } else{
            throw new OrderNotFoundException("Order not found");
        }
    }

    public OrderResponseDTO changeShippingAddress(Long Id, String newShippingAddress) throws Exception{
        Optional<Order> target = orderRepository.findById(Id);
        if(target.isPresent()){
            Order order = target.get();
            order.getOrderState().changeAddress(order, newShippingAddress);
            orderRepository.save(order);
            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            return response;
        } else{
            throw new OrderNotFoundException("Order not found");
        }
    }

    public OrderResponseDTO changeState(Long Id, String newState) throws Exception{
        Optional<Order> target = orderRepository.findById(Id);
        if(target.isPresent()){
            Order order = target.get();
            switch (newState) {
                case "made" -> order.setState(EnumStates.MADE);
                case "sent" -> order.setState(EnumStates.SENT);
                case "received" -> order.setState(EnumStates.RECEIVED);
                case "cancelled" -> order.setState(EnumStates.CANCELLED);
            };
            orderRepository.save(order);
            OrderResponseDTO response = new OrderResponseDTO();
            setResponseDTO(response, order);
            return response;
        } else{
            throw new OrderNotFoundException("Order not found");
        }
    }
    public void setResponseDTO(OrderResponseDTO response, Order order){
        response.setId(order.getId());
        response.setBillingAddress(order.getBillingAddress());
        response.setDate(order.getDate());
        response.setPayment(order.getPayment());
        response.setProductsNumber(order.getProductsNumber());
        response.setTotalPrice(order.getTotalPrice());
        response.setShippingAddress(order.getShippingAddress());
        OrderState state = order.getOrderState();
        if(state instanceof MadeState){
            response.setState("Made");
        } else if(state instanceof SentState){
            response.setState("Sent");
        } else if(state instanceof ReceivedState){
            response.setState("Received");
        } else{
            response.setState("Cancelled");
        }
        List<OrderProduct> orderProducts = order.getOrderProducts();
        List<OrderProductDTO> orderProductDTOS = new ArrayList<>();
        for(OrderProduct orderProduct : orderProducts){
            orderProductDTOS.add(new OrderProductDTO(orderProduct.isReviewed(), orderProduct.getValoration(), orderProduct.getComment(), orderProduct.getQuantity(), orderProduct.getProductAvailability().getProduct(), orderProduct.getProductAvailability().getSeller().getName(), orderProduct.getProductAvailability().getPrice()));
        }
        response.setOrderProducts(orderProductDTOS);
    }

    public void buyShoppingCart(ShoppingCart shoppingCart){
        List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
        for(ShoppingCartProduct shoppingCartProduct : shoppingCartProducts){
            ProductAvailability pa = shoppingCartProduct.getProductAvailability();
            pa.setStock(pa.getStock() - shoppingCartProduct.getQuantity());
            productAvailabilityRepository.save(pa);
        }
        shoppingCart.setShoppingCartProducts(new ArrayList<>());
        shoppingCart.setIva(0.0);
        shoppingCart.setProductsPrice(0.0);
        shoppingCart.setTotalPrice(0.0);
        productListRepository.save(shoppingCart);
    }
}
