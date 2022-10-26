package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String dbName = "for_java";
    public static final String dbTableName = "users";
    private static final String dbUrlConnection = "jdbc:mysql://localhost/" + dbName;
    private static final String dbUserName = "dmitry";
    private static final String dbUserPassword = "1234";

    public static Connection connectionJDBC = getConnectionJDBC();

    private static Connection getConnectionJDBC() {
        try {
            Connection connection = DriverManager.getConnection(dbUrlConnection, dbUserName, dbUserPassword);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error connect with database");
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory sessionFactory = new Configuration()
            .addProperties(getPropertiesSessionFactory())
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    private static Properties getPropertiesSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", dbUrlConnection);
        properties.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.connection.username", dbUserName);
        properties.setProperty("hibernate.connection.password", dbUserPassword);
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        return properties;
    }


}
