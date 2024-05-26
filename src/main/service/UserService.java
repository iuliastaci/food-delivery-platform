package main.service;

import main.dao.UserDAO;
import main.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    public boolean authenticateUser(String email) {
        return userDAO.authenticateUser(email);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
