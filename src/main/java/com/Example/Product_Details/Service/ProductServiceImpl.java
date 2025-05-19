package com.Example.Product_Details.Service;

import com.Example.Product_Details.Entity.Product;
import com.Example.Product_Details.Exception.CustomException;
import com.Example.Product_Details.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Example.Product_Details.Exception.CustomException.*;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductServiceInterface {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public String saveProduct(Product product) {
       Product product1=productRepository.save(product);
        return "Product has been Saved";
    }

    @Override
    public Product getProductById(Long prodId) {
        Product product=productRepository.findById(prodId).orElseThrow(()-> new productNotFoundById ("User "+prodId+" has not been found"));
        return product;
    }

    @Override
    public List<Product> getProductByPrice(){
        List<Product> product=productRepository.findByPriceGreaterThan(50000.0);
        return product;
    }
}
