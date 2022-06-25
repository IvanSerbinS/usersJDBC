package com.it_academy.query;

import com.it_academy.model.Account;
import com.it_academy.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.String.format;

public class UserQueryExecutor {

    public static void addNewUser(User user, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        try {
            statement.executeUpdate(format("INSERT INTO Users(name, address) VALUES ('%s', '%s');",
                    user.getName(), user.getAddress()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        statement.close();
    }

    //Проверка на наличие в БД записи с указанным userid
    public static boolean findUser(Account account, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(format("SELECT userid FROM Users WHERE userid LIKE(%d);",
                account.getUserid()));
        boolean isUseridFound=resultSet.next();
        resultSet.close();
        statement.close();
        return isUseridFound;
    }
}
