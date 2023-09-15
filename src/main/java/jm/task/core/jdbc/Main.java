package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();
//        UserDao userDao = new UserDaoJDBCImpl();

//        userDao.createUsersTable();

//        userDao.saveUser("daas", "bibba", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);

//        userDao.removeUserById(4);
//        userDao.getAllUsers();
        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
