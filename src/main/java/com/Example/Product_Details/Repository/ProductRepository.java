package com.Example.Product_Details.Repository;

import com.Example.Product_Details.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByPriceGreaterThan(Double price);
}
