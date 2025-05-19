package com.Example.Product_Details.Controller;
import com.Example.Product_Details.Entity.Product;
import com.Example.Product_Details.Service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("Product")
public class ProductController {
    @Autowired
    private final ProductServiceImpl productService;

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody Product product){
        String response = productService.saveProduct(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name="id") Long prodId){
        Product product=productService.getProductById(prodId);
        return new ResponseEntity<>(product,HttpStatus.OK);
        }

    @GetMapping("getProductPrice/gt50000")
    public ResponseEntity<List<Product>> getProductPrice(){
        List<Product> product=productService.getProductByPrice();
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
