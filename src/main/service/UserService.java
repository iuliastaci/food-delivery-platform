package main.service;

import main.dao.UserDAO;
import main.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        try {
            userDAO.add(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String email, String password) {
        return userDAO.authenticateUser(email, password);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
