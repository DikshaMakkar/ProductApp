package com.example.product.model;

public class PriceRange {
    private double minPrice;
    private double maxPrice;

    public PriceRange(double minPrice, double maxPrice){
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
