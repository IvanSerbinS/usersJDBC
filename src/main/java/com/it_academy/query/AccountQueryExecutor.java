package com.it_academy.query;

import com.it_academy.model.Account;
import com.it_academy.model.Transaction;

import java.sql.*;

import static com.it_academy.query.TransactionQueryExecutor.sumOfAllAccountTransactions;
import static java.lang.String.format;

public class AccountQueryExecutor {
    public static boolean addingNewAccount(Account account, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
                (format("INSERT INTO Accounts(userid, currency) VALUES (%d, '%s');",
                        account.getUserid(), account.getCurrency()));
        boolean accountAdded;
        if (statement.executeUpdate() > 0) {
            accountAdded = true;
            System.out.println("New account created.");
        } else {
            accountAdded = false;
        }
        return accountAdded;
    }

    //Проверка на наличие в БД записи с указанными userid и currency
    public static boolean findAccount(Account account, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                format("SELECT accountid FROM Accounts WHERE userid LIKE('%d') AND currency LIKE('%s');",
                        account.getUserid(), account.getCurrency()));
        boolean isAccountFound = resultSet.next();
        resultSet.close();
        statement.close();
        return isAccountFound;
    }

    public static boolean findAccount(Transaction transaction, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                format("SELECT accountid FROM Accounts WHERE accountid LIKE(%d);", transaction.getAccountid()));
        boolean isAccountFound = resultSet.next();
        resultSet.close();
        statement.close();
        return isAccountFound;
    }

    public static boolean precheckBalanceReq(Transaction transaction, Connection connection) throws SQLException {
        double preBalance;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                format("SELECT balance FROM Accounts WHERE accountid LIKE(%d);", transaction.getAccountid()));
        if(!transaction.getIsTransactionOut()) {
            preBalance = resultSet.getDouble("balance") + transaction.getAmount();
        }else{
            preBalance = resultSet.getDouble("balance") + transaction.getAmount()*(-1);
        }
        resultSet.close();
        statement.close();
        return !(preBalance < 0) && !(preBalance > 2000000000);
    }

    public static void balanceRecount(int accountid, Connection connection) throws SQLException{
        double balance=sumOfAllAccountTransactions(accountid, connection);
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(format("UPDATE Accounts SET balance = %s WHERE accountid = %d;",
                    balance, accountid));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        statement.close();
    }


    public static void updatingAccount(Account account, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(format("UPDATE Account SET balance = %s WHERE accountid = %d;",
                    account.getBalance(), account.getAccountid()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        statement.close();
    }


}
