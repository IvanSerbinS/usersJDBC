package com.it_academy.query;

import com.it_academy.model.Transaction;

import java.sql.*;

import static java.lang.String.format;

public class TransactionQueryExecutor {

    public static boolean transact(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement statement;
        if (!transaction.getIsTransactionOut()) {
            //Top up transaction
            statement = connection.prepareStatement
                    (format("INSERT INTO Transactions (accountid, amount) VALUES (%d, '%s');",
                            transaction.getAccountid(),
                            transaction.getAmount()));
        } else {
            //Withdraw transaction
            statement = connection.prepareStatement
                    (format("INSERT INTO Transactions (accountid, amount) VALUES (%d, '%s');",
                            transaction.getAccountid(),
                            transaction.getAmount()*(-1)));
        }
        boolean transactionAdded;
        if (statement.executeUpdate() > 0) {
            transactionAdded = true;
            System.out.println("New transaction created.");
        } else {
            transactionAdded = false;
        }
        statement.close();
        return transactionAdded;
    }

    public static double sumOfAllAccountTransactions(int accountid,Connection connection) throws SQLException{
        double sum;
        PreparedStatement statement = connection.prepareStatement(format(
                "SELECT sum(amount) AS sum FROM Transactions WHERE accountid=%d",accountid));
        ResultSet resultSet = statement.executeQuery();
        sum=resultSet.getDouble("sum");
        resultSet.close();
        statement.close();
        return sum;
    }


}
