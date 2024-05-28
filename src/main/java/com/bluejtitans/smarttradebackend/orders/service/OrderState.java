package com.bluejtitans.smarttradebackend.orders.service;

import com.bluejtitans.smarttradebackend.exception.ProductAlreadyReviewedException;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;

public interface OrderState {
    void changeAddress(Order order, String newDirection) throws Exception;

    void cancelOrder(Order order) throws Exception;

    void postReview(Order order, ProductAvailability pa, double valoration, String review) throws Exception;
}
