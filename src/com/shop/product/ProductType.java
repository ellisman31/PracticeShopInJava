package com.shop.product;

import com.shop.customer.role.Role;

public enum ProductType {
    FOOD,
    BEVERAGE,
    IT,
    MECHANIC,
    BEAUTY,
    CLOTH,
    HOME,
    OTHER;

    public static ProductType getProductType(String productType) {
        String productTypeUpperCase = productType.toUpperCase();
        return ProductType.valueOf(productTypeUpperCase);
    }
}
