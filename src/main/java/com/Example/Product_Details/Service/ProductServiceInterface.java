package com.Example.Product_Details.Service;

import com.Example.Product_Details.Entity.Product;

import java.util.List;

public interface ProductServiceInterface {
    public String saveProduct(Product product);
    public Product getProductById(Long prodId);
    public List<Product> getProductByPrice();

}
