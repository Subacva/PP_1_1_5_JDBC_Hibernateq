package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    public static final String Username = "root";
    public static final String Password = "root";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, Username, Password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,DB_Driver);
                settings.put(Environment.URL,DB_URL);
                settings.put(Environment.USER, Username);
                settings.put(Environment.PASS,Password);
                settings.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL,"true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                settings.put(Environment.HBM2DDL_AUTO,"");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Да");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;

    }
}



