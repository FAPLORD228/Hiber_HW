package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.beans.container.spi.FallbackContainedBean;

import java.sql.Connection;
import java.sql.Driver;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("").addAnnotatedClass(UserServiceImpl.class).buildSessionFactory();
       try {
           Session session = factory.getCurrentSession();
           User user = new User("Ivan", "Petrov", (byte) 33);
           session.beginTransaction();
           session.save(user);
           session.getTransaction().commit();
       } finally {
           factory.close();
       }
    }
    }