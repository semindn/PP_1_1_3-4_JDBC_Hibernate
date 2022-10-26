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
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery = "create table if not exists " + Util.dbName + "." + Util.dbTableName +
                    "(id bigint auto_increment, name text, last_name text, age integer, " +
                    "constraint table1_pk primary key (id))";
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error creating the table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    public void dropUsersTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery = "drop table if exists " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery = "INSERT INTO " + Util.dbName + "." + Util.dbTableName
                    + " (name, last_name, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when adding a record to the table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    public void removeUserById(long id) {
        try {
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableName + " WHERE id = ?";
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Util.connectionJDBC.setAutoCommit(true);
            String sqlQuery = "SELECT * FROM " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                userList.add(user);
            }
            Util.connectionJDBC.setAutoCommit(false);
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when select from table");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Util.connectionJDBC.setAutoCommit(false);
            String sqlQuery = "DELETE FROM " + Util.dbName + "." + Util.dbTableName;
            PreparedStatement preparedStatement = Util.connectionJDBC.prepareStatement(sqlQuery);
            preparedStatement.execute();
            Util.connectionJDBC.commit();
        } catch (SQLException e) {
            try {
                Util.connectionJDBC.rollback();
                Util.connectionJDBC.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException("Error when deleting a record from a table");
        }
        finally {
            try {
                Util.connectionJDBC.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException("Error setAutoCommit(true)");
            }
        }
    }
}
