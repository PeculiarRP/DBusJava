package org.example.Client;


import org.example.Server.JournalServer;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder;
import org.freedesktop.dbus.exceptions.DBusException;

import javax.swing.*;

/*
Переписать данный класс
Спрятать кнопки в своих окнах
*/

public class Client {
    private final JournalServer server;
    private final DBusConnection dBusConnection;
    private final AuthClient authWin;
    private final JournalClient mainWin;

    public Client() throws DBusException, InterruptedException {
        dBusConnection = DBusConnectionBuilder.forSessionBus().build();
        server = dBusConnection.getRemoteObject("test.dbusjava.server","/", JournalServer.class);
        authWin = new AuthClient();
        mainWin = new JournalClient(server);
        JButton btn = authWin.getAuthButton();
        btn.addActionListener(e -> authAction());
    }


    private void authAction(){
        String res = dataCheck(authWin.getLogin(), authWin.getPassword());
        if(!res.equals("error")){
            mainWin.setTestLabel(res);
            authWin.setVisible(false);
            authWin.dispose();
            mainWin.setVisible(true);
        }
        else authWin.WarningMessage();
    }
    private String dataCheck(String login, String pass){
        if(login != null && pass != null){
           return server.AuthUser(login, pass);
        }
        return "error";
    }

    public static void main(String[] args) throws DBusException, InterruptedException {
        new Client();
    }
}

