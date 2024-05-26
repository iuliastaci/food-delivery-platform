package main.service;

import main.dao.UserDAO;
import main.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    public boolean authenticateUser(String email, String password) {
        return userDAO.authenticateUser(email, password);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
