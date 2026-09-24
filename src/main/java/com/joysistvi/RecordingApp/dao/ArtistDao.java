package com.joysistvi.RecordingApp.dao;

import com.joysistvi.RecordingApp.config.DatabaseConnection;


import java.sql.*;

// inheritance: is a relationship (tightly coupled)
//composition: has a relationship (loose coupling)

public class ArtistDao extends DatabaseConnection {

    // Composition
    private final DatabaseConnection db;


    // Constructor Injection
    public ArtistDao(DatabaseConnection db) {
        this.db = db;
    }

    // CRUD OPERATION

    public void readAllArtist() {
        String query = "SELECT * FROM artists";

        // create a statement
        // try with resources
        try (Connection conn = db.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {


            // HEADER
            System.out.printf("%-3s | %-20s%n", "ID", "NAME");
            System.out.println("-".repeat(23));
            // extract data
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                System.out.printf("%-3d | %-20s%n", id, name);
            }

        } catch(SQLException e) {
            System.err.println("Get All Artist: " + e.getMessage());
        }
    }

    public void createArtist(String name) {
        // validation
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Artist name is required");
            return;
        }
        // Anti-SQL injection
        // parameterized query
        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection conn = db.connect()) {
            PreparedStatement prep = conn.prepareStatement(query);

            // set wild card values
            prep.setString(1, name);

            int rows = prep.executeUpdate();

            // synchronization
            System.out.println(rows > 0 ? "Artist added successfully." : "Failed to add artist.");
            readAllArtist();


        } catch(SQLException e) {
            System.err.println("Create Artist: " + e.getMessage());
        }
    }

    public void updateArtist(String name, int id) {
        if (id <= 0) {
            System.out.println("Invalid Artist!");
            return;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Artist name is required");
            return;
        }
        readAllArtist();

        String query = "UPDATE songs SET name = ? WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)){


            prep.setString(1, name);
            prep.setInt(2, id);

            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Artist update successfully!" : "Failed to update!");
            readAllArtist();
        } catch (SQLException e) {
            System.err.println("Update Artist: " + e.getMessage());
        }
    }

    // hard delete / soft delete(Archive)
    public void archiveArtist(int id) {
        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)){


            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Artist archived successfully!" : "Failed to archive!");
            readAllArtist();
        } catch(SQLException e) {
            System.err.println("Archive Artist: " + e.getMessage());
        }
    }

    public void restoreArtist(int id) {
        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {


            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Artist restored successfully!" : "Failed to restore!");
            readAllArtist();
        } catch(SQLException e) {
            System.err.println("Restore Artist: " + e.getMessage());
        }
    }

    public void deleteArtist(int id) {
        readAllArtist();
        String query = "DELETE FROM artists WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {


            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            System.out.println(rows > 0 ? "Artist archived successfully!" : "Failed to archive!");
            readAllArtist();
        } catch(SQLException e) {
            System.err.println("Delete Artist: " + e.getMessage());
        }
    }

    public void readArtistById(int id) {
        String query = "SELECT * FROM artists WHERE id = ?";

        try (Connection conn = db.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();


            while(res.next()) {
                int userId = res.getInt("id");
                String name = res.getString("name");
                System.out.printf("%-3d | %-5s",userId,name);

            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void readAllArchivedArtist() {
        String query = "SELECT * FROM artists WHERE is_archived = 1";

        // create a statement
        // try with resources
        try (Connection conn = db.connect();
             Statement stmnt = conn.createStatement();
             ResultSet result = stmnt.executeQuery(query)) {


            // HEADER
            System.out.printf("%-3s | %-20s%n", "ID", "NAME");
            System.out.println("-".repeat(23));
            // extract data
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                System.out.printf("%-3d | %-20s%n", id, name);
            }

        } catch(SQLException e) {
            System.err.println("Get All Artist: " + e.getMessage());
        }
    }
}

/*
    SQL Categories

    DDL -> Data Definition Language;
    DML -> Data Manipulation Language; INSERT, UPDATE, DELETE, MERGE : prepareStatement() -> executeUpdate()
    DQL -> Data Query Language; SELECT : createStatement() -> executeQuery()
 */

//class Car {
//
//}
//
//class Vehicle {
//
//}
//
//class Engine {
//
//}


