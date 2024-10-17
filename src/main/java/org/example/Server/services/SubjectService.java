package org.example.Server.services;

import org.example.Server.dao.SubjectDAO;

public class SubjectService {
    private final SubjectDAO subjectDAO = new SubjectDAO();

    public String[] getAllSubject(){
        return subjectDAO.getAllSubject();
    }

}
