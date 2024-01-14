package com.shop;

import com.shop.customer.Customer;
import com.shop.customer.role.Role;
import com.shop.authentication.login.Login;
import com.shop.authentication.login.registration.Registration;
import com.shop.util.Util;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Shop shop;
    private Registration registration;
    private Login login;
    private Customer currentCustomer;
    private int menuOption;

    public Menu(Shop shop) {
        this.shop = shop;
        this.scanner = Util.SCANNER;
        this.registration = new Registration(this.shop, this.scanner);
        this.login = new Login(this.shop);
        this.menuOption = 0;
    }

    public void startMenu() {
        defaultMenuOption();
    }

    private void defaultMenuOption() {
        while (getMenuOption() != 4) {
            defaultMenuDesignPattern();
            setMenuOption(getScanner().nextInt());
            switch (getMenuOption()) {
                case 1:
                    Customer currentCustomer = getLogin().defaultLogin();
                    if (currentCustomer != null) {
                        setCurrentCustomer(currentCustomer);
                        if(currentCustomer.getUser().getRole().equals(Role.ADMIN)) {
                            adminLoginMenuOption();
                        } else if (currentCustomer.getUser().getRole().equals(Role.USER)) {
                            afterLoginMenuOption();
                        }
                    }
                    else {
                        System.out.println("Unsuccessful login attempt!\nYou will be redirected to the menu options!");
                        defaultMenuOption();
                    }
                    break;
                case 2:
                    this.getRegistration().defaultRegistration();
                    break;
                case 3:
                    this.getShop().getInventory().getListOfProducts();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please try it again!");
                    break;
            }
        }
    }

    private void afterLoginMenuOption() {
        String productName;
        while (getMenuOption() != 5) {
            afterLoginMenuDesignPattern();
            setMenuOption(getScanner().nextInt());
            switch (getMenuOption()) {
                case 1:
                    this.getShop().getInventory().getListOfProducts();
                    break;
                case 2:
                    productName = askForProductName();
                    if (productName != null) {
                        this.getCurrentCustomer().getCart().addProductToCart(productName, getShop().getInventory());
                    } else {
                        afterLoginMenuOption();
                    }
                    break;
                case 3:
                    productName = askForProductName();
                    if (productName != null) {
                        this.getCurrentCustomer().getCart().removeProductFromCart(productName, getShop().getInventory());
                    } else {
                        afterLoginMenuOption();
                    }
                    break;
                case 4:
                    System.out.println(getCurrentCustomer().getCart().getInformationOfTheCart());
                    break;
                case 5:
                    System.out.println(getCurrentCustomer().toString());
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please try it again!");
                    break;
            }
        }
    }

    private void adminLoginMenuOption() {
        String productName;
        while (getMenuOption() != 5) {
            adminMenuDesignPattern();
            setMenuOption(getScanner().nextInt());
            switch (getMenuOption()) {
                case 1:
                    getShop().getInventory().getListOfProducts();
                    break;
                case 2:
                    productName = askForProductName();
                    if (productName != null) {
                        getCurrentCustomer().getCart().addProductToCart(productName, getShop().getInventory());
                    } else {
                        afterLoginMenuOption();
                    }
                    break;
                case 3:
                    productName = askForProductName();
                    if (productName != null) {
                        getCurrentCustomer().getCart().removeProductFromCart(productName, getShop().getInventory());
                    } else {
                        afterLoginMenuOption();
                    }
                    break;
                case 4:
                    System.out.println(getCurrentCustomer().getCart().getInformationOfTheCart());
                    break;
                case 5:
                    System.out.println(getCurrentCustomer().toString());
                    break;
                case 6:
                    productName = askForProductName();
                    getShop().getInventory().updateProduct(getCurrentCustomer(), productName);
                    break;
                case 7:
                    productName = askForProductName();
                    int amountOfTheProductAvailableAmount = getShop().getInventory().getAmountOfProduct(productName);
                    int removeProductAmount = askForRemoveProductAmount(productName, amountOfTheProductAvailableAmount);
                    getShop().getInventory().removeProduct(productName, removeProductAmount, amountOfTheProductAvailableAmount);
                    break;
                case 8:
                    productName = askForProductName();
                    getShop().getInventory().deleteProductFromInventory(getCurrentCustomer(), productName);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input, please try it again!");
                    break;
            }
        }
    }

    private int askForRemoveProductAmount(String productName, int amountOfTheProductAvailableAmount) {
        System.out.println("If you would like to remove all the products, please" +
                "use the Delete Product Menu Option!\n" +
                "Please provide how many products would you like to remove of " + productName + ".\n" +
                "Available amount of the product: " + amountOfTheProductAvailableAmount + ".\n");
        int providedAmountOfProductToRemove = getScanner().nextInt();
        if (providedAmountOfProductToRemove < amountOfTheProductAvailableAmount) {
            return providedAmountOfProductToRemove;
        }
        return 0;
    }

    private String askForProductName() {
        System.out.println("Please provide a product name: ");
        return getScanner().next();
    }

    private void adminMenuDesignPattern() {
        System.out.println("""
                Welcome to the Online-Store!
                1) Shop items
                2) Add product to Cart
                3) Remove product from Cart
                4) View Cart
                5) View Profile
                6) Update Product
                7) Remove Product
                8) Delete Product
                9) Exit
                Please select from the menu options:""");
    }

    private void defaultMenuDesignPattern() {
        System.out.println("""
                Welcome to the Online-Store!
                1) Login
                2) Registration
                3) Shop items
                4) Exit
                Please select from the menu options:""");
    }

    private void afterLoginMenuDesignPattern() {
        System.out.println("""
                Welcome to the Online-Store!
                1) Shop items
                2) Add product to Cart
                3) Remove product from Cart
                4) View Cart
                5) View Profile
                6) Exit
                Please select from the menu options:""");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public int getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(int menuOption) {
        this.menuOption = menuOption;
    }
}
