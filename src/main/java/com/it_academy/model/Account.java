package com.it_academy.model;

import java.util.InputMismatchException;

public class Account {
    private int accountid;
    private int userid;
    private double balance;
    private String currency;

    public Account() {
    }

    public int getAccountid() {
        return accountid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(String userid) throws NumberFormatException {
        int id;
        if (userid.length() == 0) {
            throw new InputMismatchException("Userid required.");
        } else if (userid.length() > 10) {
            throw new InputMismatchException("Userid mustn't be longer than 10 characters.");
        } else if (userid.contains("-") || userid.contains("+")) {
            throw new InputMismatchException("Userid must contain only numbers");
        }
        try {
            id = Integer.parseInt(userid);
            this.userid = id;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + " Invalid userid.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws Exception {
        if (balance < 0) {
            throw new Exception("The balance couldn't be lower than 0.");
        } else if (balance > 2000000000) {
            throw new Exception("The balance couldn't be more than 2000000000.");
        }
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
