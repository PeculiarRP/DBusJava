package org.example.Server;

import org.freedesktop.dbus.interfaces.DBusInterface;


public interface JournalServer extends DBusInterface {

    String AuthUser(String login, String password);
    String AddUser(String login, String password, String access);

    String[] GetStudents();
    String[] GetStudents(Boolean isAsc);
    String[] GetStudentBySurname(String param, Boolean isAsc);
    String GetStudentById(String id);
    String UpdateStudentById(String id, String name, String surname, String classStudent);
    void  DeleteStudent(String id);
    String AddStudent(String name, String surname, String classStudent);

    String DeleteAllGradeByStudentId(String id);
    String UpdateGradeById(String data);


    String[] GetAllSubject();
    String[] GetAllSubject(Boolean isAsc);
    String[] GetSubjectByParam(String param, Boolean isAsc);
    String AddSubject(String subjectName);
    void DeleteSubjectById(String id);
    String UpdateSubjectById(String id, String subjectName);


    void terminateServer();

}
