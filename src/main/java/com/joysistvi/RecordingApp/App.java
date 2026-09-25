package com.joysistvi.RecordingApp;

import com.joysistvi.RecordingApp.cliview.ArtistView;
import com.joysistvi.RecordingApp.config.DatabaseConnection;
import com.joysistvi.RecordingApp.controller.ArtistController;
import com.joysistvi.RecordingApp.repo.ArtistRepo;
import com.joysistvi.RecordingApp.repo.ArtistRepoImpl;
import com.joysistvi.RecordingApp.service.ArtistService;
import com.joysistvi.RecordingApp.service.ArtistServiceImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DatabaseConnection db = new DatabaseConnection();

        // -- Artist feature wiring --
        ArtistRepo artistRepository = new ArtistRepoImpl(db);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistController artistController = new ArtistController(artistService);
        ArtistView artistTemplate = new ArtistView(artistController, input);

        // Straight into Artist Management - no main menu needed yet
        artistTemplate.run();
    }
}
