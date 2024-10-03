package org.example.Server.services;

import org.example.Server.dao.UserDAO;
import org.example.Server.models.User;


public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public String authUser(String login, String password){
        User user = userDAO.getUserByLogin(login);
        if (user != null) {
            if (user.getPassword().equals(password)) return user.getUserId().toString();
        }
        return "error";
    }
}
