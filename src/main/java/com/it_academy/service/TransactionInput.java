package com.it_academy.service;

import com.it_academy.model.Transaction;

import java.util.Scanner;

public class TransactionInput {
    public static Transaction enterTransaction(boolean isTransactionOut){
        Transaction transaction = new Transaction();
        Scanner scanner = new Scanner(System.in);
        transaction.setIsTransactionOut(isTransactionOut);

        boolean valid = false;

        //Entering accountid
        do {
            System.out.println("Enter accountid (required): ");
            try {
                transaction.setAccountid(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!valid);

        //Entering amount
        do{
            System.out.println("Enter an amount (required): ");
            try {
                transaction.setAmount(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);

        System.out.println("You entered accountid: \""+transaction.getAccountid()+"\"");
        System.out.println("and");
        System.out.println("Amount: \""+transaction.getAmount()+"\"");
        return transaction;
    }
}
