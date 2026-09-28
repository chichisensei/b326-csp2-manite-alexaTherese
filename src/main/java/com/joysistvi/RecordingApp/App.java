package com.joysistvi.RecordingApp;

import com.joysistvi.RecordingApp.cliview.ArtistView;
import com.joysistvi.RecordingApp.cliview.UserView;
import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.controller.*;
import com.joysistvi.RecordingApp.model.Album;
import com.joysistvi.RecordingApp.model.Playlist;
import com.joysistvi.RecordingApp.model.User;
import com.joysistvi.RecordingApp.repo.*;
import com.joysistvi.RecordingApp.service.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DatabaseConnection db = new DatabaseConnection();

        // -- User feature wiring --
        UserRepo userRepo = new UserRepoImpl(db);
        UserService userService = new UserServiceImpl(userRepo);
        UserController userController = new UserController(userService);
        UserView userTemplate = new UserView(userController, input);



        // -- Song feature wiring --
        SongRepo songRepo = new SongRepoImpl(db);
        SongService songService = new SongServiceImpl(songRepo);
        SongController songController = new SongController(songService);

        // -- Album feature wiring --
        AlbumRepo albumRepo = new AlbumRepoImpl(db);
        AlbumService albumService = new AlbumServiceImpl(albumRepo);
        AlbumController albumController = new AlbumController(albumService);

        // -- Playlist feature wiring --
        PlaylistRepo playlistRepo = new PlaylistRepoImpl(db);
        PlaylistService playlistService = new PlaylistServiceImpl(playlistRepo);
        PlaylistController playlistController = new PlaylistController(playlistService);





        // Straight into Artist Management - no main menu needed yet
//        artistTemplate.run();

        User user = userTemplate.run();

        if (user == null) {
            return;
        }

        // -- Artist feature wiring --
        ArtistRepo artistRepository = new ArtistRepoImpl(db);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistTemplate = new ArtistView(artistController, input, user);
    }
}
