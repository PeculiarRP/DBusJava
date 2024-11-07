package org.example.Server.dao;

import jakarta.persistence.NoResultException;
import org.example.Server.models.Student;
import org.example.Server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class StudentDAO {

    public List<Student> getStudentByParam(String param, Boolean isAsc){
        Query query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Student where surname LIKE :surname");
        query.setParameter("surname", "%" + param + "%");
        try {
            return query.getResultList().stream()
                    .sorted(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                    .toList();
        }
        catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    public String[] getAllStudents(Boolean isAsc){
        List<String> st = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Student ")
                .list()
                .stream()
                .sorted(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .map(e -> e.toString()).toList();
        return st.toArray(new String[st.size()]);
    }

    public String getStudentById(UUID id){
        String st = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .find(Student.class, id)
                .toString();
        return st;
    }

    public Student getStudentByAllParam(String name, String surname, String classStudent){
        Query query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Student where name = :name and surname = :surname and studentClass = :classStudent");
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        query.setParameter("classStudent", classStudent);
        try
        {
            return (Student) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    public void deleteStudentById(UUID id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.find(Student.class, id);
        session.delete(student);
        transaction.commit();
        session.close();
    }

    public void addStudent(Student student){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
        transaction.commit();
        session.close();
    }

    public void updateStudentById(UUID id, String name, String surname, String classStudent){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.find(Student.class, id);
        student.setName(name);
        student.setSurname(surname);
        student.setStudentClass(classStudent);
        session.save(student);
        transaction.commit();
        session.close();
    }
}
