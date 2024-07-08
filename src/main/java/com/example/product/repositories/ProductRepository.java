package com.example.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Custom JPQL or SQL query, optional JPA can handle without this too...
    @Query(value = "SELECT * FROM products WHERE products.brand=:brandName", nativeQuery = true)
    List<Product> findByBrand(String brandName);

    @Query(value = "SELECT * FROM products WHERE products.product_price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)

    List<Product> findByPriceRange(double minPrice, double maxPrice);
}
