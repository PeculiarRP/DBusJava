package org.example.Server.dao;

import org.example.Server.models.Journal;
import org.example.Server.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class JournalDAO {
    public String[] getFullJournal(){
        var jornal = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Journal")
                .list()
                .stream()
                .map(e->e.toString())
                .toList();
        return (String[]) jornal.toArray(new String[jornal.size()]);
    }

    public List<Journal> getJournalsFromStudent(UUID stId){
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("from Journal where studentId = :studentId");
        query.setParameter("studentId", stId);
        return query.getResultList();
    }
    public void addJournal(List<Journal> journals){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for(var journal: journals){
            Journal tmp = session.find(Journal.class,journal.getId());
            if(tmp != null){
                tmp.setGrade(journal.getGrade());
            }
            else tmp = journal;
            session.save(tmp);
        }
        transaction.commit();
        session.close();
    }

    public void deleteAllJournalFromStudent(UUID id){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Journal where studentId = :studentId");
        query.setParameter("studentId", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
