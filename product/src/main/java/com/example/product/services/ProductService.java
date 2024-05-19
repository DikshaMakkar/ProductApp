package com.example.product.services;

import com.example.product.entities.Product;
import com.example.product.model.PriceRange;
import com.example.product.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public Map<String, List<Product>> getGroupedProductsByBrand() {
        List<Product> allProducts = productRepository.findAll();
        Map<String, List<Product>> groupedProductsByBrand = new HashMap<>();
        for(Product product: allProducts){
            String brand = product.getBrand();
            groupedProductsByBrand.computeIfAbsent(brand, key -> new ArrayList<>()).add(product);
        }
        return groupedProductsByBrand;
    }

    public List<Product> getProductByBrand(String brandName) {
        List<Product> productsByBrand = productRepository.findByBrand(brandName);
        return productsByBrand;
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice){
        List<Product> productsInRange = productRepository.findByPriceRange(minPrice, maxPrice);
//        Map<PriceRange, List<Product>> groupedProducts = new HashMap<>();
        return productsInRange;
    }
}
