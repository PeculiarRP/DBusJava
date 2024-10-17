package org.example.Server.dao;

import org.example.Server.utils.HibernateSessionFactoryUtil;

import java.util.List;

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
}
