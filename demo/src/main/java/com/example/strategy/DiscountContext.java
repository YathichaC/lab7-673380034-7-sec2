package com.example.strategy;

public class DiscountContext {

    public DiscountStrategy getStrategy(String discountType) {

        if (discountType == null) {
            return new NoDiscountStrategy();
        }

        return switch (discountType) {
            case "STUDENT" -> new StudentDiscountStrategy();
            case "SEASONAL" -> new SeasonalSaleStrategy();
            case "NONE" -> new NoDiscountStrategy();
            default -> new NoDiscountStrategy();
        };
    }

    public double calculatePrice(String discountType, double price) {

        DiscountStrategy strategy = getStrategy(discountType);

        return strategy.calculatePrice(price);
    }

    public String getDiscountName(String discountType) {

        DiscountStrategy strategy = getStrategy(discountType);

        return strategy.getDiscountName();
    }
}