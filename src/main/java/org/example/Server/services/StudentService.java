package org.example.Server.services;

import org.example.Server.dao.StudentDAO;
import org.example.Server.models.Journal;
import org.example.Server.models.Student;

import java.lang.reflect.Field;
import java.util.*;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public String[] getAllStudent(Boolean isAsc){
        return studentDAO.getAllStudents(isAsc);
    }

    private boolean compere(int fValue, int pValue, String oper){
        return switch (oper) {
            case ">" -> fValue > pValue;
            case "<" -> fValue < pValue;
            case "=" -> fValue == pValue;
            case "!=" -> fValue != pValue;
            default -> throw new IllegalArgumentException("Invalid operation.");
        };
    }

    public String[] getStudentsByParam(String param, Boolean isAsc){
        var paramSplit = param.split(":");
        var query = paramSplit[0];
        var studentList = studentDAO.getStudentByParam(query, isAsc);
        List<Student> outList = new ArrayList<>();
        for (int i=1; i<paramSplit.length; i++){
            var strSplit = paramSplit[i].split(" ");
            String fieldName = strSplit[0];
            String oper = strSplit[1];
            String fieldValue = strSplit[2];

            for (var person : studentList){
                int fValue = person.getJournals().stream()
                        .filter(j -> j.getObject().getObjectName().equals(fieldName))
                        .map(Journal::getGrade)
                        .findFirst().orElse(0);
                int value = Integer.parseInt(fieldValue);
                if(compere(fValue, value, oper)){
                    outList.add(person);
                }
            }
        }
        if (paramSplit.length < 2) outList = studentList;
        return outList.stream()
                .map(Student::toString)
                .toList().toArray(new String[outList.size()]);
    }

    public String getStudentById(String id){
        return studentDAO.getStudentById(UUID.fromString(id));
    }

    public void deleteStudentById(UUID id){
        studentDAO.deleteStudentById(id);
    }

    public String addStudent(String name, String surname, String classStudent){
        Student student = studentDAO.getStudentByAllParam(name, surname, classStudent);
        if (student == null) {
            student = new Student(UUID.randomUUID(),
                    name,
                    surname,
                    classStudent,
                    null);
            studentDAO.addStudent(student);
            return "Successful";
        }
        return "Such a student already exists";
    }

    public String updateStudent(String id, String name, String surname, String classStudent){
        Student student = studentDAO.getStudentByAllParam(name, surname, classStudent);
        if (student == null){
            studentDAO.updateStudentById(UUID.fromString(id), name, surname, classStudent);
            return "Successful";
        }
        return "Such a student already exists";
    }
}
