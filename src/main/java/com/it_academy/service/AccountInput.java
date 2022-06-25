package com.it_academy.service;

import com.it_academy.model.Account;

import java.util.Scanner;

public class AccountInput {

    public static Account enterAccount() {
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        //Entering userid
        do {
            System.out.println("Enter userid (required): ");
            try {
                account.setUserid(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        //Entering currency
        do {
            System.out.println("Enter currency (required): ");
            try {
                account.setCurrency(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        System.out.println("Entered:");
        System.out.println("userid: \"" + account.getUserid() + "\"");
        System.out.println("currency: \"" + account.getCurrency() + "\"");
        return account;
    }
}
