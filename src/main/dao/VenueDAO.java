package main.dao;

import main.db.BdConnection;
import main.model.Venue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {
    public void addVenue(Venue venue) {
        String sql = "INSERT INTO Venues (name, address, phone_number) VALUES (?, ?, ?)";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getAddress());
            pstmt.setString(3, venue.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Venue> listVenues() {
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM Venues";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueId(rs.getInt("venue_id"));
                venue.setName(rs.getString("name"));
                venue.setAddress(rs.getString("address"));
                venue.setPhoneNumber(rs.getString("phone_number"));
                venues.add(venue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }

    public Venue getVenueByName(String name) {
        String sql = "SELECT * FROM Venues WHERE name = ?";
        try (Connection conn = BdConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Venue venue = new Venue();
                venue.setVenueId(rs.getInt("venue_id"));
                venue.setName(rs.getString("name"));
                venue.setAddress(rs.getString("address"));
                venue.setPhoneNumber(rs.getString("phone_number"));
                return venue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
}
