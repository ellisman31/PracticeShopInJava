package com.shop.authentication.login.registration;

import com.shop.Shop;
import com.shop.customer.Address;
import com.shop.customer.Customer;
import com.shop.customer.User;
import com.shop.customer.role.Role;
import com.shop.util.Util;

import java.util.Scanner;

public class Registration {

    private Shop shop;
    private final Scanner scanner = Util.SCANNER;

    public Registration(Shop shop) {
        this.shop = shop;
        dummyRegisteredUser();
    }

    private void dummyRegisteredUser() {
        User dummyUser = new User("FirstName", "LastName",
                "test@test.com", "Password");
        dummyUser.setRole(Role.ADMIN);
        Address dummyAddress = new Address("City", 1111, "StreetName",
                "StreetNumber", "ApartmentNumber");
        dummyUser.setAddress(dummyAddress);
        Customer dummyCustomer = new Customer(dummyUser);

        addCustomerToList(dummyCustomer);
    }

    private void addCustomerToList(Customer customer) {
        this.getShop().getListOfCustomers().add(customer);
    }

    public void defaultRegistration() {
        User newUser = userInformationForRegistration();
        Address userAddress = addressInformationForRegistration(newUser);
        newUser.setAddress(userAddress);
        Customer newCustomer = new Customer(newUser);

        addCustomerToList(newCustomer);
        System.out.println("The registration for " + newCustomer.getUser().getEmailAddress() + " was successful!\n" +
                "You could log in to the Online-Store!");
    }

    private User userInformationForRegistration() {
        System.out.println("Please provide your first name: ");
        String firstName = getScanner().next();
        System.out.println("Please provide your last name: ");
        String lastName = getScanner().next();
        System.out.println("Please provide your email address: ");
        String emailAddress = getScanner().next();
        while (Util.isEmailAddressExists(getShop().getListOfCustomers(), emailAddress) && Util.isEmailAddressValid(emailAddress)) {
            System.out.println("The email address is already in use or the email address is in invalid format!\n" +
                    "Please try it again! (Example: test@test.com): ");
            emailAddress = getScanner().next();
        }
        System.out.println("Please provide your password: ");
        String password = getScanner().next();

        return userInitializationForRegistration(firstName, lastName, emailAddress, password);
    }

    private User userInitializationForRegistration(String firstName, String lastName,
                                                   String emailAddress, String password) {

        return new User(firstName, lastName, emailAddress, password);
    }
    private Address addressInformationForRegistration(User newUser) {
        System.out.println("\nAddress related information: \n");
        System.out.println("Please provide your city name: ");
        String city = getScanner().next();
        System.out.println("Please provide your postal code: ");
        int postalCode = getScanner().nextInt();
        System.out.println("Please provide your street name: ");
        String streetName = getScanner().next();
        System.out.println("Please provide your street number: ");
        String streetNumber = getScanner().next();
        System.out.println("Please provide your apartment number: ");
        String apartmentNumber = getScanner().next();

        return addressInitializationForRegistration(city, postalCode,
                streetName, streetNumber, apartmentNumber, newUser);
    }

    private Address addressInitializationForRegistration(String city,
                                                   int postalCode, String streetName,
                                                   String streetNumber, String apartmentNumber, User newUser) {

        return new Address(newUser, city, postalCode, streetName, streetNumber, apartmentNumber);
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
}
