package com.it_academy.service;

import com.it_academy.model.User;

import java.util.Scanner;

public class UserInput {

    public static User enterUser() {
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;

        //Entering name
        do {
            System.out.println("Enter user's name (required): ");
            try {
                user.setName(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while(!valid);

        //Entering address
        do{
            System.out.println("Enter user's address (optional): ");
            try {
                user.setAddress(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);

        System.out.println("Entered:");
        System.out.println("name: \""+user.getName()+"\"");
        System.out.println("address: \""+user.getAddress()+"\"");
        return user;
    }
}
