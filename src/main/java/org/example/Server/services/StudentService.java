package org.example.Server.services;

import org.example.Server.dao.StudentDAO;
import org.example.Server.models.Student;

import java.util.UUID;

public class StudentService {
    private final StudentDAO studentDAO = new StudentDAO();

    public String[] getAllStudent(){
        return studentDAO.getAllStudents();
    }

    public void deleteStudentById(UUID id){
        studentDAO.deleteStudentById(id);
    }

    public void addStudent(String name, String surname, String classStudent){
//        Student student = new Student(UUID.randomUUID(),
//                name,
//                surname,
//                classStudent);
//        studentDAO.addStudent(student);
    }
}
