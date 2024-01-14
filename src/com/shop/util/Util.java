package com.shop.util;

import com.shop.customer.Customer;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public final static Scanner SCANNER = new Scanner(System.in);

    public static boolean isEmailAddressValid(String emailAddress) {
        String emailPattern = "^(.+)@(\\S+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    public static boolean isEmailAddressExists(List<Customer> listOfCustomers, String emailAddress) {
        return listOfCustomers.stream()
                .anyMatch(customer -> customer.getUser().getEmailAddress().equals(emailAddress));
    }

}
