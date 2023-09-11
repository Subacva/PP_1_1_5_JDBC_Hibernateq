package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {
    UserDao userdao = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        userdao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userdao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userdao.saveUser(name, lastName, age);
        System.out.println("User c именем - " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) throws SQLException {
        userdao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userdao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        userdao.cleanUsersTable();
    }


}
