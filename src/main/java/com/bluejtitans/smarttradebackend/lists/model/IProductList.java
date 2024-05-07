package com.bluejtitans.smarttradebackend.lists.model;

import java.util.List;
import com.bluejtitans.smarttradebackend.products.model.Product;
public interface IProductList {
    public List<Product> getProducts();
    public void setProducts(List<Product> products);
    public void addProduct(Product product);
    public Product removeProduct(Product product);
}
