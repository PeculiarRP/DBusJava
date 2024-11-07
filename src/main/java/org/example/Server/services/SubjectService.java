package org.example.Server.services;

import org.example.Server.dao.SubjectDAO;
import org.example.Server.models.Subject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

public class SubjectService {
    private final SubjectDAO subjectDAO = new SubjectDAO();

    public String[] getAllSubject(Boolean isAsc){
        return subjectDAO.getAllSubject(isAsc);
    }

    public String[] getSubjectByParam(String param, Boolean isAsc){
        return subjectDAO.getSubjectByParam(param, isAsc);
    }

    public String addSubject(String subjectName) {
        Subject sub = getSubjectByName(subjectName);
        if (sub == null){
            sub = new Subject(UUID.randomUUID(), subjectName);
            subjectDAO.addSubject(sub);
            return "Successful";
        }
        return "Such an item already exists!";

    }

    public Subject getSubjectByName(String subjectName){
        return subjectDAO.getSubjectByName(subjectName);
    }

    public void deleteSubjectById(String id){
        subjectDAO.deleteSubjectById(UUID.fromString(id));
    }

    public String updateSubjectById(String id, String name){
        Subject subject = getSubjectByName(name);
        if (subject == null) {
            subjectDAO.updateSubjectById(UUID.fromString(id), name);
            return "Successful";
        }
        return "Such an item already exists!";
    }
}
