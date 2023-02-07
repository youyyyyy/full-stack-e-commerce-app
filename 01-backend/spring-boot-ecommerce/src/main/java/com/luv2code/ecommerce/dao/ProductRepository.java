package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

// default path to: http://localhost:8080/api/products
@CrossOrigin("http://localhost:4200")  // accepts calls from web browser scripts for this origin
public interface ProductRepository extends JpaRepository<Product, Long> {

}
