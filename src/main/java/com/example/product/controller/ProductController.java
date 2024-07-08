package com.example.product.controller;

import com.example.product.entities.Product;
import com.example.product.model.PriceRange;
import com.example.product.services.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:3000")  Not recommended for multiple controller classes...
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return !products.isEmpty() ? ResponseEntity.ok(products) : ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id){
        Optional<Product> productById = productService.getProductById(id);
        return !productById.isEmpty() ? ResponseEntity.ok(productById.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("group")
    public ResponseEntity getProductsGroupedByBrand(){
        Map<String, List<Product>> groupedProductByBrand = productService.getGroupedProductsByBrand();
        return !groupedProductByBrand.isEmpty() ? ResponseEntity.ok(groupedProductByBrand) : ResponseEntity.noContent().build();
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity getProductsByBrand(@PathVariable String brandName){
        List<Product> productByBrand = productService.getProductByBrand(brandName);
        return !productByBrand.isEmpty() ? ResponseEntity.ok(productByBrand) : ResponseEntity.notFound().build();
//        return !productByBrand.isEmpty() ? ResponseEntity.ok(productByBrand) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }

    @GetMapping("price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam(required = false) double minPrice, @RequestParam(required = false) double maxPrice){
        List<Product> groupedProducts = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(groupedProducts);
    }
}
