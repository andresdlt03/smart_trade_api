package com.bluejtitans.smarttradebackend.orders.service;

import com.bluejtitans.smarttradebackend.exception.NotImplementedInCurrentStateException;
import com.bluejtitans.smarttradebackend.orders.models.EnumStates;
import com.bluejtitans.smarttradebackend.orders.models.Order;
import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;

public class MadeState implements OrderState{
    public void changeAddress(Order order, String newAddress) throws Exception{
        order.setShippingAddress(newAddress);
    }

    public void cancelOrder(Order order) throws Exception{
        order.setState(EnumStates.CANCELLED);
    }

    public void postReview(Order order, ProductAvailability pa, double valoration, String review) throws Exception{
        throw new NotImplementedInCurrentStateException("Not implemented in made state");
    }
}
