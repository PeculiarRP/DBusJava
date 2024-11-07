package org.example.Server.dao;

import jakarta.persistence.NoResultException;
import org.example.Server.models.Subject;
import org.example.Server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class SubjectDAO {
    public String[] getAllSubject(Boolean isAsc){
        List<String> sb = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Subject ")
                .list()
                .stream()
                .sorted(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                .map(e -> e.toString()).toList();
        return sb.toArray(new String[sb.size()]);
    }

    public String[] getSubjectByParam(String param, Boolean isAsc){
        Query query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Subject where objectName like :objectName");
        query.setParameter("objectName", "%" + param + "%");
        try {
            List<Subject> sb = query.getResultList().stream()
                    .sorted(isAsc ? Comparator.naturalOrder() : Comparator.reverseOrder())
                    .map(e -> e.toString()).toList();
            return sb.toArray(new String[sb.size()]);
        }
        catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    public Subject getSubjectById(UUID id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .find(Subject.class, id);
    }

    public void addSubject(Subject subject){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subject);
        transaction.commit();
        session.close();
    }

    public Subject getSubjectByName(String subjectName){
        Query query = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Subject where objectName = :name");
        query.setParameter("name", subjectName);
        try{
           return (Subject) query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public void deleteSubjectById(UUID id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Subject subject = session.find(Subject.class, id);
        session.delete(subject);
        transaction.commit();
        session.close();
    }

    public void updateSubjectById(UUID id, String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Subject subjectCur = session.find(Subject.class, id);
        subjectCur.setObjectName(name);
        session.save(subjectCur);
        transaction.commit();
        session.close();
    }
}
