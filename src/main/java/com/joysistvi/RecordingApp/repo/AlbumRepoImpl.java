package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepoImpl implements AlbumRepo{

    private final DatabaseConnection db;

    public AlbumRepoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public boolean createAlbum(Album album) {
        String query = "INSERT INTO albums (name) VALUES (?)";

        try (Connection conn = db.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {


            prep.setString(1, album.getName());

            int rows = prep.executeUpdate();

            return rows > 0;

        } catch(SQLException e) {
            System.err.println("Create an Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Album> getAllAlbum() {

        List<Album> albums = new ArrayList<>();
        String query = "SELECT * FROM albums WHERE is_archived = 0";

        try {
            Connection conn = db.connect();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery(query);

            while (result.next()) {
                albums.add(new Album(result.getInt("id"), result.getString("name"), result.getInt("year")));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return List.of();
    }

    @Override
    public Album getAlbumByArtistId(Artist artist) { // join
        Album album = new Album(artist.getId());
        String query = """
                    SELECT
                        artists.id AS artist_id,
                        artists.name AS artist_name,
                        albums.name AS album_name,
                        albums.year AS album_year
                    FROM albums
                    JOIN artists ON albums.artist_id = artists.id
                    WHERE artists.id = ? AND is_archived = 0
""";

        try {
            Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query);

            prep.setInt(1, artist.getId());
            ResultSet res = prep.executeQuery();

            while (res.next()) {
                return new Album(res.getInt(artist.getId()), (res.getString("artist_name") + res.getString("album_name") + res.getInt("album_year")));
            }

        } catch(SQLException e) {
            System.err.println("Getting album: " + e.getMessage());
        }

        return album;
    }

    @Override
    public List<Album> searchAlbum(String keyword) {
        List<Album> albums = new ArrayList<>();
        String query = "SELECT * FROM albums WHERE name = ? AND is_archived = 0";

        try(Connection conn = db.connect();
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery(query)) {

            while(res.next()) {
                albums.add(new Album(res.getInt("id"), res.getString("%" + "name" + "%")));
            }

        } catch(SQLException e) {
            System.err.println("Search Album: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public List<Album> searchAlbumByArtistKeyword(Artist artist) { // fix
        List<Album> albums = new ArrayList<>();

        String query = """
                SELECT
                    artists.id AS artist_id,
                    artists.name AS artist_name,
                    albums.name AS album_name,
                    albums.year AS album_year
                FROM albums
                JOIN artists ON albums.artist_id = artists.id
                WHERE artists.name LIKE ? AND albums.is_archived = 0
                """;

        try (Connection conn = db.connect();
             PreparedStatement prep = conn.prepareStatement(query)){


            prep.setString(1, "%" + artist.getName() + "%");

            try(ResultSet res = prep.executeQuery()) {

                while (res.next()) {
                    Album album = new Album(res.getInt("artist_id"), res.getString("album_name"));

                    albums.add(album);
                }
            }

        } catch(SQLException e) {
            System.err.println("Search album by keyword: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public boolean updateAlbum(Album album) {
        String query = "UPDATE albums SET name = ?, year = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {


            prep.setString(1, album.getName());
            prep.setInt(2, album.getYear());

            int rows = prep.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Update album: " + e.getMessage());
        }


        return false;
    }

    @Override
    public boolean archiveAlbum(Album album) {
        String query = "UPDATE albums SET is_archived = 1, WHERE id = ?";

        try {
            Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query);

            prep.setInt(1, album.getId());

            int rows = prep.executeUpdate();
            return rows > 0;
        } catch(SQLException e) {
            System.err.println("Archive artist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAlbum(Album album) {
        String query = "DELETE FROM albums WHERE name id = ?";

        try {
            Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query);

            prep.setInt(1, album.getId());

            int rows = prep.executeUpdate();

            return rows > 0;
        } catch(SQLException e) {
            System.err.println("Delete album: " + e.getMessage());
        }

        return false;
    }
}
