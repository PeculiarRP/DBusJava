package org.example;

import org.example.Server.dao.JournalDAO;
import org.example.Server.dao.StudentDAO;
import org.example.Server.dao.UserDAO;
import org.example.Server.models.Journal;
import org.freedesktop.dbus.*;
import org.freedesktop.dbus.annotations.DBusInterfaceName;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {

        var server = new StudentDAO();
        Arrays.stream(server.getAllStudents()).forEach(System.out::println);

//        var ser = new UserDAO();
//        ser.getUserByLogin("admin");

    }
}