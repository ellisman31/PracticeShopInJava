package com.shop;

import com.shop.customer.Customer;
import com.shop.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    private Menu menu;
    private List<Customer> listOfCustomers;
    private Inventory inventory;

    public Shop() {
        this.listOfCustomers = new ArrayList<>();
        this.inventory = new Inventory(getListOfCustomers());
        this.menu = new Menu(this);
    }

    public void addRegisteredCustomer(Customer customer) {
        listOfCustomers.add(customer);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public void setListOfCustomers(List<Customer> listOfCustomers) {
        this.listOfCustomers = listOfCustomers;
    }
}
