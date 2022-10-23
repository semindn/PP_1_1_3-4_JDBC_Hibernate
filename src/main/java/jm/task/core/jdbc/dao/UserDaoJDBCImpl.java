package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable =
                    "create table if not exists " + util.getDbName() + "." + util.getDbTableName() + " (" +
                            "id bigint auto_increment, name text, last_name text, age integer, " +
                            "constraint table1_pk primary key (id)" +
                            ")";
            statement.execute(sqlQueryCreateTable);

        } catch (SQLException e) {
            System.out.println("Error creating the table");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable =
                    "drop table if exists " + util.getDbName() + "." + util.getDbTableName();
            statement.execute(sqlQueryCreateTable);

        } catch (SQLException e) {
            System.out.println("Error when deleting a table");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable = "INSERT INTO " + util.getDbName() + "." + util.getDbTableName()
                    + " (name, last_name, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";
            statement.execute(sqlQueryCreateTable);

        } catch (SQLException e) {
            System.out.println("Error when adding a record to the table");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable = "DELETE FROM " + util.getDbName() + "." + util.getDbTableName()
                    + " WHERE id = " + id;
            statement.execute(sqlQueryCreateTable);

        } catch (SQLException e) {
            System.out.println("Error when deleting a record from a table");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable = "SELECT * FROM " + util.getDbName() + "." + util.getDbTableName();
            ResultSet rs = statement.executeQuery(sqlQueryCreateTable);
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error when select from table");
            throw new RuntimeException(e);
        }

        return userList;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        try (
                Connection connection = util.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String sqlQueryCreateTable = "DELETE FROM " + util.getDbName() + "." + util.getDbTableName();
            statement.execute(sqlQueryCreateTable);

        } catch (SQLException e) {
            System.out.println("Error when deleting a record from a table");
            throw new RuntimeException(e);
        }
    }
}
