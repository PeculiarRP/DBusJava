package org.example.Server.services;

import org.example.Server.dao.UserDAO;
import org.example.Server.models.User;

import java.util.UUID;


public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public String authUser(String login, String password){
        User user = userDAO.getUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) return user.getUserId().toString() + ":" + user.getAccess();
        }
        return "error";
    }

    public String regUser(String login, String password, String access){
        User user = userDAO.getUserByLogin(login);
        if (user != null){
            user = new User(UUID.randomUUID(), login, password, access);
            userDAO.addUser(user);
            return "Successful";
        }
        return "Such a user already exists";
    }
}
