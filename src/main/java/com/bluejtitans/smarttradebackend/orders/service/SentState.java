package com.bluejtitans.smarttradebackend.orders.service;

import com.bluejtitans.smarttradebackend.exception.NotImplementedInCurrentStateException;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;

public class SentState implements OrderState{
    public void changeAddress(Order order, String newAddress) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in sent state");
    }

    public void cancelOrder(Order order) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in sent state");
    }

    public void postReview(Order order, ProductAvailability pa, double valoration, String review) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in sent state");
    }
}
