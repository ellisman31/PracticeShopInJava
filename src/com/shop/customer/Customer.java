package com.shop.customer;

import com.shop.inventory.Cart;

public class Customer {

    private User user;
    private final Cart cart;

    public Customer(User user) {
        this.user = user;
        this.cart = new Cart(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "user=" + user.toString() +
                ", cart=" + cart.getInformationOfTheCart() +
                '}';
    }
}
