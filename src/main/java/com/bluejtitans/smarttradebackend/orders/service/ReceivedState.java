package com.bluejtitans.smarttradebackend.orders.service;

import com.bluejtitans.smarttradebackend.exception.NotImplementedInCurrentStateException;
import com.bluejtitans.smarttradebackend.exception.ProductAlreadyReviewedException;
import com.bluejtitans.smarttradebackend.exception.ProductAvailabilityNotFoundException;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import com.bluejtitans.smarttradebackend.orders.models.OrderProduct;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;

import java.util.Optional;

public class ReceivedState implements OrderState {
    public void changeAddress(Order order, String newAddress) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in received state");
    }

    public void cancelOrder(Order order) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in received state");
    }

    public void postReview(Order order, ProductAvailability pa, double valoration, String comment) throws Exception {
        Optional<OrderProduct> target = order.getOrderProducts().stream()
                .filter(orderReview -> orderReview.getProductAvailability().equals(pa))
                .findFirst();
        if(target.isPresent()){
            OrderProduct reviewProduct = target.get();
            if(!reviewProduct.isReviewed()){
                reviewProduct.setReviewed(true);
                reviewProduct.setComment(comment);
                reviewProduct.setValoration(valoration);
            } else{
                throw new ProductAlreadyReviewedException("Product already reviewed");
            }
        } else{
            throw new ProductAvailabilityNotFoundException("Product availability not found");
        }
    }
}
