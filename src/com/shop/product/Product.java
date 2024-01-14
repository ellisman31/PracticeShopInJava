package com.shop.product;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

    private final UUID id = java.util.UUID.randomUUID();;
    private String name;
    private BigDecimal price;
    private ProductType type;
    private String description;

    public Product(String name, BigDecimal price, ProductType type, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
