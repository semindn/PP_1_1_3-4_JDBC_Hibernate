package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        User user1 = new User("Dmitry1", "Semin1", (byte) 31);
        User user2 = new User("Dmitry2", "Semin2", (byte) 32);
        User user3 = new User("Dmitry3", "Semin3", (byte) 33);
        User user4 = new User("Dmitry4", "Semin4", (byte) 34);
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        for (User elem : userList) {
            service.saveUser(elem.getName(), elem.getLastName(), elem.getAge());
            System.out.println("User с именем - " + elem.getName() + " добавлен в базу данных");
        }

        List<User> select = service.getAllUsers();
        select.stream().forEach(System.out::println);

        service.cleanUsersTable();

        service.dropUsersTable();
    }
}
