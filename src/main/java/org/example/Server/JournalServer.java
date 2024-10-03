package org.example.Server;

import org.example.Server.models.User;
import org.freedesktop.dbus.interfaces.DBusInterface;


public interface JournalServer extends DBusInterface {

    String AuthUser(String login, String password);
    String[] GetStudents();
    void  DeleteStudent(String id);

    void AddStudent(String name, String surname, String classStudent);
    void terminateServer();

}
