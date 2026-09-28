package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepoImpl implements  PlaylistRepo {

    private final DatabaseConnection db;

    public PlaylistRepoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {
        String query = "INSERT INTO playlist (name, user_id) VALUES (?, ?)";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());
            prep.setInt(2, playlist.getUser_id());

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Create Playlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Playlist> getAllPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE is_archived = 0";


        try(Connection conn = db.connect();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery(query)) {

            while (res.next()) {
                playlists.add(new Playlist(res.getInt("id"), res.getString("name"), res.getString("date_created"), res.getInt("user_id")));
            }

        } catch(SQLException e) {
            System.err.println("Get All Playlist: " + e.getMessage());
        }

        return playlists;
    }

    @Override
    public List<Playlist> searchPlaylist(String keyword) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE is_archived = 0 AND name LIKE ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");


            ResultSet res = prep.executeQuery();

            while(res.next()) {
                playlists.add(new Playlist(res.getInt("id"),res.getString("name"), res.getString("date_created"), res.getInt("user_id")));
            }

        } catch(SQLException e) {
            System.err.println("Search Playlist: " + e.getMessage());
        }
        return playlists;
    }

    @Override
    public List<Playlist> getPlaylistByUserId(User user) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE is_archived = 0 AND user_id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, user.getId());

            ResultSet res = prep.executeQuery();

            while(res.next()) {
                playlists.add(new Playlist(res.getInt("id"),
                        res.getString("name"),
                        res.getString("date_created"),
                        res.getInt("user_id")));
            }

        } catch(SQLException e) {
            System.err.println("Get Playlist By User ID: " + e.getMessage());
        }
        return playlists;
    }
    

    @Override
    public boolean updatePlaylist(Playlist playlist) {
        String query = "UPDATE playlist SET name = ? WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, playlist.getName());
            prep.setInt(2, playlist.getId());

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Update Playlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean archivePlaylist(int id) {
        String query = "UPDATE playlist SET is_archived = 1 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Archive Playlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Playlist> getAllArchivedPlaylist() {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE is_archived = 1";


        try(Connection conn = db.connect();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery(query)) {

            while (res.next()) {
                playlists.add(new Playlist(res.getInt("id"), res.getString("name"), res.getString("date_created"), res.getInt("user_id")));
            }

        } catch(SQLException e) {
            System.err.println("Get All Archived Playlist: " + e.getMessage());
        }

        return playlists;

    }

    @Override
    public boolean restoreArchivedPlaylist(int id) {
        String query = "UPDATE playlist SET is_archived = 0 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Restore Archived Playlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePlaylist(int id) {
        String query = "DELETE FROM playlist WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Delete Playlist: " + e.getMessage());
        }
        return false;
    }
}
