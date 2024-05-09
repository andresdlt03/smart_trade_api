package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.lists.DTO.*;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;

public interface IListStrategy {
    ProductList addProduct(ListRequestDTO request) throws Exception;

    ProductList removeProduct(ListRequestDTO request) throws Exception;
}
