package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String sqlQuery = "create table if not exists " + Util.dbName + "." + Util.dbTableName +
                    "(id bigint auto_increment, name text, last_name text, age integer, " +
                    "constraint table1_pk primary key (id))";
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error creating the table");
        }
    }

    public void dropUsersTable() {
        try {
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.connection.setAutoCommit(false);
            String sqlQuery = "INSERT INTO " + Util.dbName + "." + Util.dbTableName
                    + " (name, last_name, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            Util.connection.commit();
            Util.connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                Util.connection.rollback();
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when adding a record to the table");
        }
    }

    public void removeUserById(long id) {
        try {
            Util.connection.setAutoCommit(false);
            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableName + " WHERE id = ?";
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Util.connection.commit();
            Util.connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                Util.connection.rollback();
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                userList.add(user);
            }
        } catch (SQLException e) {
            try {
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when select from table");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Util.connection.setAutoCommit(false);
            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connection.prepareStatement(sqlQuery);
            preparedStatement.execute();
            Util.connection.commit();
            Util.connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                Util.connection.rollback();
                Util.connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
    }
}
