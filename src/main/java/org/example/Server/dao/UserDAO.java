package org.example.Server.dao;

import jakarta.persistence.NoResultException;
import org.example.Server.models.User;
import org.example.Server.utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

public class UserDAO {
    public User getUserByLogin(String login){
        Query query = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("from User where login = :login");
        query.setParameter("login", login);
        try {
            return (User) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
