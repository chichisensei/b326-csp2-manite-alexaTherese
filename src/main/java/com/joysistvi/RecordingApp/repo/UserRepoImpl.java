package com.joysistvi.RecordingApp.repo;

import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.model.User;
import com.mysql.cj.protocol.Resultset;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoImpl implements UserRepo {

    private final DatabaseConnection db;

    public UserRepoImpl(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        String query = "INSERT INTO users (username, password, is_admin) VALUES(?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUserName());
            prep.setString(2, hashedPassword);
            prep.setBoolean(3, false);

            int row = prep.executeUpdate();

            return row > 0;
        } catch(SQLException e) {
            System.err.println("Create User: " + e.getMessage());
        }

        return false;
    }


    @Override
    public User loginUser(User user) {
        String query = """
                        SELECT id, username, password, is_admin
                        FROM users
                        WHERE username = ?
                        """;

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getUserName());

            ResultSet res = prep.executeQuery();

            if (res.next()) {
                String storedHash = res.getString("password");

                boolean passwordCorrect = BCrypt.checkpw(user.getPassword(), storedHash);

                if (passwordCorrect) {

                    User loggedInUser = new User();

                    loggedInUser.setId(
                            res.getInt("id")
                    );

                    loggedInUser.setUserName(
                            res.getString("username")
                    );

                    loggedInUser.setIs_admin(
                            res.getBoolean("is_admin")
                    );

                    return loggedInUser;
                }

            }



        } catch(SQLException e) {
            System.err.println("Login user: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean existByUsername(String username) {
        String query = "SELECT 1 FROM USERS WHERE username = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);

            ResultSet res = prep.executeQuery();

            return res.next();

        } catch(SQLException e) {
            System.out.println("Exist by username: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean isValidLogin(String username, String password) {

        String query = "SELECT 1 FROM users WHERE username = ? AND password = ?";

        try(Connection conn = db.connect();
            PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, username);
            prep.setString(2, password);

            ResultSet res = prep.executeQuery();

            while(res.next()) {
                return res.next();
            }
        } catch(SQLException e) {
            System.err.println("Login validation: " + e.getMessage());
        }
        return false;
    }
}
