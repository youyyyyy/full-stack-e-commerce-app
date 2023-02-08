package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

// default path to: http://localhost:8080/api/products
@CrossOrigin("http://localhost:4200")  // accepts calls from web browser scripts for this origin
public interface ProductRepository extends JpaRepository<Product, Long> {
    // spring Data REST automatically exposes endpoint http://localhost:8080/api/products/search/findByCategoryId?id=2
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
}
