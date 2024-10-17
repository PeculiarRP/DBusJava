package org.example.Server.dao;

import org.example.Server.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SubjectDAO {
    public String[] getAllSubject(){
        List<String> sb = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Subject ")
                .list()
                .stream()
                .map(e -> e.toString()).toList();
        return sb.toArray(new String[sb.size()]);
    }
}
