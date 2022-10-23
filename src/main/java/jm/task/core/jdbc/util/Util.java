package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String dbName = "for_java";
    private final String dbTableName = "users";
    private final String dbUrlConnection = "jdbc:mysql://localhost/"+dbName;
    private final String dbUserName = "dmitry";
    private final String dbUserPassword = "1234";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrlConnection, dbUserName, dbUserPassword);
        } catch (SQLException e) {
            System.out.println("Error connect with databese");
            throw new RuntimeException(e);
        }
    }
    public String getDbName(){
        return dbName;
    }
    public String getDbTableName(){
        return dbTableName;
    }

}
