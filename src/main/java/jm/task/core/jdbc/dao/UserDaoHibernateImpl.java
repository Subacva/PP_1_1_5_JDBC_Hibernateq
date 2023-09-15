package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS user " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query =  session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();
        }catch (Exception e ){
            if (transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> user = session.createQuery("from User").getResultList();
        for(User e: user)
            System.out.println(e);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "TRUNCATE TABLE user";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        session.getTransaction().commit();
    }
}
