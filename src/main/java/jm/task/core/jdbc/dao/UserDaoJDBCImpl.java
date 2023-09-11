package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE `users`.`user` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(45) NOT NULL," +
                    "  `lastName` VARCHAR(45) NOT NULL," +
                    "  `age` INT(3) NOT NULL," +
                    "  PRIMARY KEY (`id`))" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (Exception ignored) {
        }
        }


    public void dropUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table if exists user;");
            System.out.println("Таблица удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert  into  user  (name, lastName, age)  values  (?,?,?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User c именем - " + name + " добавлен в базу данных.");
        }
    }

    public void removeUserById(long id) throws SQLException {

        try(PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ? ")) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
            try(Statement statement = connection.createStatement()) {
                int resultSet = statement.executeUpdate("truncate table user");
            }
    }
}