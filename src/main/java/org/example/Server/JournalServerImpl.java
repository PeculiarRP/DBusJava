package org.example.Server;

import org.example.Server.dao.SubjectDAO;
import org.example.Server.services.StudentService;
import org.example.Server.services.SubjectService;
import org.example.Server.services.UserService;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder;
import org.freedesktop.dbus.exceptions.DBusException;

import org.example.Server.models.User;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class JournalServerImpl implements JournalServer{

    private final DBusConnection dBusConnection;
    private final CountDownLatch waitClose;

    private final UserService userService = new UserService();
    private final StudentService studentService = new StudentService();
    private final SubjectService subjectService = new SubjectService();


    public JournalServerImpl() throws DBusException, InterruptedException {
        waitClose = new CountDownLatch(1);
        dBusConnection = DBusConnectionBuilder.forSessionBus().build();
        dBusConnection.requestBusName("test.dbusjava.server");
        dBusConnection.exportObject("/", this);
        waitClose.await();
        System.out.println("Stop");
    }
    @Override
    public String AuthUser(String login, String password) {
        return userService.authUser(login, password);
    }

    @Override
    public String[] GetStudents() {
        return studentService.getAllStudent();
    }

    @Override
    public String GetStudentById(String id) {
        return null;
    }

    @Override
    public String UpdateStudentById(String id) {
        return null;
    }

    @Override
    public String AddUser(String login, String password, String access) {return userService.regUser(login, password, access);}
    @Override
    public void DeleteStudent(String id){
        studentService.deleteStudentById(UUID.fromString(id));
    }

    @Override
    public void AddStudent(String name, String surname, String classStudent){
        studentService.addStudent(name, surname, classStudent);
    }

    @Override
    public String UpdateGradeById(String id) {
        return null;
    }

    @Override
    public String[] GetAllSubject() {
        return subjectService.getAllSubject();
    }

    @Override
    public String DeleteSubjectById(String id) {
        return null;
    }

    @Override
    public String UpdateSubjectById(String id) {
        return null;
    }

    @Override
    public void terminateServer() {
        waitClose.countDown();
    }

    @Override
    public String getObjectPath() {
        return "/";
    }

    public static void main(String[] args) throws DBusException, InterruptedException {
        new JournalServerImpl();
    }
}
