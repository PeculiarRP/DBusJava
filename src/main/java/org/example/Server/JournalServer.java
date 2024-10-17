package org.example.Server;

import org.example.Server.models.User;
import org.freedesktop.dbus.interfaces.DBusInterface;


public interface JournalServer extends DBusInterface {

    String AuthUser(String login, String password);
    String[] GetStudents();

    String GetStudentById(String id);

    String UpdateStudentById(String id);

    String AddUser(String login, String password, String access);
    void  DeleteStudent(String id);

    void AddStudent(String name, String surname, String classStudent);

    String UpdateGradeById(String id);

    String[] GetAllSubject();

    String DeleteSubjectById(String id);

    String UpdateSubjectById(String id);

    void terminateServer();

}
