package main.dao;

import main.db.BdConnection;
import main.model.Client;
import main.model.Owner;
import main.model.Role;
import main.model.User;
import main.service.AuditService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class UserDAO implements GenericDAO<User>{
    private final AuditService auditService = new AuditService();
    @Override
    public void add(User user) {
        String sql = "INSERT INTO Users (RoleId, Name, Email, Address, Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, getRoleId(user.getRole()));
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPassword());
            pstmt.executeUpdate();
            auditService.logTransaction("ADD_USER", "Username=" + user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id){
        String sql = "SELECT * FROM Users WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                int roleId = rs.getInt("RoleId");
                User user = createUserInstanceByRole(roleId);
                if (user != null){
                    populateUserFromResultSet(rs, user, roleId);
                }
                return user;
            }
            auditService.logTransaction("GET_USER", "UserId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET RoleId = ?, Name = ?, Email = ?, Address = ?, Password = ? WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, getRoleId(user.getRole()));
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getPassword());
            pstmt.setInt(6, user.getUserId());
            pstmt.executeUpdate();
            auditService.logTransaction("UPDATE_USER", "UserId=" + user.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Users WHERE Id = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            auditService.logTransaction("DELETE_USER", "UserId=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int roleId = rs.getInt("RoleId");
                User user = createUserInstanceByRole(roleId);
                if (user != null){
                    populateUserFromResultSet(rs, user, roleId);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private int getRoleId(Role role) {
        if(role == Role.CLIENT) {
            return 1;
        } else if(role == Role.OWNER) {
            return 2;
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    private Role getRoleById(int roleId) {
        if(roleId == 1) {
            return Role.CLIENT;
        } else if(roleId == 2) {
            return Role.OWNER;
        } else {
            throw new IllegalArgumentException("Invalid role id: " + roleId);
        }
    }

    private User createUserInstanceByRole(int roleId) {
        if(roleId == 1){
            return new Client();
        } else if(roleId == 2){
            return new Owner();
        } else {
            return null; // Invalid role
        }
    }

    private void populateUserFromResultSet(ResultSet rs, User user, int roleId) throws SQLException {
        user.setUserId(rs.getInt("Id"));
        user.setName(rs.getString("Name"));
        user.setEmail(rs.getString("Email"));
        user.setAddress(rs.getString("Address"));
        user.setPassword(rs.getString("Password"));
        user.setRole(getRoleById(roleId));
    }

    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if user exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        auditService.logTransaction("AUTHENTICATE_USER", "Email=" + email);
        return false;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int roleId = rs.getInt("RoleId");
                User user = createUserInstanceByRole(roleId);
                if (user != null) {
                    populateUserFromResultSet(rs, user, roleId);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
