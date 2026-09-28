package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongRepoImpl implements SongRepo {

    private final DatabaseConnection db;

    public SongRepoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public boolean createSong(Song song, Album album) {
        String query = "INSERT INTO songs (title, length, genre, album_id) VALUES (?, ?, ?, ?)";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getLength());
            prep.setString(3, song.getGenre());
            prep.setInt(4, album.getId());

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Create Song" + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Song> getAllSongs() {

        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM songs WHERE is_archived = 0";

        try(Connection conn = db.connect();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery(query)) {

            while(res.next()) {
                songs.add(new Song(res.getInt("id"), res.getString("title"), res.getString("length"), res.getString("genre")));
            }

        } catch(SQLException e) {
            System.err.println("Get All Songs: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public Song getSongById(int id) {
        Song song = new Song(id);
        String query = "SELECT * FROM songs WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            ResultSet res = prep.executeQuery();

            if (res.next()) {
                return new Song(res.getInt("id"), res.getString("title"), res.getString("length"), res.getString("genre"));
            }

        } catch(SQLException e) {
            System.err.println("Get Song By Id: " + e.getMessage());
        }
        return song;
    }

    @Override
    public List<Song> getSongByKeyword(String keyword) {

        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM songs WHERE name LIKE ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + "name" + "%");

            ResultSet res = prep.executeQuery();

            while(res.next()) {
                songs.add(new Song(res.getInt("id"), res.getString("title"), res.getString("length"), res.getString("genre")));
            }

        } catch(SQLException e) {
            System.err.println("Get Song By Keyword: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public List<Song> getSongByArtistId(Artist artist) {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT artists.name, songs.title, songs.length, songs.genre FROM songs JOIN albums ON songs.album_id = albums.id JOIN artists ON albums.artist_id = artists.id WHERE artists.id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, artist.getId());

            ResultSet res = prep.executeQuery();

            while(res.next()) {
                songs.add(new Song(res.getString(artist.getName()), res.getString("songs.title"), res.getString("songs.length"), res.getString("songs.genre")));
            }

        } catch(SQLException e) {
            System.err.println("Get Song By Artist ID: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public List<Song> getSongByArtistKeyword(Artist keyword) {
        List<Song> songs = new ArrayList<>();

        String query = "SELECT artists.name, songs.title, songs.length, songs.genre FROM songs JOIN albums ON songs.album_id = albums.id JOIN artists ON albums.artist_id = artists.id WHERE artists.name LIKE ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword.getName() + "%");
            ResultSet res = prep.executeQuery();

            while(res.next()) {
                songs.add(new Song(res.getString(keyword.getName()), res.getString("songs.title"), res.getString("songs.length"), res.getString("songs.genre")));
            }

        } catch(SQLException e) {
            System.err.println("Get Song By Keyword: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public boolean updateSong(Song song) {
        String query = "UPDATE songs SET title = ?, length = ?, genre = ? WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, song.getTitle());
            prep.setString(2, song.getLength());
            prep.setString(3, song.getGenre());
            prep.setInt(4, song.getId());

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Update Song: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveSong(Song song) {
        String query = "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, song.getId());

            int row = prep.executeUpdate();

            return row > 0;

        } catch(SQLException e) {
            System.err.println("Archive Song: " + e.getMessage());

        }

        return false;
    }

    @Override
    public List<Song> readAllArchivedSongs() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM songs WHERE is_archived = 1";

        try(Connection conn = db.connect();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery(query)) {

            while(res.next()) {
                songs.add(new Song(res.getInt("id"), res.getString("title"), res.getString("length"), res.getString("genre")));
            }

        } catch(SQLException e) {
            System.err.println("Read Archived Songs: " + e.getMessage());
        }
        return songs;
    }

    @Override
    public boolean restoreArchivedSong(Song song) {
        String query = "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, song.getId());

            int row = prep.executeUpdate();

            return row > 0;

        } catch(SQLException e) {
            System.err.println("Restore Archived Song: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteSong(Song song) {
        String query = "DELETE FROM songs WHERE id = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, song.getId());

            int row = prep.executeUpdate();

            return row > 0;

        } catch(SQLException e) {
            System.err.println("Delete Song: " + e.getMessage());
        }
        return false;
    }
}
