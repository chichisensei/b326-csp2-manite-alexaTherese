package com.joysistvi.RecordingApp.cliview;

import com.joysistvi.RecordingApp.controller.ArtistController;
import com.joysistvi.RecordingApp.model.Artist;
import com.joysistvi.RecordingApp.model.User;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistController artistController;
    private final Scanner input;
    private final User user;

    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GOLD = "\033[33m";
    private static final String DIM = "\033[2m";

    public ArtistView(ArtistController artistController, Scanner input, User user) {
        this.artistController = artistController;
        this.input = input;
        this.user = user;
    }

    public void run() {
        int choice;

        do {
            clearScreen();
            if (!user.isIs_admin()) {
                printMenuUser();
            } else {
                printMenuAdmin();
            }


            choice = readInt(input, "Enter choice");

            switch(choice) {
                case 1 -> viewAllArtists();
                case 2 -> searchArtist();
                case 3 -> {
                    if(user.isIs_admin()) {
                        addArtist();
                    } else {
                        System.out.println("Access Denied");
                    }
                }
                case 4 -> {
                    if(user.isIs_admin()) {
                        updateArtist();
                    } else {
                        System.out.println("Access Denied");
                    }
                }
                case 5 -> {
                    if(user.isIs_admin()) {
                        archiveArtist();
                    } else {
                        System.out.println("Access Denied");
                    }
                }
                case 6 -> {
                    if(user.isIs_admin()) {
                        restoreArchive();
                    } else {
                        System.out.println("Access Denied");
                    }
                }
                case 7 -> {
                    if(user.isIs_admin()) {
                        deleteArtist();
                    } else {
                        System.out.println("Access Denied");
                    }
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("Invalid choice. Try again!");

            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                input.nextLine();
            }

        } while(choice != 0);
    }

    private void printMenuUser() {
        String menu = """
                【１】View All Artist
                【２】Search Artist
                【０】Back
                """;
        displayMenu("Chipotify", menu);

    }

    private void printMenuAdmin() {
        String menu = """
                【１】View All Artist
                【２】Search Artist
                【３】Add Artist
                【４】Update Artist
                【５】Archive Artist
                【６】Restore Archive
                【７】Delete Artist
                【０】Back
                """;
        displayMenu("Chipotify", menu);

    }




    private void viewAllArtists() {
        System.out.println("\n----- View All Artists -----");
        List<Artist> artists = artistController.handleViewAllArtists();
        printArtists(artists);
    }

    private void viewAllArchivedArtist() {
        System.out.println("\n----- View All Artists -----");
        List<Artist> artists = artistController.handleReadAllArchivedArtist();
        printArtists(artists);
    }

    private void searchArtist() {
        displayMenu("Search Artists");

        String keyword = promptInput(input, "Enter name");
        List<Artist> artists = artistController.handleSearchArtist(keyword);
        printArtists(artists);
    }

    private void addArtist() {
        displayMenu("Add Artist");

        System.out.println();
        String name = promptInput(input, "Enter name");

        Artist artist = new Artist(name);
        boolean isSuccess = artistController.handleCreateArtist(artist);
        System.out.println(isSuccess ? "Artist added successfully" : "Failed to add artist!");

        if(isSuccess) {
            System.out.println();
            viewAllArtists();
        }
    }

    private void updateArtist() {
        displayMenu("Update Artists");

        viewAllArtists();

        System.out.println();
        int id = readInt(input, "Artist ID to update");

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

        boolean isSuccess = artistController.handleUpdateArtist(artist);
        System.out.println(isSuccess ? "Artist added successfully" : "Failed to add artist!");

        if (isSuccess) { // read-after-write / refresh-after-mutation
            System.out.println();
            viewAllArtists();
        }



    }

    private void archiveArtist() {
        displayMenu("Archived Artists");

        viewAllArtists();

        int id = readInt(input, "Artist ID to archive");

        boolean is_success = artistController.handleArchiveArtist(id);
        System.out.println(is_success ? "Artist archived successfully" : "Failed to archive");

        if (is_success) {
            System.out.println();
            viewAllArtists();
        }
    }

    private void restoreArchive() {
        displayMenu("Restore Archives");

        viewAllArchivedArtist();

        int id = readInt(input, "Artist ID to restore");

        boolean is_success = artistController.handleRestoreArtist(id);

        System.out.println(is_success ? "Artist restored successfully" : "Failed to restore");

        if (is_success) {
            System.out.println();
            viewAllArtists();
        }
    }

    private void deleteArtist() {
        displayMenu("Delete Artists");

        viewAllArtists();

        int id = readInt(input, "Artist ID to delete");

        boolean is_success = artistController.handleDeleteArtist(id);

        System.out.println(is_success ? "Artist deleted successfully!" : "Failed to delete!");

        if (is_success) {
            System.out.println();
            viewAllArtists();
        }
    }

    public void printArtists(List<Artist> artists) {
        if (artists.isEmpty()) {
            displayMenu("Artists", "Empty");
            return;
        }

        StringBuilder content = new StringBuilder();

        content.append(String.format("%-5s %-25s%n", "ID", "Name"));
        content.append("─".repeat(32)).append("\n");



        for (Artist artist : artists) {
            content.append(String.format("%5d %-25s%n", artist.getId(), artist.getName()));
        }

        displayMenu("Artists", content.toString());


    }

    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private int readInt(Scanner input, String label) {
        while(true) {
            try {
                return Integer.parseInt(promptInput(input, label));
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid input!");
            }
        }
    }

    private static String promptInput(Scanner input, String label) {
        String prompt = label + " ○ ";

        int width = prompt.length() + 10;

        String topBorder = createMusicBorder(width);
        String bottomBorder = "─".repeat(width);

        System.out.println();
        System.out.println(RED + topBorder + RESET);

        System.out.print(GOLD + prompt + RESET);

        String value = input.nextLine().trim();

        System.out.println(RED + bottomBorder + RESET);

        return value;

    }

    private static String createMusicBorder(int width) {
        int sideLength = (width - 3) / 2;

        return "─".repeat(sideLength) + " ♪ " + "─".repeat(width - sideLength - 3);
    }

    private static void displayMenu(String title, String content) {
        int width = Math.max(getVisualLength(title), getMaxLength(content.split("\n"))) + 10;

        String top = createMusicBorder(width);

        System.out.println();
        System.out.println(RED + top + RESET);
        System.out.println(GOLD + centerText("○ " + title + " ○", width) + RESET);

        System.out.println(RED + "─".repeat(width) + RESET);

        System.out.println(content);

    }

    private static void displayMenu(String title) {
        int width = Math.max(getVisualLength(title), getMaxLength(title.split("\n"))) + 10;

        String top = createMusicBorder(width);

        System.out.println();
        System.out.println(RED + top + RESET);
        System.out.println(GOLD + centerText("○ " + title + " ○", width) + RESET);

        System.out.println(RED + "─".repeat(width) + RESET);



    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;

        if (padding <= 0) {
            return text;
        }

        return " ".repeat(padding) + text;
    }

    private static int getVisualLength(String text) {
        if (text == null || text.isEmpty()) return 0;

        String cleanText = text.replaceAll("\u001B\\[[;\\d]*m", "");

        int length = cleanText.length();

        for (char c : cleanText.toCharArray()) {
            if (c == '【' || c == '】' || c == '₱' || c == '[' || c == ']') {
                length += 1;
            }
        }
        return length;
    }

    private static int getMaxLength(String[] lines) {
        int max = 0;
        for (String line : lines) {
            int visuallen = getVisualLength(line);
            if (visuallen > max) {
                max = visuallen;
            }

//            if (line.length() > max) {
//                max = line.length();
//            }
        }
        return max;
    }


}
