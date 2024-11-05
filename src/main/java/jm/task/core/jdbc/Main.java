package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args)  {

        UserServiceImpl userService = new UserServiceImpl();

        userService.saveUser("Vlad", "Vladosovich", (byte) 20);
        userService.saveUser("Kolya", "Kolyasovich", (byte) 25);
        userService.saveUser("Ura", "Urevich", (byte) 31);
        userService.saveUser("Jora", "Jorovich", (byte) 38);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
