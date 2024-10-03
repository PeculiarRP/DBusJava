package org.example.Server.dao;

import org.example.Server.models.Student;
import org.example.Server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class StudentDAO {
    public String[] getAllStudents(){
        List<String> st = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Student ")
                .list()
                .stream()
                .map(e -> e.toString()).toList();
        return st.toArray(new String[st.size()]);
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
}
