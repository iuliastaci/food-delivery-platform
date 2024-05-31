package main.dao;

import main.db.BdConnection;
import main.model.Venue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO implements GenericDAO<Venue>{
    @Override
    public void add(Venue venue) {
        String sql = "INSERT INTO Venues (Name, Address, Phone_number, UserId) VALUES (?, ?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getAddress());
            pstmt.setString(3, venue.getPhoneNumber());
            pstmt.setInt(4, venue.getOwnerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venue read(int id){
        String sql = "SELECT * FROM Venues WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueId(rs.getInt("Id"));
                venue.setName(rs.getString("Name"));
                venue.setAddress(rs.getString("Address"));
                venue.setPhoneNumber(rs.getString("Phone_number"));
                venue.setOwnerId(rs.getInt("UserId"));
                return venue;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Venue venue) {
        String sql = "UPDATE Venues SET Name = ?, Address = ?, Phone_number = ?, UserId = ? WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getAddress());
            pstmt.setString(3, venue.getPhoneNumber());
            pstmt.setInt(4, venue.getOwnerId());
            pstmt.setInt(5, venue.getVenueId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Venues WHERE Id = ?";
        try(Connection conn = BdConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Venue> getAll() {
        String sql = "SELECT * FROM Venues";
        List<Venue> venues = new ArrayList<>();
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueId(rs.getInt("Id"));
                venue.setName(rs.getString("Name"));
                venue.setAddress(rs.getString("Address"));
                venue.setPhoneNumber(rs.getString("Phone_number"));
                venue.setOwnerId(rs.getInt("UserId"));
                venues.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public boolean venueExists(String name) {
        String sql = "SELECT * FROM Venues WHERE name = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // returns true if venue exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Venue getVenueByName(String name) {
        String sql = "SELECT * FROM Venues WHERE name = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueId(rs.getInt("Id"));
                venue.setName(rs.getString("Name"));
                venue.setAddress(rs.getString("Address"));
                venue.setPhoneNumber(rs.getString("Phone_number"));
                venue.setOwnerId(rs.getInt("UserId"));
                return venue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
