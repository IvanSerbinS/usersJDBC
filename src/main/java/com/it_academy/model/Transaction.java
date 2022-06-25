package com.it_academy.model;

import java.util.InputMismatchException;

public class Transaction {
    private int transactionid;
    private int accountid;
    private double amount;
    private boolean isTransactionOut;

    public boolean getIsTransactionOut() {
        return isTransactionOut;
    }

    public void setIsTransactionOut(boolean isTransactionOut) {
        this.isTransactionOut = isTransactionOut;
    }



    public int getTransactionid() {
        return transactionid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        int id;
        if (accountid.length() == 0) {
            throw new InputMismatchException("Accountid required.");
        } else if (accountid.length() > 10) {
            throw new InputMismatchException("Accountid mustn't be longer than 10 characters.");
        } else if(accountid.contains("-")||accountid.contains("+")){
            throw new InputMismatchException("Accountid must contain only numbers");
        }
        try {
            id = Integer.parseInt(accountid);
            this.accountid = id;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage()+" Invalid accountid.");
        }
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(String sAmount) {
        sAmount=sAmount.replace(',','.');
        double dAmount;
        if (sAmount.length() == 0) {
            throw new InputMismatchException("Amount required.");
        }
        try {
            dAmount = Double.parseDouble(sAmount);
            if (dAmount<=0){
                throw new InputMismatchException("Amount should be more than 0");
            }else if(dAmount>100000000){
                throw new InputMismatchException("Amount shouldn't be more than 100000000");
            }else if (sAmount.contains(".")&&((sAmount.length()-1)-(sAmount.indexOf("."))>3)){
                throw new InputMismatchException("The fractional part must be no more than 3 characters.");
            }
            this.amount = dAmount;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage()+" Invalid amount.");
        }
    }
}
