package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        List<User> addedUsers = new ArrayList<>();

        userDao.saveUser("Vlad", "Vladosovich", (byte) 20);
        addedUsers.add(new User("Vlad", "Vladosovich", (byte) 20));

        userDao.saveUser("Kolya", "Kolyasovich", (byte) 25);
        addedUsers.add(new User("Kolya", "Kolyasovich", (byte) 25));

        userDao.saveUser("Ura", "Urevich", (byte) 31);
        addedUsers.add(new User("Ura", "Urevich", (byte) 31));

        userDao.saveUser("Jora", "Jorovich", (byte) 38);
        addedUsers.add(new User("Jora", "Jorovich", (byte) 38));

        for (User user : addedUsers) {
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных");
        }

        userDao.getAllUsers();
        List<User> users2 = userDao.getAllUsers();
        for (User user : users2) {
            String firstName = user.getName();
            System.out.println(firstName);
        }

        userDao.cleanUsersTable();
        userDao.dropUsersTable();



    }
}
