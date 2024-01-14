package com.shop.inventory;

import com.shop.customer.Customer;
import com.shop.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {

    private List<Product> listOfProducts;
    private Customer customer;

    public Cart(Customer customer) {
        this.listOfProducts = new ArrayList<>();
        this.customer = customer;
    }

    public BigDecimal totalAmount() {
        BigDecimal sumPriceOfTheProducts = BigDecimal.ZERO;
        for (Product product : listOfProducts) {
            sumPriceOfTheProducts = sumPriceOfTheProducts.add(product.getPrice());
        }

        return sumPriceOfTheProducts;
    }

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addProductToCart(String productName, Inventory inventory) {
        Product product = inventory.findProduct(productName);
        boolean isProductAvailable = inventory.isProductAvailability(product);
        if (isProductAvailable) {
            this.listOfProducts.add(product);
            inventory.removeProductFromInventory(product);
            System.out.println("The " + product.getName() + " had been successfully added to the cart!");
        } else {
            System.out.println("The product is not exist!");
        }
    }

    public void removeProductFromCart(String productName, Inventory inventory) {
        Optional<Product> findRemovableProduct = getListOfProducts().stream()
                .filter(product -> product.equals(inventory.findProduct(productName)))
                .findFirst();

        if (findRemovableProduct.isPresent()) {
            listOfProducts.remove(findRemovableProduct.get());
            inventory.addProductToInventory(findRemovableProduct.get());
            System.out.println("The " + findRemovableProduct.get().getName() + " is successfully removed from the cart!");
        } else {
            System.out.println("The removable product is not found in the cart!");
        }
    }

    public String getInformationOfTheCart() {
        return this.listOfProducts + " | Total Amount of the Products: " + totalAmount();
    }
}
