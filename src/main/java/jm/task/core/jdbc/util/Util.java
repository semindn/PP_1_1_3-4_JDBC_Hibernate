package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String dbName = "for_java";
    public static final String dbTableName = "users";
    private static final String dbUrlConnection = "jdbc:mysql://localhost/"+dbName;
    private static final String dbUserName = "dmitry";
    private static final String dbUserPassword = "1234";

    public static Connection connection = getConnection();

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrlConnection, dbUserName, dbUserPassword);
        } catch (SQLException e) {
            System.out.println("Error connect with database");
            throw new RuntimeException(e);
        }
    }
    /*public String getDbName(){
        return dbName;
    }
    public String getDbTableName(){
        return dbTableName;
    }*/

}
