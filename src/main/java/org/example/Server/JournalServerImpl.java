package org.example.Server;

import org.example.Server.dao.SubjectDAO;
import org.example.Server.models.Journal;
import org.example.Server.services.JournalService;
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
    private final JournalService journalService = new JournalService();


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
        return studentService.getAllStudent(true);
    }

    @Override
    public String[] GetStudents(Boolean isAsc) {
        return studentService.getAllStudent(isAsc);
    }
    @Override
    public String[] GetStudentBySurname(String param, Boolean isAsc){
        return studentService.getStudentsByParam(param, isAsc);
    }

    @Override
    public String GetStudentById(String id) {
        return studentService.getStudentById(id);
    }

    @Override
    public String UpdateStudentById(String id, String name, String surname, String classStudent) {
        return studentService.updateStudent(id, name, surname, classStudent);
    }

    @Override
    public String AddUser(String login, String password, String access) {return userService.regUser(login, password, access);}
    @Override
    public void DeleteStudent(String id){
        studentService.deleteStudentById(UUID.fromString(id));
    }

    @Override
    public String AddStudent(String name, String surname, String classStudent){
        return studentService.addStudent(name, surname, classStudent);
    }

    @Override
    public String UpdateGradeById(String data) {
        return journalService.UpdateGradeByStudent(data);
    }

    @Override
    public String DeleteAllGradeByStudentId(String id){ return journalService.DeleteAllGradeByStudentId(id); }

    @Override
    public String[] GetAllSubject() {
        return subjectService.getAllSubject(true);
    }

    @Override
    public String[] GetAllSubject(Boolean isAsc) {
        return subjectService.getAllSubject(isAsc);
    }

    @Override
    public String[] GetSubjectByParam(String param, Boolean isAsc){
        return subjectService.getSubjectByParam(param, isAsc);
    }

    @Override
    public String AddSubject(String subjectName) {
        return subjectService.addSubject(subjectName);
    }

    @Override
    public void DeleteSubjectById(String id) {
        subjectService.deleteSubjectById(id);
    }

    @Override
    public String UpdateSubjectById(String id, String subjectName) {
        return subjectService.updateSubjectById(id, subjectName);
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
