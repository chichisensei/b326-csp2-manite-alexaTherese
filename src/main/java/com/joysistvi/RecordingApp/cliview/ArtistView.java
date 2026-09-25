package com.joysistvi.RecordingApp.cliview;

import com.joysistvi.RecordingApp.controller.ArtistController;
import com.joysistvi.RecordingApp.model.Artist;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistController artistController;
    private final Scanner input;

    public ArtistView(ArtistController artistController, Scanner input) {
        this.artistController = artistController;
        this.input = input;
    }

    public void run() {
        int choice;

        do {
            printMenu();

            choice = promptChoice();

            switch(choice) {
                case 1 -> viewAllArtists();
                case 2 -> searchArtist();
                case 3 -> addArtist();
                case 4 -> updateArtist();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again!");

            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                input.nextLine();
            }

        } while(choice != 0);
    }

    private void printMenu() {
        String menu = "1. View All Artists\n2. Search Artist\n3. Add Artist\n4. Update Artists\n5. Archive\n6. Restore\n7. Delete\n0. Back";
        System.out.println("\n=== Artist Management ===");


    }

    public int promptChoice() {
        System.out.println("Choice: ");
        return readInt();

    }

    private int readInt() {
        while (true) {
            String scanner = input.nextLine();

            try {
                return Integer.parseInt(scanner.trim());
            } catch (RuntimeException e) {
                System.err.println("Please enter a valid input!");
            }
        }
    }

    private void viewAllArtists() {
        System.out.println("\n----- View All Artists -----");
        List<Artist> artists = artistController.handleViewAllArtists();
        printArtists(artists);
    }

    private void searchArtist() {
        System.out.println("\n----- Search Artists -----");
        System.out.println("Enter name: ");
        String keyword = input.nextLine();
        List<Artist> artists = artistController.handleSearchArtist(keyword);
        printArtists(artists);
    }

    private void addArtist() {
        System.out.println("\n----- Add Artists -----");
        System.out.println("Enter name: ");
        String name = input.nextLine();

        Artist artist = new Artist(name);
        boolean isSuccess = artistController.handleCreateArtist(artist);
        System.out.println(isSuccess ? "Artist added successfully" : "Failed to add artist!");

        if(isSuccess) {
            System.out.println();
            viewAllArtists();
        }
    }

    private void updateArtist() {
        System.out.println("\n----- Update Artists ----- ");

        viewAllArtists();

        System.out.println("Artist ID to update: ");
        int id = readInt();

        Artist current = artistController.handleGetArtistById(id);

        if (current == null) {
            System.out.println("No artist found with ID " + id + ". Please check the ID & try again!");
            return;
        }

        System.out.println("New Name [ " + current.getName() + "] (Press Enter to keep the current): ");
        String name = input.nextLine();
        if (name.trim().isEmpty()) {
            name = current.getName();
        }

        Artist artist = new Artist(id, name);

        boolean isSuccess = artistController.handleCreateArtist(artist);
        System.out.println(isSuccess ? "Artist added successfully" : "Failed to add artist!");

        if (isSuccess) { // read-after-write / refresh-after-mutation
            System.out.println();
            viewAllArtists();
        }



    }

    public void printArtists(List<Artist> artists) {
        if (artists.isEmpty()) {
            System.out.println("No Artist found!");
            return;
        }

        String border = "+" + "-".repeat(6) + "+" + "-".repeat(27) + "+";

        System.out.println(border);
        System.out.printf("| %-4s | %-25s |%n", "ID", "Name");

        for (Artist artist : artists) {
            System.out.printf("| %-4s | %-25s |%n", artist.getId(), artist.getName());
        }

        System.out.println(border);
    }


}
