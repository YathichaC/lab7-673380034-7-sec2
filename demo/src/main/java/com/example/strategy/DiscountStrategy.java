package com.example.strategy;

public interface DiscountStrategy {
    double calculatePrice(double price);
    String getDiscountName();
}
