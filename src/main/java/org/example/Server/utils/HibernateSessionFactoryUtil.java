package org.example.Server.utils;

import org.example.Server.models.Journal;
import org.example.Server.models.Student;
import org.example.Server.models.Subject;
import org.example.Server.models.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Subject.class);
                configuration.addAnnotatedClass(Journal.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
