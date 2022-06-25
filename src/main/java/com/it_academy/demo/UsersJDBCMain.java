package com.it_academy.demo;

import com.it_academy.model.Account;
import com.it_academy.model.Transaction;
import com.it_academy.model.User;

import java.sql.*;
import java.util.Scanner;

import static com.it_academy.query.AccountQueryExecutor.*;
import static com.it_academy.query.TransactionQueryExecutor.transact;
import static com.it_academy.query.UserQueryExecutor.addNewUser;
import static com.it_academy.query.UserQueryExecutor.findUser;
import static com.it_academy.service.AccountInput.enterAccount;
import static com.it_academy.service.TransactionInput.enterTransaction;
import static com.it_academy.service.UserInput.enterUser;

public class UsersJDBCMain {
    private static final String JDBC_DRIVER_PATH = "org.sqlite.JDBC";
    private static final String DATABASE_URL =
            "jdbc:sqlite:E:\\Serbin_Ivan\\IT_academy\\Automation\\projects\\usersJDBC\\src\\main\\resources\\hw3db.db";

    public static void main(String[] args) throws SQLException {
        if (isJDBCDriverExists()) {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            String actionCode;
            do {
                printMenu();
                actionCode = new Scanner(System.in).nextLine();
                switch (actionCode) {
                    case "1" -> {
                        System.out.println("Adding new user: ");
                        User user = enterUser();
                        addNewUser(user, connection);
                    }
                    case "2" -> {
                        System.out.println("Adding new account to the Account table: ");
                        Account account = enterAccount();
                        if (findUser(account, connection)) {
                            if (!findAccount(account, connection)) {
                                addingNewAccount(account, connection);
                            } else {
                                System.out.println("User can't have more then 1 account for the currency.");
                                System.out.println("Use your existing account.");
                            }
                        } else {
                            System.out.println("There is no such userid in DB");
                        }
                    }
                    case "3" -> {
                        System.out.println("Top up you account: ");
                        Transaction transaction = enterTransaction(false);
                        if (findAccount(transaction, connection)) {
                            if (precheckBalanceReq(transaction, connection)) {
                                transact(transaction, connection);
                                balanceRecount(transaction.getAccountid(), connection);
                            } else {
                                System.out.println("Amount is too big. Balance could not be more then 2000mln.");
                            }
                        } else {
                            System.out.println("No such account founded.");
                        }
                    }
                    case "4" -> {
                        System.out.println("Withdraw account: ");
                        Transaction transaction = enterTransaction(true);
                        if (findAccount(transaction, connection)) {
                            if (precheckBalanceReq(transaction, connection)) {
                                transact(transaction, connection);
                                balanceRecount(transaction.getAccountid(), connection);
                            } else {
                                System.out.println("Amount is too high. Balance could not be lower then 0.");
                            }
                        } else {
                            System.out.println("No such account founded.");
                        }
                    }
                    case "5" -> System.out.println("Thanks for using the program.");
                    default -> System.out.println("Unknown option. Please try again.");
                }
            }
            while (!"5".equals(actionCode));
            System.exit(0);
        }

    }

    //Driver JDBC registration method
    private static boolean isJDBCDriverExists() {
        try {
            Class.forName(JDBC_DRIVER_PATH);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException() + " JDBC driver not found");
            return false;
        }
    }

    private static void printMenu() {
        System.out.println("\nPlease enter an action");
        System.out.println("1 - Register new user");
        System.out.println("2 - Add new account");
        System.out.println("3 - Top up account");
        System.out.println("4 - Withdraw funds from account");
        System.out.println("5 - Exit\n");
    }
}
