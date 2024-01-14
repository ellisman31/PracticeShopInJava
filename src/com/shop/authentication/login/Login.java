package com.shop.authentication.login;

import com.shop.Shop;
import com.shop.customer.Customer;
import com.shop.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Login {

    private Shop shop;
    private Scanner scanner;

    public Login(Shop shop) {
        this.shop = shop;
        this.scanner = Util.SCANNER;
    }

    public Customer defaultLogin() {
        Optional<Customer> currentCustomer = checkForUserByLoginInformation();
        return currentCustomer.orElse(null);
    }

    private Optional<Customer> checkForUserByLoginInformation() {
        List<String> loginInformation = informationForLogin();

        return this.getShop().getListOfCustomers().stream()
                .filter(customer ->
                        customer.getUser().getEmailAddress().equals(loginInformation.get(0)))
                .filter(customer -> customer.getUser().getPassword().equals(loginInformation.get(1)))
                .findFirst();
    }

    private List<String> informationForLogin() {
        System.out.println("Please provide your email address: ");
        String emailAddress = getScanner().next();

        while (!Util.isEmailAddressValid(emailAddress)) {
            System.out.println("Invalid email address format! Please provide your email address again!\n (Example: test@test.com): ");
            emailAddress = getScanner().next();
        }
        System.out.println("Please provide your password: ");
        String password = getScanner().next();

        List<String> loginInformation = new ArrayList<>();
        loginInformation.add(emailAddress);
        loginInformation.add(password);

        return loginInformation;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
