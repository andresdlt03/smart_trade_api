package com.bluejtitans.smarttradebackend.lists.service;

import com.bluejtitans.smarttradebackend.lists.DTO.*;
import com.bluejtitans.smarttradebackend.lists.model.ProductList;

public interface IListStrategy {
    ProductList addProduct(ProductList list, ListRequestDTO request) throws Exception;

    ProductList removeProduct(ProductList list, ListRequestDTO request) throws Exception;
}
